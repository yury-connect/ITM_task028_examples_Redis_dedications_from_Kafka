package com.example.redis_deduplicate.demo.model.kafka;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.mapper.AmountMapper;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class PaymentDTO {
    @NotBlank
    @Size(min = 1, max = 32)
    private String id;

    @NotBlank
    private String docRef;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String amount; // Принимаем как строку для точного парсинга

    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;

    @NotNull
    private ParticipantDTO sender;

    @NotNull
    private ParticipantDTO recipient;

    @Size(max = 512)
    private String purpose;

    // Метод для конвертации суммы
    public long getAmountInMinorUnits() throws PaymentValidationException {
        return AmountMapper.parseAmount(this.amount, this.currency);
    }
}
