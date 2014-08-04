package com.cubie.openapi.sdk.internal.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.cubie.openapi.sdk.CubieServiceCallback;
import com.cubie.openapi.sdk.service.CubieFriend;
import com.cubie.openapi.sdk.service.CubieFriendList;

public class CubieFriendListCallback extends AbstractCubieServiceCallback<CubieFriendList> {

  private final CubieFriendList friendList;

  public CubieFriendListCallback(CubieFriendList friendList,
      CubieServiceCallback<CubieFriendList> callback) {
    super(callback);
    this.friendList = friendList;
  }

  private CubieFriendList decode(String content) throws JSONException {
    boolean hasMore = friendList.hasMore();
    final List<CubieFriend> allFriends = new ArrayList<CubieFriend>(friendList.getAllFriends());
    final List<CubieFriend> newFriends = new ArrayList<CubieFriend>();
    final JSONArray array = new JSONArray(content);
    for (int i = 0; i < array.length(); ++i) {
      final CubieFriend cubieFriend = CubieFriend.decode(array.getJSONObject(i));
      if (!allFriends.contains(cubieFriend)) {
        newFriends.add(cubieFriend);
        allFriends.add(cubieFriend);
      }
    }
    if (newFriends.isEmpty()) {
      hasMore = false;
    }

    final boolean immutableHasMore = hasMore;

    return new CubieFriendList() {

      @Override
      public List<CubieFriend> getAllFriends() {
        return allFriends;
      }

      @Override
      public boolean hasMore() {
        return immutableHasMore;
      }
    };
  }

  @Override
  public void onSuccess(String content) {
    try {
      callback.done(decode(content), null);
    } catch (final JSONException e) {
      new RuntimeException(e);
    }
  }

}
