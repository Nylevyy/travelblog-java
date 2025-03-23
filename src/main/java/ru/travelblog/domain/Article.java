package ru.travelblog.domain;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.travelblog.entities.ArticleEntity;

@Data
@NoArgsConstructor
public class Article {
  private UUID id;
  private String title;
  private String description;
  private String location;
  private Timestamp date;
  private Boolean isImportant;
  private User user;

  public static Article fromEntity(ArticleEntity entity, User user) {
    Article article = new Article();
    article.setId(entity.getId());
    article.setTitle(entity.getTitle());
    article.setDescription(entity.getDescription());
    article.setLocation(entity.getLocation());
    article.setDate(entity.getDate());
    article.setIsImportant(entity.getIsImportant());
    article.setUser(user);
    return article;
  }

  public ArticleEntity toEntity() {
    ArticleEntity e = new ArticleEntity();
    e.setId(id);
    e.setTitle(title);
    e.setDescription(description);
    e.setLocation(location);
    e.setDate(date);
    e.setIsImportant(isImportant);
    e.setUser(user.toEntity());
    return e;
  }
}
