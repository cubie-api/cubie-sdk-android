package com.cubie.openapi.sdk.exception;

public final class CubieNoActivityHandlingReturnUrlDefinedException extends CubieException {
  private static final long serialVersionUID = 8913161997757024540L;

  private static final String INTENT_FILTER_EXAMPLE = "" //
      + "<intent-filter>\n" //
      + "    <action android:name=\"android.intent.action.VIEW\" />\n"//
      + "    <category android:name=\"android.intent.category.DEFAULT\" />\n"//
      + "    <category android:name=\"android.intent.category.BROWSABLE\" />\n"//
      + "    <data android:host=\"@string/cubie_return_url_host\" android:scheme=\"cubie-%s\" />\n"//
      + "</intent-filter>\n";

  private final String appKey;

  public CubieNoActivityHandlingReturnUrlDefinedException(final String appKey) {
    super();
    this.appKey = appKey;
  }

  @Override
  public String toString() {
    return "must define an activity with this intent filter in AndroidManifest.xml:\n" + String.format(INTENT_FILTER_EXAMPLE,
        appKey)
        + "\n"
        + super.toString();
  }
}
