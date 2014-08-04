package com.cubie.openapi.sdk.service;

import org.json.JSONException;
import org.json.JSONObject;

public final class CubieMessageRequest {

  private final String receiverUid;
  private final CubieMessage cubieMessage;

  public CubieMessageRequest(String receiverUid, CubieMessage cubieMessage) {
    this.receiverUid = receiverUid;
    this.cubieMessage = cubieMessage;
  }

  public JSONObject encode() throws JSONException {
    final JSONObject jsonObject = new JSONObject();
    jsonObject.put("receiver_uid", receiverUid);
    jsonObject.put("text", cubieMessage.getText());
    jsonObject.put("image_url", cubieMessage.getImageUrl());
    jsonObject.put("image_width", cubieMessage.getImageWidth());
    jsonObject.put("image_height", cubieMessage.getImageHeight());
    jsonObject.put("link_text", cubieMessage.getLinkText());
    jsonObject.put("link_android_execute_param", cubieMessage.getLinkAndroidExecuteParam());
    jsonObject.put("link_android_market_param", cubieMessage.getLinkAndroidMarketParam());
    jsonObject.put("link_ios_execute_param", cubieMessage.getLinkIosExecuteParam());
    jsonObject.put("button_text", cubieMessage.getButtonText());
    jsonObject.put("button_android_execute_param", cubieMessage.getButtonAndroidExecuteParam());
    jsonObject.put("button_android_market_param", cubieMessage.getButtonAndroidMarketParam());
    jsonObject.put("button_ios_execute_param", cubieMessage.getButtonIosExecuteParam());
    return jsonObject;
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
    final CubieMessageRequest other = (CubieMessageRequest) obj;
    if (cubieMessage == null) {
      if (other.cubieMessage != null) {
        return false;
      }
    } else if (!cubieMessage.equals(other.cubieMessage)) {
      return false;
    }
    if (receiverUid == null) {
      if (other.receiverUid != null) {
        return false;
      }
    } else if (!receiverUid.equals(other.receiverUid)) {
      return false;
    }
    return true;
  }

  public CubieMessage getCubieMessage() {
    return cubieMessage;
  }

  public String getReceiverUid() {
    return receiverUid;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cubieMessage == null) ? 0 : cubieMessage.hashCode());
    result = prime * result + ((receiverUid == null) ? 0 : receiverUid.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "CubieMessageRequest [receiverUid=" + receiverUid
        + ", cubieMessage="
        + cubieMessage
        + "]";
  }

}
