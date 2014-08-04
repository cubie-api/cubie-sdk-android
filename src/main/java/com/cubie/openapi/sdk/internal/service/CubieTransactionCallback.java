package com.cubie.openapi.sdk.internal.service;

import com.cubie.openapi.sdk.CubieServiceCallback;

public class CubieTransactionCallback extends AbstractCubieServiceCallback<Void> {
  public CubieTransactionCallback(CubieServiceCallback<Void> callback) {
    super(callback);
  }

  @Override
  public void onSuccess(String content) {
    callback.done(null, null);
  }
}
