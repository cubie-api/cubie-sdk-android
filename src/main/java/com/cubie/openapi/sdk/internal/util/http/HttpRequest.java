package com.cubie.openapi.sdk.internal.util.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.cubie.openapi.sdk.internal.util.Strings;

public class HttpRequest {

  public static HttpRequest Get(final String url) {
    return new HttpRequest("GET", url, null);
  }

  public static HttpRequest Get(final String url, final Map<String, String> params) {
    final List<NameValuePair> pairs = new ArrayList<NameValuePair>();
    for (final Map.Entry<String, String> param : params.entrySet()) {
      pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
    }
    final String query = URLEncodedUtils.format(pairs, "UTF-8");
    return Get(url + "?" + query);
  }

  public static HttpRequest Post(final String url) {
    return Post(url, null);
  }

  public static HttpRequest Post(final String url, final String postBody) {
    return new HttpRequest("POST", url, postBody);
  }

  private static final int DEFAULT_CONNECT_TIMEOUT = 10000;

  private static final int DEFAULT_READ_TIMEOUT = 10000;

  private final String url;
  private final String method;
  private final String postBody;
  private int readTimeout;
  private int connectTimeout;
  private final Map<String, String> headers;

  public HttpRequest(final String method, final String url, final String postBody) {
    this.method = method;
    this.url = url;
    this.postBody = postBody;
    connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    readTimeout = DEFAULT_READ_TIMEOUT;
    headers = new HashMap<String, String>();
  }

  public HttpRequest addHeader(final String key, final String value) {
    headers.put(key, value);
    return this;
  }

  public int getConnectTimeout() {
    return connectTimeout;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public String getMethod() {
    return method;
  }

  public String getPostBody() {
    return postBody;
  }

  public int getReadTimeout() {
    return readTimeout;
  }

  public String getUrl() {
    return url;
  }

  public boolean isPosting() {
    return "POST".equals(method) && !Strings.isBlank(postBody);
  }

  public HttpRequest setConnectTimeout(final int connectTimeout) {
    this.connectTimeout = connectTimeout;
    return this;
  }

  public HttpRequest setReadTimeout(final int readTimeout) {
    this.readTimeout = readTimeout;
    return this;
  }
}
