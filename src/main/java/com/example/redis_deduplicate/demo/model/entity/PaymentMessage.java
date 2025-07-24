package com.example.redis_deduplicate.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


/**
 * 1. Main Payment Entity (Корневой класс)
 */
@Entity
@Table(name = "payment_messages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class PaymentMessage {

    /**
     * Primary key: UUID
     * Maps to the "id" column in payment_messages table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID")
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    /**
     * References metadata.id
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metadata_id", nullable = false, referencedColumnName = "id")
    private Metadata metadata;

    /**
     * References payments.payment_id
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false, referencedColumnName = "payment_id")
    private Payment payment;
}
