package com.cubie.openapi.sdk.exception;

public final class CubieServiceException extends CubieException {

  private static final long serialVersionUID = -7722928155969441829L;
  private final int code;
  private final String reason;

  public CubieServiceException(final int code, final String reason) {
    this.code = code;
    this.reason = reason;
  }

  public int getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return getReason();
  }

  public String getReason() {
    return reason;
  }

  @Override
  public String toString() {
    return "CubieServiceException [code=" + code + ", reason=" + reason + "]";
  }

}
