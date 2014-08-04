package com.cubie.openapi.sdk.internal.util;

import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.cubie.openapi.sdk.internal.intent.AccessToken;

public class Pref {
  public static Pref getInstance(final Context context) {
    if (pref == null) {
      pref = new Pref(context, Contexts.getAppKey(context));
    }
    return pref;
  }

  private static Pref pref;

  private final SharedPreferences sharedPreferences;

  private Pref(final Context context, final String appKey) {
    sharedPreferences = context.getSharedPreferences(appKey, Context.MODE_PRIVATE);
  }

  public void clearAccessToken() {
    final Editor editor = sharedPreferences.edit();
    editor.putString(AccessToken.class.getName(), null);
    editor.commit();
  }

  public String load(final String key) {
    return load(key, null);
  }

  public String load(final String key, final String defaultValue) {
    return sharedPreferences.getString(key, defaultValue);
  }

  public AccessToken loadAccessToken() throws JSONException {
    final String json = sharedPreferences.getString(AccessToken.class.getName(), null);
    if (json == null) {
      return null;
    }
    return AccessToken.fromJsonString(json);
  }

  public void store(final String key, final String value) {
    final Editor editor = sharedPreferences.edit();
    editor.putString(key, value);
    editor.commit();
  }

  public void storeAccessToken(final AccessToken accessToken) {
    final Editor editor = sharedPreferences.edit();
    editor.putString(AccessToken.class.getName(), accessToken.toJsonString());
    editor.commit();
  }
}
