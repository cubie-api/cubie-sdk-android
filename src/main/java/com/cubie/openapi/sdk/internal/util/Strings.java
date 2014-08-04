package com.cubie.openapi.sdk.internal.util;

public class Strings {
  public static boolean isBlank(final CharSequence cs) {
    int strLen;
    if (cs == null || (strLen = cs.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if (Character.isWhitespace(cs.charAt(i)) == false) {
        return false;
      }
    }
    return true;
  }

  public static String trimToEmpty(final String str) {
    return str == null ? EMPTY : str.trim();
  }

  public static final String EMPTY = "";
}
