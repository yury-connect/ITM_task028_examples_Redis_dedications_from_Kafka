package com.example.redis_deduplicate.demo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


/**
 * 4. Participant Entity (Отправитель/Получатель)
 */
@Entity
@Table(name = "participants")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "participant_id", updatable = false, nullable = false, columnDefinition = "UUID")
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(length = 34, nullable = false) // IBAN максимальная длина
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9]{15,34}$", message = "Invalid account format")
    private String account;

    @Column(length = 128, nullable = false)
    @NotBlank
    @Size(min = 1, max = 128)
    private String name;

    @Column(length = 12, updatable = false, nullable = false)
    @NotBlank
    @Pattern(regexp = "^(\\d{10}|\\d{12})$", message = "INN must be 10 or 12 digits")
    private String inn;
}
