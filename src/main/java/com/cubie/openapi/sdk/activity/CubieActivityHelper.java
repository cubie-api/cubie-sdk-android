package com.cubie.openapi.sdk.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cubie.openapi.sdk.CubieConnectButton;
import com.cubie.openapi.sdk.SessionHelper;
import com.cubie.openapi.sdk.SessionHelper.SessionCallback;

/**
 * When the activities in your app cannot extend CubieBaseActivity directly, modify your base
 * activity (if you have one) or define one similar to {@link CubieBaseActivity} and call
 * {@link #onCreate(Bundle)} and {@link #onResume()} in your base activity.
 */
public class CubieActivityHelper {

  private final Activity activity;
  private final CubieActivitySessionListener sessionListener;

  private final SessionCallback sessionCallback = new SessionCallback() {

    /**
     * Will be called when user finished disconnecting from Cubie
     */
    @Override
    public void onClose() {
      sessionListener.onSessionClose();
    }

    /**
     * Will be called when user is already connected or just finished connecting to Cubie
     */
    @Override
    public void onOpen() {
      sessionListener.onSessionOpen();
    }
  };

  public CubieActivityHelper(Activity activity, CubieActivitySessionListener sessionListener) {
    this.activity = activity;
    this.sessionListener = sessionListener;
  }

  /**
   * Call this when users click on the {@link CubieConnectButton}, users will be redirected to the
   * authorization activity on Cubie
   */
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

  /**
   * Call this when users want to disconnect from your app from Cubie
   */
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