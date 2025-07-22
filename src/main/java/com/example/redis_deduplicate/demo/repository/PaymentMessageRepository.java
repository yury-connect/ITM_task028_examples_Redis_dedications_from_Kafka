package com.example.redis_deduplicate.demo.repository;

import com.example.redis_deduplicate.demo.model.entity.PaymentMessage;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Immutable
public interface PaymentMessageRepository extends JpaRepository<PaymentMessage, UUID> {
    Optional<PaymentMessage> findByMetadata_EventId(UUID eventId);
}
