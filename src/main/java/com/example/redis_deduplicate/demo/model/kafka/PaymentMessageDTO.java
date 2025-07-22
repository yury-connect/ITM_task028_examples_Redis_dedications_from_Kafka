package com.example.redis_deduplicate.demo.model.kafka;

import com.example.redis_deduplicate.demo.model.entity.Metadata;
import com.example.redis_deduplicate.demo.model.entity.Payment;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * 1. Main Payment Entity (Корневой класс)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentMessageDTO {
    private Metadata metadata;
    private Payment payment;
}
