package com.example.redis_deduplicate.demo;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;


@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String REDIS_DEDUP_SET = "payment:dedup:set";
//    private static final Logger log = LoggerFactory.getLogger(PaymentConsumer.class);


    @KafkaListener(topics = "payments")
    public void listen(String message) throws PaymentValidationException {
        // Парсим UID из сообщения (формат: {"uid": "abc123", "amount": 100})
        String uid = parseUidFromJson(message);

        // Проверяем дубликат через Redis SET
        boolean isDuplicate = checkDuplicate(uid);

        if (isDuplicate) {
            log.info("Дубликат платежа, пропускаем: {}", uid);
            return;
        }

        // Обрабатываем платеж
        processPayment(message);

        // Добавляем UID в Redis SET с TTL 24 часа
        addToRedisSet(uid);
    }

    private String parseUidFromJson(String json) throws PaymentValidationException {
        try {
            JsonNode node = new ObjectMapper().readTree(json);
            return node.get("uid").asText();
        } catch (Exception e) {
            log.error("Ошибка парсинга UID из JSON: {}", json, e);
            throw new PaymentValidationException("Некорректный формат JSON", e);
        }
    }

    private boolean checkDuplicate(String uid) {
        // SADD возвращает 1, если элемента не было, 0 – если уже есть
        Long added = redisTemplate.opsForSet().add(REDIS_DEDUP_SET, uid);
        return added != null && added == 0;
    }

    private void addToRedisSet(String uid) {
        // Устанавливаем TTL для всего SET (24 часа)
        redisTemplate.expire(REDIS_DEDUP_SET, 24, TimeUnit.HOURS);
    }

    private void processPayment(String message) {
        // Логика обработки платежа...
        log.info("Обрабатываем платеж: {}", message);
    }
}
