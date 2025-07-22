package com.example.redis_deduplicate.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZonedDateTime;
import java.util.UUID;


/**
 * Сущность для хранения метаданных платежа.
 * Все поля неизменяемы после создания (updatable = false).
 */
@Entity
@Table(name = "metadata")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
//    @JdbcTypeCode(SqlTypes.UUID)
    private UUID eventId;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
//    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private ZonedDateTime timestamp;
}
