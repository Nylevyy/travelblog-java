package ru.travelblog.exceptions;

public interface AbstractErrorEnum {
  public Integer getInternalErrorCode();
  public String getErrorDescription();
}
