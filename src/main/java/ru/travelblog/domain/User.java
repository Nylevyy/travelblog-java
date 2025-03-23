package ru.travelblog.domain;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.travelblog.entities.UserEntity;

@Data
@NoArgsConstructor
public class User {
  private UUID id;
  private String username;
  private String password;

  public static User fromEntity(UserEntity entity) {
    User user = new User();
    user.setPassword(entity.getPassword());
    user.setUsername(entity.getUsername());
    user.setId(entity.getId());
    return user;
  }

  public UserEntity toEntity() {
    UserEntity e = new UserEntity();
    e.setId(id);
    e.setPassword(password);
    e.setUsername(username);
    return e;
  }
}
