package com.cubie.openapi.sdk.service;

public final class CubieMessageActionParams {
  private String androidExecuteParam;
  private String androidMarketParam;
  private String iosExecuteParam;

  public CubieMessageActionParams() {}

  public CubieMessageActionParams(final String androidExecuteParam,
      final String androidMarketParam,
      final String iosExecuteParam) {
    this.androidExecuteParam = androidExecuteParam;
    this.androidMarketParam = androidMarketParam;
    this.iosExecuteParam = iosExecuteParam;
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
    final CubieMessageActionParams other = (CubieMessageActionParams) obj;
    if (androidExecuteParam == null) {
      if (other.androidExecuteParam != null) {
        return false;
      }
    } else if (!androidExecuteParam.equals(other.androidExecuteParam)) {
      return false;
    }
    if (androidMarketParam == null) {
      if (other.androidMarketParam != null) {
        return false;
      }
    } else if (!androidMarketParam.equals(other.androidMarketParam)) {
      return false;
    }
    if (iosExecuteParam == null) {
      if (other.iosExecuteParam != null) {
        return false;
      }
    } else if (!iosExecuteParam.equals(other.iosExecuteParam)) {
      return false;
    }
    return true;
  }

  public String getAndroidExecuteParam() {
    return androidExecuteParam;
  }

  public String getAndroidMarketParam() {
    return androidMarketParam;
  }

  public String getIosExecuteParam() {
    return iosExecuteParam;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((androidExecuteParam == null) ? 0 : androidExecuteParam.hashCode());
    result = prime * result + ((androidMarketParam == null) ? 0 : androidMarketParam.hashCode());
    result = prime * result + ((iosExecuteParam == null) ? 0 : iosExecuteParam.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "CubieMessageActionParams [androidExecuteParam=" + androidExecuteParam
        + ", androidMarketParam="
        + androidMarketParam
        + ", iosExecuteParam="
        + iosExecuteParam
        + "]";
  }

  public CubieMessageActionParams withAndroidExecuteParam(final String executeParam) {
    androidExecuteParam = executeParam;
    return this;
  }

  public CubieMessageActionParams withAndroidMarketParam(final String marketParam) {
    androidMarketParam = marketParam;
    return this;
  }

  public CubieMessageActionParams withExecuteParam(final String executeParam) {
    androidExecuteParam = executeParam;
    iosExecuteParam = executeParam;
    return this;
  }

  public CubieMessageActionParams withIosExecuteParam(final String executeParam) {
    iosExecuteParam = executeParam;
    return this;
  }

  public CubieMessageActionParams withMarketParam(final String marketParam) {
    androidMarketParam = marketParam;
    return this;
  }

}
