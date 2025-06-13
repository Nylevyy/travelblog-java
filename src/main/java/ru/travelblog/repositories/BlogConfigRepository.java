package ru.travelblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.travelblog.entities.BlogConfigEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogConfigRepository extends JpaRepository<BlogConfigEntity, UUID> {
  Optional<BlogConfigEntity> findByAppConfigId(UUID appConfigId);
}
