package com.cubie.openapi.sdk.service;

import org.json.JSONException;
import org.json.JSONObject;

public final class CubieUser {

  public static CubieUser decode(String content) throws JSONException {
    final JSONObject jsonObject = new JSONObject(content);
    return new CubieUser(jsonObject.getString("uid"),
        jsonObject.getString("cubie_id"),
        jsonObject.getString("nickname"),
        jsonObject.getString("icon_url"));
  }

  private final String uid;
  private final String cubieId;
  private final String nickname;
  private final String iconUrl;

  public CubieUser(String uid, String cubieId, String nickname, String iconUrl) {
    this.uid = uid;
    this.cubieId = cubieId;
    this.nickname = nickname;
    this.iconUrl = iconUrl;
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
    final CubieUser other = (CubieUser) obj;
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
    result = prime * result + ((cubieId == null) ? 0 : cubieId.hashCode());
    result = prime * result + ((iconUrl == null) ? 0 : iconUrl.hashCode());
    result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
    result = prime * result + ((uid == null) ? 0 : uid.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "CubieUser [uid=" + uid
        + ", cubieId="
        + cubieId
        + ", nickname="
        + nickname
        + ", iconUrl="
        + iconUrl
        + "]";
  }

}
