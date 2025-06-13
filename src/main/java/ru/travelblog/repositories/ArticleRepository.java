package ru.travelblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.travelblog.entities.ArticleEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {
  List<ArticleEntity> findAllByUserId(UUID userId);

  Optional<ArticleEntity> findOneByIdAndUserId(UUID articleId, UUID userId);

  Boolean existsByIdAndUserId(UUID articleId, UUID userId);

  void deleteByIdAndUserId(UUID articleId, UUID userId);
}
