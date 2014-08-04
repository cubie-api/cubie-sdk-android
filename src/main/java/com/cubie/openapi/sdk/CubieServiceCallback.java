package com.cubie.openapi.sdk;

import com.cubie.openapi.sdk.exception.CubieException;

public interface CubieServiceCallback<T> {

  void done(T object, CubieException e);
}
