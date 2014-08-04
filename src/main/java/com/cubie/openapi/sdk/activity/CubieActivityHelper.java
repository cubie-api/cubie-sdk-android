package com.cubie.openapi.sdk.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cubie.openapi.sdk.SessionHelper;
import com.cubie.openapi.sdk.SessionHelper.SessionCallback;

public class CubieActivityHelper {

  private final Activity activity;
  private final CubieActivitySessionListener sessionListener;

  private final SessionCallback sessionCallback = new SessionCallback() {
    @Override
    public void onClose() {
      sessionListener.onSessionClose();
    }

    @Override
    public void onOpen() {
      sessionListener.onSessionOpen();
    }
  };

  public CubieActivityHelper(Activity activity, CubieActivitySessionListener sessionListener) {
    this.activity = activity;
    this.sessionListener = sessionListener;
  }

  public void connect() {
    final SessionHelper session = SessionHelper.getSession();
    if (session == null) {
      // connect must be called after init
      return;
    }
    if (session.isOpen()) {
      sessionListener.onSessionOpen();
    } else {
      SessionHelper.getSession().open(activity, sessionCallback);
    }
  }

  public void disconnect() {
    final SessionHelper session = SessionHelper.getSession();
    if (session == null) {
      // disconnect must be called after init
      return;
    }
    if (session.isOpen()) {
      SessionHelper.getSession().close(activity, sessionCallback);
    } else {
      sessionListener.onSessionClose();
    }
  }

  public void onCreate(Bundle savedInstanceState) {
    final SessionHelper session = SessionHelper.getSession();
    if (session != null && session.isOpen()) {
      sessionListener.onSessionOpen();
    }
  }

  public void onResume() {
    SessionHelper.init(activity, sessionCallback);
  }
}