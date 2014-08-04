package com.cubie.openapi.sdk;

import android.content.Intent;
import android.net.Uri;

import com.cubie.openapi.sdk.internal.service.CubieServiceV1;
import com.cubie.openapi.sdk.internal.util.Strings;

public final class Cubie {

  /**
   * @return a CubieService implementation
   */
  public static CubieService getService() {
    if (service == null) {
      service = new CubieServiceV1();
    }
    return service;
  }

  /**
   * Specify execute parameters when sending a message:
   * 
   * <pre>
   * cubieMessageBuilder.setAppButton(&quot;go to app&quot;,
   *     new CubieMessageActionParams().withExecuteParam(&quot;msg=hello&quot;));
   * </pre>
   * 
   * When a user taps on a button with execute parameters and go to your app, the following code can
   * be used to extract them:
   * 
   * <pre>
   * Cubie.resolveExecuteParams(getIntent())"
   * </pre>
   * 
   * The code above will return "msg=hello"
   * 
   * @see Cubie#resolveExecuteParams(Intent, String)
   */
  public static String resolveExecuteParams(final Intent intent) {
    if (intent == null) {
      return null;
    }

    final Uri data = intent.getData();
    if (data == null) {
      return null;
    }

    return data.getEncodedQuery();
  }

  /**
   * Similar to {@link Cubie#resolveExecuteParams(Intent)}, but a key can be specified to extract
   * only the value
   * of the execute parameters. Message sent using
   * 
   * <pre>
   * CubieMessageActionParams().withExecuteParam(&quot;msg=hello%20world&quot;)
   * </pre>
   * 
   * The following code will return "hello world"
   * 
   * <pre>
   * Cubie.resolveExecuteParams(getIntent())
   * </pre>
   */
  public static String resolveExecuteParams(final Intent intent, final String key) {
    final String query = resolveExecuteParams(intent);
    if (Strings.isBlank(query)) {
      return null;
    }

    if (query.indexOf(key + "=") == -1) {
      return null;
    }
    return intent.getData().getQueryParameter(key);
  }

  private static CubieService service = null;

}
