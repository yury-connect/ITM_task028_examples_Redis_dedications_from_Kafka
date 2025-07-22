package com.example.redis_deduplicate.demo.model.kafka;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * 4. Participant Entity (Отправитель/Получатель)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParticipantDTO {
    private String account;  // Номер счета
    private String name;     // Наименование
    private String inn;      // ИНН (только для получателя)
}
