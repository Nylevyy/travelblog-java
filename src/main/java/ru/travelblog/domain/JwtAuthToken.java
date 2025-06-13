package ru.travelblog.domain;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class JwtAuthToken extends UsernamePasswordAuthenticationToken {
  private String token;

  public JwtAuthToken(Object principal, Object credentials, String token) {
    super(null, null);
    this.token = token;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}
