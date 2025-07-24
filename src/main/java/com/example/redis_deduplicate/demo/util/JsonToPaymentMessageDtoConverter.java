package com.example.redis_deduplicate.demo.util;

import com.example.redis_deduplicate.demo.model.kafka.PaymentMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Если просто в коде нужно однажды парсить строку
 * Теперь где‑угодно можно @Autowired Converter<String,PaymentMessageDTO> и делать converter.convert(rawJson).
 */
@Component
public class JsonToPaymentMessageDtoConverter implements Converter<String, PaymentMessageDTO> {

    private final ObjectMapper om = new ObjectMapper().findAndRegisterModules();

    @Override
    public PaymentMessageDTO convert(String source) {
        try {
            return om.readValue(source, PaymentMessageDTO.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Bad JSON for PaymentMessageDTO", e);
        }
    }
}

