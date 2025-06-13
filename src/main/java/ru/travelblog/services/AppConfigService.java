package ru.travelblog.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.travelblog.domain.AppConfig;
import ru.travelblog.domain.BlogConfig;
import ru.travelblog.domain.User;
import ru.travelblog.entities.AppConfigEntity;
import ru.travelblog.entities.BlogConfigEntity;
import ru.travelblog.entities.UserEntity;
import ru.travelblog.repositories.ApplicationConfigRepository;
import ru.travelblog.repositories.BlogConfigRepository;
import ru.travelblog.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AppConfigService {
  private final UserRepository userRepository;
  private final ApplicationConfigRepository appConfigRepository;
  private final BlogConfigRepository blogConfigRepository;

  public AppConfig getUserAppConfig(UUID userId) throws Exception {
    User user = getUserById(userId);
    Optional<AppConfigEntity> optEntity = appConfigRepository.findOneByUserId(userId);

    if (optEntity.isEmpty()) {
      return createEmptyConfigForUser(user);
    }

    return AppConfig.fromEntity(optEntity.get(), user);
  }

  public AppConfig saveUserAppConfig(UUID userId, AppConfig appConfig) throws Exception {
    User user = getUserById(userId);
    appConfig.setUser(user);

    AppConfigEntity entity = appConfig.toEntity();
    entity = appConfigRepository.saveAndFlush(entity);
    return AppConfig.fromEntity(entity, user);
  }

  public BlogConfig saveUserBlogConfig(UUID userId, BlogConfig blogConfig) throws Exception {
    Optional<AppConfigEntity> optAppConfig = appConfigRepository.findOneByUserId(userId);

    if (optAppConfig.isEmpty()) {
      throw new Exception(String.format("У пользователя %s нет настроек приложения", userId));
    }

    BlogConfigEntity entity = blogConfig.toEntity();
    entity.setAppConfig(optAppConfig.get());
    entity = blogConfigRepository.saveAndFlush(entity);
    return BlogConfig.fromEntity(entity);
  }

  private AppConfig createEmptyConfigForUser(User user) {
    AppConfigEntity entity = new AppConfigEntity();
    BlogConfigEntity blogConfigEntity = new BlogConfigEntity();
    entity.setUser(user.toEntity());
    entity = appConfigRepository.save(entity);

    blogConfigEntity.setAppConfig(entity);
    blogConfigEntity = blogConfigRepository.saveAndFlush(blogConfigEntity);

    entity.setBlogConfig(blogConfigEntity);
    entity = appConfigRepository.saveAndFlush(entity);
    return AppConfig.fromEntity(entity, user);
  }

  private User getUserById(UUID userId) throws Exception {
    Optional<UserEntity> userEntity = userRepository.findById(userId);

    if (userEntity.isEmpty()) {
      throw new Exception(String.format("Пользователь с ID %s не найден", userId));
    }

    return User.fromEntity(userEntity.get());
  }
}
