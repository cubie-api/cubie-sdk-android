package com.cubie.openapi.sdk.service;

import java.util.List;

public interface CubieFriendList {
  List<CubieFriend> getAllFriends();

  boolean hasMore();
}
