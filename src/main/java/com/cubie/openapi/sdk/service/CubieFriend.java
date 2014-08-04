package com.cubie.openapi.sdk.service;

import org.json.JSONException;
import org.json.JSONObject;

public final class CubieFriend {

  public static CubieFriend decode(JSONObject jsonObject) throws JSONException {
    return new CubieFriend(jsonObject.getString("uid"),
        jsonObject.getString("cubie_id"),
        jsonObject.getString("nickname"),
        jsonObject.getString("icon_url"),
        jsonObject.getBoolean("app_installed"));
  }

  private final String uid;
  private final String cubieId;
  private final String nickname;
  private final String iconUrl;
  private final boolean appInstalled;

  public CubieFriend(String uid,
      String cubieId,
      String nickname,
      String iconUrl,
      boolean appInstalled) {
    this.uid = uid;
    this.cubieId = cubieId;
    this.nickname = nickname;
    this.iconUrl = iconUrl;
    this.appInstalled = appInstalled;
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
    final CubieFriend other = (CubieFriend) obj;
    if (appInstalled != other.appInstalled) {
      return false;
    }
    if (cubieId == null) {
      if (other.cubieId != null) {
        return false;
      }
    } else if (!cubieId.equals(other.cubieId)) {
      return false;
    }
    if (iconUrl == null) {
      if (other.iconUrl != null) {
        return false;
      }
    } else if (!iconUrl.equals(other.iconUrl)) {
      return false;
    }
    if (nickname == null) {
      if (other.nickname != null) {
        return false;
      }
    } else if (!nickname.equals(other.nickname)) {
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

  public String getCubieId() {
    return cubieId;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public String getNickname() {
    return nickname;
  }

  public String getUid() {
    return uid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (appInstalled ? 1231 : 1237);
    result = prime * result + ((cubieId == null) ? 0 : cubieId.hashCode());
    result = prime * result + ((iconUrl == null) ? 0 : iconUrl.hashCode());
    result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
    result = prime * result + ((uid == null) ? 0 : uid.hashCode());
    return result;
  }

  public boolean isAppInstalled() {
    return appInstalled;
  }

  @Override
  public String toString() {
    return "CubieFriend [uid=" + uid
        + ", cubieId="
        + cubieId
        + ", nickname="
        + nickname
        + ", iconUrl="
        + iconUrl
        + ", appInstalled="
        + appInstalled
        + "]";
  }

}
