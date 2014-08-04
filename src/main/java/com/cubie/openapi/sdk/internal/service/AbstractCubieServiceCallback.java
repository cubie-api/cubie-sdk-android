package com.cubie.openapi.sdk.internal.service;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.cubie.openapi.sdk.CubieServiceCallback;
import com.cubie.openapi.sdk.exception.CubieException;
import com.cubie.openapi.sdk.exception.CubieServiceException;
import com.cubie.openapi.sdk.internal.util.http.HttpCallback;

public abstract class AbstractCubieServiceCallback<T> extends HttpCallback {

  protected CubieServiceCallback<T> callback;

  public AbstractCubieServiceCallback(CubieServiceCallback<T> callback) {
    this.callback = callback;
  }

  @Override
  public void onException(IOException e) {
    callback.done(null, new CubieException(e));
  }

  @Override
  public void onFailure(final String content) {
    try {
      final JSONObject jsonObject = new JSONObject(content);
      final CubieServiceException cubieServiceException = new CubieServiceException(jsonObject.getInt("code"),
          jsonObject.getString("reason"));
      callback.done(null, cubieServiceException);
    } catch (final JSONException e) {
      new RuntimeException(e);
    }
  }

}
