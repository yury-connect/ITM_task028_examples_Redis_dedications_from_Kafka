package com.example.redis_deduplicate.demo.model.kafka;

import com.example.redis_deduplicate.demo.model.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * 3. Payment Entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDTO {
    private String currency;
    private BigDecimal amount;

    public long getAmountInMinorUnits() {
        int decimals = CurrencyUtils.getDecimalsForCurrency(currency); // 100 для RUB/USD
        return amount.multiply(BigDecimal.valueOf(decimals)).longValue();
    }


    private String id;            // Номер документа ("ПП-12345")
    private String docRef;        // GUID из 1С
    private LocalDate date;       // Дата платежа
    private String  amount;        // Сумма (BigDecimal для точных расчетов)
    private String currency;      // Валюта ("RUB")
    private Participant sender;   // Отправитель
    private Participant recipient; // Получатель
    private String purpose;       // Назначение платежа
}
