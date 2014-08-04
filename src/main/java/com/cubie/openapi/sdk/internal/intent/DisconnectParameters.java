package com.cubie.openapi.sdk.internal.intent;

import java.io.Serializable;

import android.os.Bundle;

public class DisconnectParameters implements Serializable {
  public static DisconnectParameters fromBundle(final Bundle bundle) {
    return new DisconnectParameters(bundle.getString(KEY_APP_KEY),
        bundle.getString(KEY_UID),
        bundle.getString(KEY_APP_UNIQUE_DEVICE_ID));
  }

  private static final long serialVersionUID = 2145345379391557518L;
  private static final String KEY_APP_KEY = "com.cubie.openapi.sdk.model.DisconnectParameters.appKey";
  private static final String KEY_UID = "com.cubie.openapi.sdk.model.DisconnectParameters.uid";
  private static final String KEY_APP_UNIQUE_DEVICE_ID = "com.cubie.openapi.sdk.model.DisconnectParameters.appUniqueDeviceId";

  private final String appKey;
  private final String uid;
  private final String appUniqueDeviceId;

  public DisconnectParameters(String appKey, String myUid, String appUniqueDeviceId) {
    this.appKey = appKey;
    uid = myUid;
    this.appUniqueDeviceId = appUniqueDeviceId;
  }

  public String getAppKey() {
    return appKey;
  }

  public String getAppUniqueDeviceId() {
    return appUniqueDeviceId;
  }

  public String getUid() {
    return uid;
  }

  public Bundle toBundle() {
    final Bundle bundle = new Bundle();
    bundle.putString(KEY_APP_KEY, appKey);
    bundle.putString(KEY_UID, uid);
    bundle.putString(KEY_APP_UNIQUE_DEVICE_ID, appUniqueDeviceId);
    return bundle;
  }

  @Override
  public String toString() {
    return "DisconnectParameters [appKey=" + appKey
        + ", uid="
        + uid
        + ", appUniqueDeviceId="
        + appUniqueDeviceId
        + "]";
  }
}
