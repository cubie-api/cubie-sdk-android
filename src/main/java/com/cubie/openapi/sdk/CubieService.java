package com.cubie.openapi.sdk;

import com.cubie.openapi.sdk.service.CubieFriendList;
import com.cubie.openapi.sdk.service.CubieMessage;
import com.cubie.openapi.sdk.service.CubieSendAck;
import com.cubie.openapi.sdk.service.CubieTransactionRequest;
import com.cubie.openapi.sdk.service.CubieUser;
import com.cubie.openapi.sdk.service.CubieUserAccessToken;

public interface CubieService {

  public static final int DEFAULT_FRIEND_LIST_PAGE_SIZE = 100;

  void createTransaction(CubieTransactionRequest request, final CubieServiceCallback<Void> callback);

  void extendAccessToken(final CubieServiceCallback<CubieUserAccessToken> callback);

  void requestFriends(final CubieFriendList currentFriendListOrNull,
      int pageSize,
      CubieServiceCallback<CubieFriendList> callback);

  void requestMe(final CubieServiceCallback<CubieUser> callback);

  void sendMessage(final String receiverUid,
      final CubieMessage cubieMessage,
      final CubieServiceCallback<CubieSendAck> callback);
}
