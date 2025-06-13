package ru.travelblog.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.travelblog.domain.User;
import ru.travelblog.entities.UserEntity;
import ru.travelblog.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;

  /**
   * Сохранение пользователя
   */
  public User save(User user) {
    UserEntity ue = user.toEntity();
    return User.fromEntity(repository.save(ue));
  }

  /**
   * Создание пользователя
   */
  public User create(User user) {
    if (repository.existsByUsername(user.getUsername())) {
      throw new RuntimeException("Пользователь с таким именем уже существует");
    }

    UserEntity ue = user.toEntity();
    return User.fromEntity(repository.save(ue));
  }

  /**
   * Получение пользователя по имени пользователя
   * <p>
   * Нужен для Spring Security
   */
  public UserDetailsService userDetailsService() {
    return this::getByUsername;
  }

  /**
   * Получение пользователя по имени пользователя
   */
  public User getByUsername(String username) {
    UserEntity ue = repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    return User.fromEntity(ue);
  }

  /**
   * Получение текущего пользователя
   */
  public User getCurrentUser() {
    // Получение имени пользователя из контекста Spring Security
    var username = SecurityContextHolder.getContext().getAuthentication().getName();
    return getByUsername(username);
  }
}
