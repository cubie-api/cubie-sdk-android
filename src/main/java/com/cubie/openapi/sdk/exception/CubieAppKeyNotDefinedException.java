package com.cubie.openapi.sdk.exception;

public final class CubieAppKeyNotDefinedException extends CubieException {
  private static final long serialVersionUID = 3833543701706070430L;

  private static final String ACTIVITY_DEFINITION = "" //
      + "<meta-data android:name=\"com.cubie.openapi.sdk.AppKey\" android:value=\"@string/cubie_app_key\" />\n";

  @Override
  public String toString() {
    return "must define app key in AndroidManifest.xml:\n" + ACTIVITY_DEFINITION
        + "\n"
        + super.toString();
  }
}
