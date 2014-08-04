package com.cubie.openapi.sdk.internal.intent;

import java.io.Serializable;

import android.os.Bundle;

public class ConnectParameters implements Serializable {
  public static ConnectParameters fromBundle(final Bundle bundle) {
    return new ConnectParameters(bundle.getString(KEY_APP_KEY),
        bundle.getString(KEY_APP_UNIQUE_DEVICE_ID));
  }

  private static final long serialVersionUID = 2145345379391557518L;
  private static final String KEY_APP_KEY = "com.cubie.openapi.sdk.model.ConnectParameters.appKey";
  private static final String KEY_APP_UNIQUE_DEVICE_ID = "com.cubie.openapi.sdk.model.ConnectParameters.appUniqueDeviceId";

  private final String appKey;
  private final String appUniqueDeviceId;

  public ConnectParameters(final String appKey, final String appUniqueDeviceId) {
    this.appKey = appKey;
    this.appUniqueDeviceId = appUniqueDeviceId;
  }

  public String getAppKey() {
    return appKey;
  }

  public String getAppUniqueDeviceId() {
    return appUniqueDeviceId;
  }

  public Bundle toBundle() {
    final Bundle bundle = new Bundle();
    bundle.putString(KEY_APP_KEY, appKey);
    bundle.putString(KEY_APP_UNIQUE_DEVICE_ID, appUniqueDeviceId);
    return bundle;
  }

  @Override
  public String toString() {
    return "ConnectParameters [appKey=" + appKey + ", appUniqueDeviceId=" + appUniqueDeviceId + "]";
  }

}
