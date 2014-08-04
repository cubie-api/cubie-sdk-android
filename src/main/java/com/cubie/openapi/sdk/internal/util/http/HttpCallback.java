package com.cubie.openapi.sdk.internal.util.http;

import java.io.IOException;

public abstract class HttpCallback {
  public abstract void onException(final IOException e);

  public abstract void onFailure(final String content);

  public abstract void onSuccess(final String content);

}
