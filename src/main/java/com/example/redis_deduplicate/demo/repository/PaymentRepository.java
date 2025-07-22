package com.example.redis_deduplicate.demo.repository;

import com.example.redis_deduplicate.demo.model.entity.Payment;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Immutable
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByDocRef(UUID docRef);
    Optional<Payment> findByDocumentNumber(String documentNumber);
}
