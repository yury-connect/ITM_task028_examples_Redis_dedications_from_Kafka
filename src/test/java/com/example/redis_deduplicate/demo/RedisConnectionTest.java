package com.example.redis_deduplicate.demo;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


// Проверьте подключение к Redis:
@SpringBootTest
class RedisConnectionTest {
    private static final Logger log = LoggerFactory.getLogger(RedisConnectionTest.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisConnection() {
        String result = redisTemplate.opsForValue().get("test");
        log.info("Redis connection successful. Test value: {}", result);
    }
}

/*
Если проблема сохраняется — укажите:

Версию Spring Boot,

Логи ошибок,

Название IDE (IntelliJ/VSCode/etc.).
 */