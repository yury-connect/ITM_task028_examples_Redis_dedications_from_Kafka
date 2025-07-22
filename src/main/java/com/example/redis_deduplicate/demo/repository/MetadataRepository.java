package com.example.redis_deduplicate.demo.repository;

import com.example.redis_deduplicate.demo.model.entity.Metadata;
import org.hibernate.annotations.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


/*
Добавление в репозиторий аннотацию @org.springframework.data.annotation.Immutable обеспечит:
1. Запрет на update/delete через репозиторий
2. Оптимизации для read-only операций
3. Явное указание намерений в коде
 */
@Repository
public interface MetadataRepository extends JpaRepository<Metadata, UUID> {
    @Immutable
    Optional<Metadata> findByEventId(UUID eventId);
}
