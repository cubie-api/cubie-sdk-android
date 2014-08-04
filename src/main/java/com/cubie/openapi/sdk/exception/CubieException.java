package com.cubie.openapi.sdk.exception;

public class CubieException extends RuntimeException {

  private static final long serialVersionUID = -2135282852809541893L;

  public CubieException() {
    super();
  }

  public CubieException(String detailMessage) {
    super(detailMessage);
  }

  public CubieException(String message, Throwable cause) {
    super(message, cause);
  }

  public CubieException(Throwable cause) {
    super(cause);
  }

}
