package ru.travelblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.travelblog.entities.ArticleEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {
  List<ArticleEntity> findAllByUserId(UUID userId);
}