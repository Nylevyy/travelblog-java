package ru.travelblog.domain;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.travelblog.entities.Role;
import ru.travelblog.entities.UserEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
  private UUID id;
  private String username;
  private String password;
  private Role role;

  public static User fromEntity(UserEntity entity) {
    User user = new User();
    user.setPassword(entity.getPassword());
    user.setUsername(entity.getUsername());
    user.setRole(entity.getRole());
    user.setId(entity.getId());
    return user;
  }

  public UserEntity toEntity() {
    UserEntity e = new UserEntity();
    e.setId(id);
    e.setPassword(password);
    e.setUsername(username);
    e.setRole(role);
    return e;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
