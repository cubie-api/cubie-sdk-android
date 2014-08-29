package com.cubie.openapi.sdk;

import com.cubie.openapi.sdk.service.CubieFriend;
import com.cubie.openapi.sdk.service.CubieFriendList;
import com.cubie.openapi.sdk.service.CubieMessage;
import com.cubie.openapi.sdk.service.CubieMessageBuilder;
import com.cubie.openapi.sdk.service.CubieSendAck;
import com.cubie.openapi.sdk.service.CubieTransactionRequest;
import com.cubie.openapi.sdk.service.CubieUser;
import com.cubie.openapi.sdk.service.CubieUserAccessToken;

public interface CubieService {

  public static final int DEFAULT_FRIEND_LIST_PAGE_SIZE = 100;

  /**
   * Record a transaction on Cubie's server
   * 
   * @see CubieTransactionRequest
   * @see CubieServiceCallback
   */
  void createTransaction(CubieTransactionRequest request, final CubieServiceCallback<Void> callback);

  /**
   * Extend the expiration date for the current access token. no need to call this manullay because
   * SessionHelper will extend the access token if it's going to expire within a certin period of
   * time.
   * 
   * @see CubieServiceCallback
   * @see CubieUserAccessToken
   * @see SessionHelper
   */
  void extendAccessToken(final CubieServiceCallback<CubieUserAccessToken> callback);

  /**
   * Request a partial list of friends for the current connected user
   * 
   * @param currentFriendListOrNull
   *          Pass null for the first time, after that, pass the friendList
   *          returning from the first callback argument (you may store it in a instance field)
   * @param pageSize
   *          Pass Cubie.DEFAULT_FRIEND_LIST_PAGE_SIZE for or any number less than
   *          DEFAULT_FRIEND_LIST_PAGE_SIZE to control the return size
   * 
   * @see CubieServiceCallback
   * @see CubieFriendList
   * @see CubieFriend
   */
  void requestFriends(final CubieFriendList currentFriendListOrNull,
      int pageSize,
      CubieServiceCallback<CubieFriendList> callback);

  /**
   * Request the basic info of the current connected user
   * 
   * @see CubieServiceCallback
   * @see CubieUser
   */
  void requestMe(final CubieServiceCallback<CubieUser> callback);

  /**
   * Send a message to a friend of the current connected user
   * 
   * @param receiverUid
   *          {@link CubieFriend#getUid()}
   * @param cubieMessage
   *          This message is built using {@link CubieMessageBuilder} please note that notification
   *          is a required field and you must fill one of the following 4 fields: text, image,
   *          link, button
   * 
   * @see CubieServiceCallback
   * @see CubieMessageBuilder
   * @see CubieMessage
   * @see CubieSendAck
   */
  void sendMessage(final String receiverUid,
      final CubieMessage cubieMessage,
      final CubieServiceCallback<CubieSendAck> callback);
}
