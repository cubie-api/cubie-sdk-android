package com.cubie.openapi.sdk.service;

import org.json.JSONException;
import org.json.JSONObject;

public final class CubieSendAck {

  public static CubieSendAck decode(String content) throws JSONException {
    final JSONObject jsonObject = new JSONObject(content);
    return new CubieSendAck(jsonObject.getString("message_id"));
  }

  private final String messageId;

  public CubieSendAck(String messageId) {
    this.messageId = messageId;
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
    final CubieSendAck other = (CubieSendAck) obj;
    if (messageId == null) {
      if (other.messageId != null) {
        return false;
      }
    } else if (!messageId.equals(other.messageId)) {
      return false;
    }
    return true;
  }

  public String getMessageId() {
    return messageId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((messageId == null) ? 0 : messageId.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "CubieSendAck [messageId=" + messageId + "]";
  }

}
