package com.cubie.openapi.sdk.internal.intent;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;

import com.cubie.openapi.sdk.internal.util.Strings;

public class AccessToken {
  public static AccessToken fromBundle(final Bundle bundle) {
    return new AccessToken(bundle.getString(KEY_UID),
        bundle.getString(KEY_OPEN_API_APP_ID),
        bundle.getString(KEY_ACCESS_TOKEN),
        bundle.getLong(KEY_EXPIRE_TIME));
  }

  public static AccessToken fromJsonString(final String json) throws JSONException {
    final JSONObject obj = new JSONObject(json);
    return new AccessToken(obj.getString(KEY_UID),
        obj.getString(KEY_OPEN_API_APP_ID),
        obj.getString(KEY_ACCESS_TOKEN),
        obj.getLong(KEY_EXPIRE_TIME));
  }

  private static final String KEY_UID = "com.cubie.openapi.sdk.model.AccessToken.uid";
  private static final String KEY_OPEN_API_APP_ID = "com.cubie.openapi.sdk.model.AccessToken.openApiAppId";
  private static final String KEY_ACCESS_TOKEN = "com.cubie.openapi.sdk.model.AccessToken.accessToken";
  private static final String KEY_EXPIRE_TIME = "com.cubie.openapi.sdk.model.AccessToken.expireTime";

  private final String uid;
  private final String openApiAppId;
  private final String accessToken;
  private final long expireTime;

  public AccessToken(String uid, String openApiAppId, String accessToken, long expireTime) {
    this.uid = uid;
    this.openApiAppId = openApiAppId;
    this.accessToken = accessToken;
    this.expireTime = expireTime;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public long getExpireTime() {
    return expireTime;
  }

  public String getOpenApiAppId() {
    return openApiAppId;
  }

  public String getUid() {
    return uid;
  }

  public boolean isValid() {
    return !Strings.isBlank(accessToken) && expireTime > System.currentTimeMillis();
  }

  public Bundle toBundle() {
    final Bundle bundle = new Bundle();
    bundle.putString(KEY_UID, uid);
    bundle.putString(KEY_OPEN_API_APP_ID, openApiAppId);
    bundle.putString(KEY_ACCESS_TOKEN, accessToken);
    bundle.putLong(KEY_EXPIRE_TIME, expireTime);
    return bundle;
  }

  public String toJsonString() {
    try {
      final JSONObject obj = new JSONObject();
      obj.put(KEY_UID, uid);
      obj.put(KEY_OPEN_API_APP_ID, openApiAppId);
      obj.put(KEY_ACCESS_TOKEN, accessToken);
      obj.put(KEY_EXPIRE_TIME, expireTime);
      return obj.toString();
    } catch (final JSONException e) {
      throw new RuntimeException("fail to encode AccessToken to JSON", e);
    }
  }

  @Override
  public String toString() {
    return "AccessToken [uid=" + uid
        + ", openApiAppId="
        + openApiAppId
        + ", accessToken="
        + accessToken
        + ", expireTime="
        + expireTime
        + "]";
  }

}
