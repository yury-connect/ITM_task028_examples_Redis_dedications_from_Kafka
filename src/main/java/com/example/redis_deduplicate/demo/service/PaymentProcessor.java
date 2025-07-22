package com.example.redis_deduplicate.demo.service;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.mapper.PaymentMapper;
import com.example.redis_deduplicate.demo.model.entity.PaymentMessage;
import com.example.redis_deduplicate.demo.model.kafka.PaymentMessageDTO;
import com.example.redis_deduplicate.demo.repository.PaymentMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentProcessor {
    private final PaymentMapper paymentMapper;
    private final PaymentMessageRepository paymentMessageRepository;

    public void processPayment(PaymentMessageDTO messageDTO) throws PaymentValidationException {
        PaymentMessage paymentMessage = paymentMapper.toEntity(messageDTO);
        paymentMessageRepository.save(paymentMessage);
    }
}
