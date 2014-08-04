package com.cubie.openapi.sdk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public final class CubieUserAccessToken {

  public static CubieUserAccessToken decode(String content) throws JSONException {
    final JSONObject obj = new JSONObject(content);
    try {
      return new CubieUserAccessToken(obj.getString("uid"),
          obj.getString("app_id"),
          obj.getString("access_token"),
          new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US).parse(obj.getString("expire_date")));
    } catch (final ParseException e) {
      throw new JSONException("cannot parse expire_date");
    }
  }

  private final String uid;
  private final String appId;
  private final String accessToken;
  private final Date expireDate;

  public CubieUserAccessToken(String uid, String appId, String accessToken, Date expireDate) {
    this.uid = uid;
    this.appId = appId;
    this.accessToken = accessToken;
    this.expireDate = expireDate;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final CubieUserAccessToken other = (CubieUserAccessToken) obj;
    if (accessToken == null) {
      if (other.accessToken != null) {
        return false;
      }
    } else if (!accessToken.equals(other.accessToken)) {
      return false;
    }
    if (appId == null) {
      if (other.appId != null) {
        return false;
      }
    } else if (!appId.equals(other.appId)) {
      return false;
    }
    if (expireDate == null) {
      if (other.expireDate != null) {
        return false;
      }
    } else if (!expireDate.equals(other.expireDate)) {
      return false;
    }
    if (uid == null) {
      if (other.uid != null) {
        return false;
      }
    } else if (!uid.equals(other.uid)) {
      return false;
    }
    return true;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getAppId() {
    return appId;
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public String getUid() {
    return uid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
    result = prime * result + ((appId == null) ? 0 : appId.hashCode());
    result = prime * result + ((expireDate == null) ? 0 : expireDate.hashCode());
    result = prime * result + ((uid == null) ? 0 : uid.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "CubieUserAccessToken [uid=" + uid
        + ", appId="
        + appId
        + ", accessToken="
        + accessToken
        + ", expireDate="
        + expireDate
        + "]";
  }

}
