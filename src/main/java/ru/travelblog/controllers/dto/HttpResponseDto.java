package ru.travelblog.controllers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HttpResponseDto<Response> {
  private Response data;
  private ErrorResponseDto error;
}
