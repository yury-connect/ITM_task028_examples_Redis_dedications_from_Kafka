package com.example.redis_deduplicate.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


/**
 * 3. Payment Entity
 */
@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id", updatable = false, nullable = false, columnDefinition = "UUID")
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "document_number", length = 32, nullable = false)
    private String documentNumber; // поле 'id' в Kafka

    @Column(name = "doc_ref", nullable = false, columnDefinition = "UUID")
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID docRef;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    @Column(nullable = false, precision = 20, scale = 2)
    private Long amount;

    @Column(length = 3, nullable = false)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender_id", nullable = false)
    private Participant sender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recipient_id", nullable = false)
    private Participant recipient;

    @Column(columnDefinition = "TEXT")
    private String purpose;
}
