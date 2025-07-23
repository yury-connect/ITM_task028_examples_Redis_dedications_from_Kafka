package com.example.redis_deduplicate.demo.model.kafka;

import com.example.redis_deduplicate.demo.model.entity.Metadata;
import com.example.redis_deduplicate.demo.model.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder
public class PaymentMessageDTO {
    @NotNull
    private MetadataDTO metadata;

    @NotNull
    private PaymentDTO payment;
}
