package ru.travelblog.exceptions;

public class AuthException extends AbstractException {
  public AuthException(AuthErrors errorCode) {
    super(errorCode.internalErrorCode, errorCode.errorDescription);
  }

  public static enum AuthErrors implements AbstractErrorEnum {
    ACCESS_TOKEN_EXPIRED(4001, "Access token is expired"),
    ACCESS_TOKEN_INVALID(4002, "Access token is invalid"),
    REFRESH_TOKEN_EXPIRED(4003, "Refresh token is expired"),
    REFRESH_TOKEN_INVALID(4004, "Refresh token is invalid"),
    USER_NOT_FOUND(4005, "User not found"),
    ;

    public Integer internalErrorCode;
    public String errorDescription;

    private AuthErrors(Integer code, String message) {
      this.errorDescription = message;
      this.internalErrorCode = code;
    }

    public Integer getInternalErrorCode() {
      return this.getInternalErrorCode();
    };

    public String getErrorDescription() {
      return this.getErrorDescription();
    };
  }
}
