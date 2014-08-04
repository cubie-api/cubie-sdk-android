package com.cubie.openapi.sdk.exception;

public final class CubieMessageEmptyException extends CubieException {
  private static final long serialVersionUID = 114894965459209128L;

  public CubieMessageEmptyException() {
    super();
  }

  public CubieMessageEmptyException(final String detailMessage) {
    super(detailMessage);
  }

  public CubieMessageEmptyException(final String detailMessage, final Throwable throwable) {
    super(detailMessage, throwable);
  }

  public CubieMessageEmptyException(final Throwable throwable) {
    super(throwable);
  }

}
