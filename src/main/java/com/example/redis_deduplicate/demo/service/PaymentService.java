package com.example.redis_deduplicate.demo.service;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.mapper.PaymentMapper;
import com.example.redis_deduplicate.demo.model.entity.Payment;
import com.example.redis_deduplicate.demo.model.kafka.PaymentMessageDTO;
import com.example.redis_deduplicate.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public void processPayment(PaymentMessageDTO messageDTO) throws PaymentValidationException {
        // Преобразуем только Payment
        Payment payment = paymentMapper.toEntity(messageDTO.getPayment());

        // Сохраняем через правильный репозиторий
        paymentRepository.save(payment);
    }
}
