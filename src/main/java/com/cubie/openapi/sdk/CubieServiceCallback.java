package com.cubie.openapi.sdk;

import com.cubie.openapi.sdk.exception.CubieException;
import com.cubie.openapi.sdk.service.CubieFriendList;
import com.cubie.openapi.sdk.service.CubieSendAck;
import com.cubie.openapi.sdk.service.CubieUser;
import com.cubie.openapi.sdk.service.CubieUserAccessToken;

public interface CubieServiceCallback<T> {

  /**
   * @param object
   *          The response object depending the type of request you make (
   *          {@link CubieUserAccessToken}, {@link CubieFriendList}, {@link CubieUser},
   *          {@link CubieSendAck})
   * @param e
   *          null if the request is successful
   */
  void done(T object, CubieException e);
}
