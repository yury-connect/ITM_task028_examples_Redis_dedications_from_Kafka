package com.example.redis_deduplicate.demo.service;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.mapper.PaymentMapper;
import com.example.redis_deduplicate.demo.model.entity.PaymentMessage;
import com.example.redis_deduplicate.demo.model.kafka.PaymentMessageDTO;
import com.example.redis_deduplicate.demo.repository.PaymentMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


/*
Тут происходит сохранение в БД
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessor {

    private final PaymentMapper paymentMapper;
    private final PaymentMessageRepository paymentMessageRepository;


    /*
    Проведение платежа
     */
    public PaymentMessage processPayment(PaymentMessageDTO messageDTO) throws PaymentValidationException {
        PaymentMessage paymentMessageSrc = paymentMapper.toEntity(messageDTO);

        UUID id = paymentMessageRepository.save(paymentMessageSrc).getId();
        paymentMessageRepository.flush();
        PaymentMessage paymentMessage = paymentMessageRepository.findById(id).orElseThrow(
                () -> new PaymentValidationException("PaymentProcessor.processPayment: PaymentMessage с id " + id + " не найден!"));

                log.info("PaymentProcessor.processPayment: Payment saved - {}", paymentMessage);
        return paymentMessage;
    }
}
