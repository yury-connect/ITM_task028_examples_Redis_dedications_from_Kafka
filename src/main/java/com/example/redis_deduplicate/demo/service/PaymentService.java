package com.example.redis_deduplicate.demo.service;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.mapper.PaymentMapper;
import com.example.redis_deduplicate.demo.model.entity.Payment;
import com.example.redis_deduplicate.demo.model.entity.PaymentMessage;
import com.example.redis_deduplicate.demo.model.kafka.PaymentMessageDTO;
import com.example.redis_deduplicate.demo.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final ObjectMapper objectMapper;               // настроен Spring Boot
    private final PaymentProcessor paymentProcessor;


    public void processPayment(PaymentMessageDTO messageDTO) throws PaymentValidationException {
        // Преобразуем только Payment
        Payment payment = paymentMapper.toEntity(messageDTO.getPayment());

        // Сохраняем через правильный репозиторий
        paymentRepository.save(payment);
    }


    public PaymentMessage jsonToEntity(String json) throws JsonProcessingException {
        PaymentMessageDTO dto = objectMapper.readValue(json, PaymentMessageDTO.class);
        PaymentMessage paymentMessage = paymentProcessor.processPayment(dto);

        log.info("PaymentService.jsonToEntity: PaymentMessage paymentMessage - {}", paymentMessage);
        return paymentMessage;
    }
}
