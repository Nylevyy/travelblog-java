package ru.travelblog.exceptions;

import lombok.Getter;

@Getter
public abstract class AbstractException extends RuntimeException {
  private final Integer internalErrorCode;
  private final String errorDescription;

  public AbstractException(Integer code, String message) {
    super(String.format("%s: %s", code, message));
    this.errorDescription = message;
    this.internalErrorCode = code;
  };
}
