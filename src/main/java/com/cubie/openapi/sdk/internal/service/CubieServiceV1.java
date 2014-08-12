package com.cubie.openapi.sdk.internal.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.cubie.openapi.sdk.CubieService;
import com.cubie.openapi.sdk.CubieServiceCallback;
import com.cubie.openapi.sdk.SessionHelper;
import com.cubie.openapi.sdk.exception.CubieException;
import com.cubie.openapi.sdk.exception.CubieMessageEmptyException;
import com.cubie.openapi.sdk.internal.util.http.HttpRequest;
import com.cubie.openapi.sdk.internal.util.http.HttpTask;
import com.cubie.openapi.sdk.service.CubieFriend;
import com.cubie.openapi.sdk.service.CubieFriendList;
import com.cubie.openapi.sdk.service.CubieMessage;
import com.cubie.openapi.sdk.service.CubieMessageRequest;
import com.cubie.openapi.sdk.service.CubieSendAck;
import com.cubie.openapi.sdk.service.CubieTransactionRequest;
import com.cubie.openapi.sdk.service.CubieUser;
import com.cubie.openapi.sdk.service.CubieUserAccessToken;

public class CubieServiceV1 implements CubieService {

  static class CubieHttpTask<T> extends HttpTask {
    public CubieHttpTask(final AbstractCubieServiceCallback<T> callback) {
      super(callback);
    }
  }

  private static final ServerType SERVER_TYPE = ServerType.PRODUCTION;

  private static final String CUBIE_ACCESS_TOKEN_HEADER_KEY = "X-CUBIE-ACCESS-TOKEN";

  private static CubieFriendList EMPTY_FRIEND_LIST = new CubieFriendList() {

    @Override
    public List<CubieFriend> getAllFriends() {
      return Collections.emptyList();
    }

    @Override
    public boolean hasMore() {
      return true;
    }
  };

  @Override
  public void createTransaction(CubieTransactionRequest request,
      final CubieServiceCallback<Void> callback) {
    final SessionHelper session = SessionHelper.getSession();
    if (session == null) {
      throw new IllegalStateException("session must not be null");
    }

    final String url = SERVER_TYPE.getApiUrl("/v1/api/transaction/create");
    try {
      final HttpRequest httpRequest = HttpRequest.Post(url, request.encode().toString())
          .addHeader(CUBIE_ACCESS_TOKEN_HEADER_KEY, session.getAccessToken())
          .addHeader("Content-Type", "application/json");
      new CubieHttpTask<Void>(new CubieTransactionCallback(callback)).execute(httpRequest);
    } catch (final JSONException e) {
      throw new CubieException("CubieTransactionRequest encode to JSON fails", e);
    }
  }

  @Override
  public void extendAccessToken(final CubieServiceCallback<CubieUserAccessToken> callback) {
    final SessionHelper session = SessionHelper.getSession();
    if (session == null) {
      throw new IllegalStateException("session must not be null");
    }

    final String url = SERVER_TYPE.getApiUrl("/v1/api/user/extend-access-token");
    final HttpRequest httpRequest = HttpRequest.Post(url) //
        .addHeader(CUBIE_ACCESS_TOKEN_HEADER_KEY, session.getAccessToken());
    new CubieHttpTask<CubieUserAccessToken>(new CubieUserAccessTokenCallback(callback)).execute(httpRequest);
  }

  @Override
  public void requestFriends(final CubieFriendList currentFriendListOrNull,
      int pageSize,
      CubieServiceCallback<CubieFriendList> callback) {
    final SessionHelper session = SessionHelper.getSession();
    if (session == null) {
      throw new IllegalStateException("session must not be null");
    }

    final CubieFriendList currentFriendList = currentFriendListOrNull == null ? EMPTY_FRIEND_LIST
        : currentFriendListOrNull;
    final Map<String, String> params = new HashMap<String, String>();
    params.put("size", String.valueOf(pageSize));
    final List<CubieFriend> friends = currentFriendList.getAllFriends();
    if (!friends.isEmpty()) {
      params.put("start-friend-uid", friends.get(friends.size() - 1).getUid());
    }

    final String url = SERVER_TYPE.getApiUrl("/v1/api/friend/list");
    final HttpRequest httpRequest = HttpRequest.Get(url, params) //
        .addHeader(CUBIE_ACCESS_TOKEN_HEADER_KEY, session.getAccessToken());
    new CubieHttpTask<CubieFriendList>(new CubieFriendListCallback(currentFriendList, callback)).execute(httpRequest);
  }

  @Override
  public void requestMe(final CubieServiceCallback<CubieUser> callback) {
    final SessionHelper session = SessionHelper.getSession();
    if (session == null) {
      throw new IllegalStateException("session must not be null");
    }

    final String url = SERVER_TYPE.getApiUrl("/v1/api/user/me");
    final HttpRequest httpRequest = HttpRequest.Get(url).addHeader(CUBIE_ACCESS_TOKEN_HEADER_KEY,
        session.getAccessToken());
    new CubieHttpTask<CubieUser>(new CubieUserCallback(callback)).execute(httpRequest);
  }

  @Override
  public void sendMessage(final String receiverUid,
      final CubieMessage cubieMessage,
      final CubieServiceCallback<CubieSendAck> callback) {
    final SessionHelper session = SessionHelper.getSession();
    if (session == null) {
      throw new IllegalStateException("session must not be null");
    }

    if (cubieMessage.isEmpty()) {
      throw new CubieMessageEmptyException();
    }

    final String url = SERVER_TYPE.getApiUrl("/v1/api/chat/app-message");
    try {
      final HttpRequest httpRequest = HttpRequest.Post(url,
          new CubieMessageRequest(receiverUid, cubieMessage).encode().toString())
          .addHeader(CUBIE_ACCESS_TOKEN_HEADER_KEY, session.getAccessToken())
          .addHeader("Content-Type", "application/json");
      new CubieHttpTask<CubieSendAck>(new CubieSendMessageCallback(callback)).execute(httpRequest);
    } catch (final JSONException e) {
      throw new CubieException("CubieMessage encode to JSON fails", e);
    }
  }
}
