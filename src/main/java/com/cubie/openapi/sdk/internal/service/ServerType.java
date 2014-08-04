package com.cubie.openapi.sdk.internal.service;

public enum ServerType {
  PRODUCTION("https://api.cubie.com"), //
  LOCAL("http://192.168.56.1:8553"), //
  ;

  private String endpoint;

  private ServerType(final String endpoint) {
    this.endpoint = endpoint;
  }

  public String getApiUrl(final String path) {
    return endpoint + path;
  }
}
