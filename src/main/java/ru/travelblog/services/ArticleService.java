package ru.travelblog.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.travelblog.domain.Article;
import ru.travelblog.domain.User;
import ru.travelblog.entities.ArticleEntity;
import ru.travelblog.entities.UserEntity;
import ru.travelblog.repositories.ArticleRepository;
import ru.travelblog.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleRepository articleRepository;
  private final UserRepository userRepository;

  public List<Article> getUserArticles(UUID userId) throws Exception {
    User user = getUserById(userId);

    return articleRepository.findAllByUserId(userId)
        .stream()
        .map(e -> Article.fromEntity(e, user))
        .toList();
  }

  public Article createArticleForUser(Article article, UUID userId) throws Exception {
    User user = getUserById(userId);

    ArticleEntity e = new ArticleEntity();
    e.setTitle(article.getTitle());
    e.setDescription(article.getDescription());
    e.setLocation(article.getLocation());
    e.setDate(article.getDate());
    e.setIsImportant(article.getIsImportant());
    e.setUser(user.toEntity());
    e = articleRepository.saveAndFlush(e);
    return Article.fromEntity(e, user);
  }

  private User getUserById(UUID userId) throws Exception {
    Optional<UserEntity> userEntity = userRepository.findById(userId);

    if (userEntity.isEmpty()) {
      throw new Exception(String.format("user with %s is not found", userId));
    }

    return User.fromEntity(userEntity.get());
  }
}
