package com.cubie.openapi.sdk.internal.util.http;

import java.io.IOException;

public class HttpResponse {
  public static HttpResponse complete(final int responseCode, final String content) {
    return new HttpResponse(responseCode, content, null);
  }

  public static HttpResponse fail(final IOException exception) {
    return new HttpResponse(INVALID_RESPONSE_CODE, null, exception);
  }

  private static final int INVALID_RESPONSE_CODE = Integer.MIN_VALUE;

  private final int responseCode;

  private final String content;

  private final IOException ioException;

  public HttpResponse(final int responseCode, final String content, final IOException ioException) {
    this.responseCode = responseCode;
    this.content = content;
    this.ioException = ioException;
  }

  public String getContent() {
    return content;
  }

  public IOException getIoException() {
    return ioException;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public boolean hasException() {
    return ioException != null;
  }

  public boolean isComplete() {
    return responseCode != INVALID_RESPONSE_CODE;
  }

  public boolean isSuccessful() {
    return !hasException() && isComplete() && responseCode >= 200 && responseCode < 400;
  }
}
