package com.example.redis_deduplicate.demo.repository;

import com.example.redis_deduplicate.demo.model.entity.Participant;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
@Immutable
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    Optional<Participant> findByAccount(String account);

    Optional<Participant> findByInn(String inn);
}
