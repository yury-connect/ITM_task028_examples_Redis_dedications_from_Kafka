package com.example.redis_deduplicate.demo.kafkaListener;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.model.kafka.PaymentMessageDTO;
import com.example.redis_deduplicate.demo.service.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


/**
 * Для Kafka‑консьюмеров
 */
@Service
@RequiredArgsConstructor
public class KafkaPaymentListener {

    private final PaymentProcessor processor;


    @KafkaListener(topics = "payments", groupId = "payment-group")
    public void listen(PaymentMessageDTO dto) throws PaymentValidationException {

        // при правильно сконфигурированном JsonDeserializer<KafkaMessageDTO>
        processor.processPayment(dto);
    }

}

