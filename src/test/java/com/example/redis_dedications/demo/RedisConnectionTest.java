package com.example.redis_dedications.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


// Проверьте подключение к Redis:
@SpringBootTest
class RedisConnectionTest {
    private static final Logger log = LoggerFactory.getLogger(RedisConnectionTest.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisConnection() {
        try {
            String result = redisTemplate.opsForValue().get("test");
            log.info("Redis connection successful. Test value: {}", result);
        } catch (Exception e) {
            log.error("Redis connection failed", e);
            throw e;
        }
    }
}

/*
Если проблема сохраняется — укажите:

Версию Spring Boot,

Логи ошибок,

Название IDE (IntelliJ/VSCode/etc.).
 */