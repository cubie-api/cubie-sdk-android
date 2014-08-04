package com.cubie.openapi.sdk.exception;

public final class ConnectCubieActivityNotDefinedException extends CubieException {
  private static final long serialVersionUID = 3833543701706070430L;

  private static final String ACTIVITY_DEFINITION = "" //
      + "<activity android:name=\"com.cubie.openapi.sdk.ConnectCubieActivity\" />\n";

  @Override
  public String toString() {
    return "must define ConnectCubieActivity in AndroidManifest.xml:\n" + ACTIVITY_DEFINITION
        + "\n"
        + super.toString();
  }
}
