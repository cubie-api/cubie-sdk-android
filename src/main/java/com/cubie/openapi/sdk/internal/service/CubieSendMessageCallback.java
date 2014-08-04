package com.cubie.openapi.sdk.internal.service;

import org.json.JSONException;

import com.cubie.openapi.sdk.CubieServiceCallback;
import com.cubie.openapi.sdk.exception.CubieException;
import com.cubie.openapi.sdk.service.CubieSendAck;

public class CubieSendMessageCallback extends AbstractCubieServiceCallback<CubieSendAck> {
  public CubieSendMessageCallback(CubieServiceCallback<CubieSendAck> callback) {
    super(callback);
  }

  @Override
  public void onSuccess(String content) {
    try {
      callback.done(CubieSendAck.decode(content), null);
    } catch (final JSONException e) {
      callback.done(null, new CubieException(e));
    }
  }
}
