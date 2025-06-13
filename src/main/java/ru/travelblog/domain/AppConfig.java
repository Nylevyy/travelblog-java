package ru.travelblog.domain;

import java.util.Optional;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.travelblog.entities.AppConfigEntity;

@Data
@NoArgsConstructor
public class AppConfig {
  private UUID id;
  private User user;
  private BlogConfig blogConfig;

  public static AppConfig fromEntity(AppConfigEntity entity, User user) {
    AppConfig appConfig = new AppConfig();
    appConfig.setId(entity.getId());
    appConfig.setUser(user);

    Optional.ofNullable(entity.getBlogConfig()).ifPresent(
        bc -> appConfig.setBlogConfig(BlogConfig.fromEntity(bc)));

    return appConfig;
  }

  public AppConfigEntity toEntity() {
    AppConfigEntity e = new AppConfigEntity();
    e.setId(id);
    e.setUser(user.toEntity());

    Optional.ofNullable(blogConfig).ifPresent(
        bc -> e.setBlogConfig(bc.toEntity()));

    return e;
  }
}
