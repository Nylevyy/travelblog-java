package ru.travelblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.travelblog.entities.AppConfigEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationConfigRepository extends JpaRepository<AppConfigEntity, UUID> {
  Optional<AppConfigEntity> findOneByUserId(UUID userId);

  Boolean existsByUserId(UUID userId);
}
