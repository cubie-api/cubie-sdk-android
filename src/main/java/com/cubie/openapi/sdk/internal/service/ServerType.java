package com.cubie.openapi.sdk.internal.service;

public enum ServerType {
  PRODUCTION("https://api.cubie.com"), //
  ;

  private String endpoint;

  private ServerType(final String endpoint) {
    this.endpoint = endpoint;
  }

  public String getApiUrl(final String path) {
    return endpoint + path;
  }
}
