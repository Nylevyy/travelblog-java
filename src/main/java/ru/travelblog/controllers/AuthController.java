package ru.travelblog.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.travelblog.controllers.dto.HttpResponseDto;
import ru.travelblog.domain.User;
import ru.travelblog.dto.SignInRequest;
import ru.travelblog.dto.SignUpRequest;
import ru.travelblog.dto.UserDto;
import ru.travelblog.services.AuthenticationService;
import ru.travelblog.services.JwtTokenService;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
  public static final String HEADER_NAME = "Authorization";
  private final AuthenticationService authenticationService;
  private final JwtTokenService jwtTokenService;

  @PostMapping("/join")
  public HttpResponseDto<UserDto> signUp(@RequestBody SignUpRequest request, HttpServletResponse response) {
    HttpResponseDto<UserDto> loginResponse = new HttpResponseDto<UserDto>();

    User user = authenticationService.signUp(request);

    UserDto userDto = new UserDto(user.getId(), user.getUsername());

    loginResponse.setData(userDto);
    return loginResponse;
  }

  @PostMapping("/login")
  public HttpResponseDto<UserDto> signIn(@RequestBody SignInRequest request, HttpServletResponse response) {
    HttpResponseDto<UserDto> loginResponse = new HttpResponseDto<UserDto>();

    String jwt = authenticationService.signIn(request);
    
    response.setHeader(HEADER_NAME, jwt);
    Cookie cookie = new Cookie("Authorization", jwt);
    response.addCookie(cookie);
    
    UserDto user = new UserDto(
        jwtTokenService.extractUserId(jwt),
        jwtTokenService.extractUserName(jwt));
        
    loginResponse.setData(user);
    return loginResponse;
  }
}
