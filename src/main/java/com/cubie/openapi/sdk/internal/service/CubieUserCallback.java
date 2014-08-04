package com.cubie.openapi.sdk.internal.service;

import org.json.JSONException;

import com.cubie.openapi.sdk.CubieServiceCallback;
import com.cubie.openapi.sdk.exception.CubieException;
import com.cubie.openapi.sdk.service.CubieUser;

public class CubieUserCallback extends AbstractCubieServiceCallback<CubieUser> {

  public CubieUserCallback(CubieServiceCallback<CubieUser> callback) {
    super(callback);
  }

  @Override
  public void onSuccess(String content) {
    try {
      callback.done(CubieUser.decode(content), null);
    } catch (final JSONException e) {
      callback.done(null, new CubieException(e));
    }
  }
}
