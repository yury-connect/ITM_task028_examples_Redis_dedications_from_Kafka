package com.example.redis_dedications.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class RedisConnectionTestSimple {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    void testConnection() {
        try (RedisConnection connection = redisConnectionFactory.getConnection()) {
            assertThat(connection.ping()).isEqualTo("PONG");
        }
    }
}
