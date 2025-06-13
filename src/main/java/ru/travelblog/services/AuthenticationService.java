package ru.travelblog.services;

import lombok.RequiredArgsConstructor;
import ru.travelblog.domain.User;
import ru.travelblog.dto.SignInRequest;
import ru.travelblog.dto.SignUpRequest;
import ru.travelblog.entities.Role;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserService userService;
  private final JwtTokenService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  /**
   * Регистрация пользователя
   *
   * @param request данные пользователя
   */
  public User signUp(SignUpRequest request) {

    var user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ROLE_USER)
        .build();

    return userService.create(user);
  }

  /**
   * Аутентификация пользователя
   *
   * @param request данные пользователя
   */
  public String signIn(SignInRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()));

    var user = userService
        .userDetailsService()
        .loadUserByUsername(request.getUsername());

    var jwt = jwtService.generateToken(user);
    return jwt;
  }
}