package ru.travelblog.controllers.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDto {
  private String message;
  private HttpStatus status;
}
