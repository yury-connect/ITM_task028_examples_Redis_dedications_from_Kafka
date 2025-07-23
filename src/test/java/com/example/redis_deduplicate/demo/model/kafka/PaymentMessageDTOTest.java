package com.example.redis_deduplicate.demo.model.kafka;

import com.example.redis_deduplicate.demo.DemoApplication;
import com.example.redis_deduplicate.demo.mapper.PaymentMapper;
import com.example.redis_deduplicate.demo.model.entity.PaymentMessage;
import com.example.redis_deduplicate.demo.repository.PaymentMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;


import java.util.UUID;
@Import(PaymentMapper.class) // удалить
@SpringBootTest(classes = DemoApplication.class)
@Testcontainers
class PaymentMessageDTOTest {

    // этот блок удалить
    @TestConfiguration
    static class MapperCfg {
        @Bean
        PaymentMapper paymentMapper() {
            return Mappers.getMapper(PaymentMapper.class);
        }
    }


    @Container
    static final PostgreSQLContainer<?> POSTGRES =
            new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        POSTGRES.start();
        r.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        r.add("spring.datasource.username", POSTGRES::getUsername);
        r.add("spring.datasource.password", POSTGRES::getPassword);
        // если используете Liquibase:
        r.add("spring.liquibase.enabled", () -> true);
        // чтобы Hibernate не правил схему:
        r.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
    }

    @Autowired
    PaymentMapper mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    PaymentMessageRepository repository;

    private final ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    @DisplayName("JSON -> DTO -> Entity -> Save")
    void shouldMapAndPersist() throws Exception {
        // given
        String json = """
        {
          "metadata": {
            "eventId": "7f4a8c01-2e3d-4b5f-89a1-0c6d7e8f9a0b",
            "timestamp": "2024-07-20T12:00:00Z"
          },
          "payment": {
            "id": "ПП-12345",
            "docRef": "11111111-2222-3333-4444-555555555555",
            "date": "2024-07-20",
            "amount": "10000.50",
            "currency": "RUB",
            "sender": {
              "account": "40702810500000012345",
              "name": "ООО Ромашка",
              "inn": "0987654321"
            },
            "recipient": {
              "account": "40817810200000054321",
              "name": "ИП Иванов",
              "inn": "1234567890"
            },
            "purpose": "Оплата по договору №123 от 2024-01-01"
          }
        }
        """;

        // when
        PaymentMessageDTO dto = om.readValue(json, PaymentMessageDTO.class);
        PaymentMessage entity = mapper.toEntity(dto); // может бросить PaymentValidationException
        PaymentMessage saved = repository.save(entity);

        // then
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(UUID.fromString("7f4a8c01-2e3d-4b5f-89a1-0c6d7e8f9a0b"),
                saved.getMetadata().getEventId());
        Assertions.assertEquals("ПП-12345", saved.getPayment().getDocumentNumber());
        Assertions.assertEquals("RUB", saved.getPayment().getCurrency());
        Assertions.assertEquals("ООО Ромашка", saved.getPayment().getSender().getName());
    }
}