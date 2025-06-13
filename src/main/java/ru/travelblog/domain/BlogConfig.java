package ru.travelblog.domain;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.travelblog.entities.BlogConfigEntity;

@Data
@NoArgsConstructor
public class BlogConfig {
  private UUID id;
  private String title;

  public static BlogConfig fromEntity(BlogConfigEntity entity) {
    BlogConfig blogConfig = new BlogConfig();
    blogConfig.setId(entity.getId());
    blogConfig.setTitle(entity.getTitle());
    return blogConfig;
  }

  public BlogConfigEntity toEntity() {
    BlogConfigEntity e = new BlogConfigEntity();
    e.setId(id);
    e.setTitle(title);
    return e;
  }
}
