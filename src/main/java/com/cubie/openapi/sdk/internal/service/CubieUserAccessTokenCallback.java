package com.cubie.openapi.sdk.internal.service;

import org.json.JSONException;

import com.cubie.openapi.sdk.CubieServiceCallback;
import com.cubie.openapi.sdk.service.CubieUserAccessToken;

public class CubieUserAccessTokenCallback extends
    AbstractCubieServiceCallback<CubieUserAccessToken> {
  public CubieUserAccessTokenCallback(CubieServiceCallback<CubieUserAccessToken> callback) {
    super(callback);
  }

  @Override
  public void onSuccess(String content) {
    try {
      callback.done(CubieUserAccessToken.decode(content), null);
    } catch (final JSONException e) {

    }
  }
}
