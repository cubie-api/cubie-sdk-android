package com.cubie.openapi.sdk;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;

import com.cubie.openapi.sdk.exception.ConnectCubieActivityNotDefinedException;
import com.cubie.openapi.sdk.exception.CubieAppKeyNotDefinedException;
import com.cubie.openapi.sdk.exception.CubieException;
import com.cubie.openapi.sdk.exception.CubieServiceException;
import com.cubie.openapi.sdk.internal.intent.AccessToken;
import com.cubie.openapi.sdk.internal.intent.ConnectParameters;
import com.cubie.openapi.sdk.internal.intent.CubieIntents;
import com.cubie.openapi.sdk.internal.intent.DisconnectParameters;
import com.cubie.openapi.sdk.internal.util.Contexts;
import com.cubie.openapi.sdk.internal.util.Pref;
import com.cubie.openapi.sdk.service.CubieUserAccessToken;

public final class SessionHelper {

  public interface SessionCallback {
    public void onClose();

    public void onOpen();
  }

  private static void askUserToInstallOrUpdateCubie(final Activity activity) {
    final Intent googlePlayIntent = CubieIntents.createGooglePlayIntentForCubie(activity);
    final String packageName = Contexts.resolvePackageNameForIntent(activity, googlePlayIntent);
    if (packageName == null) {
      Toast.makeText(activity, R.string.error_no_google_play_to_install_cubie, Toast.LENGTH_LONG)
          .show();
      return;
    }

    int errorMsgResId = R.string.cubie_dialog_message_install;
    if (Contexts.existsPackage(activity, CUBIE_PACKAGE_NAME)) {
      errorMsgResId = R.string.cubie_dialog_message_update;
    }

    new AlertDialog.Builder(activity).setMessage(errorMsgResId)
        .setPositiveButton(R.string.cubie_dialog_button_positive, new OnClickListener() {
          @Override
          public void onClick(final DialogInterface dialog, final int which) {
            activity.startActivity(googlePlayIntent);
          }
        })
        .setNegativeButton(R.string.cubie_dialog_button_negative, null)
        .show();
  }

  private static void checkIfAppKeyIsDefined(final Activity activity) {
    if (Contexts.getAppKey(activity) == null) {
      throw new CubieAppKeyNotDefinedException();
    }
  }

  private static void checkIfConnectCubieActivityIsDefined(final Activity activity) {
    final Intent intent = new Intent(activity, ConnectCubieActivity.class);
    final String packageName = Contexts.resolvePackageNameForIntent(activity, intent);
    if (packageName == null) {
      throw new ConnectCubieActivityNotDefinedException();
    }
  }

  /**
   * @return null if {@link SessionHelper#init(Context, SessionCallback)} is not called; otherwise,
   *         current session is returned
   */
  public static SessionHelper getSession() {
    synchronized (STATIC_LOCK) {
      return currentSession;
    }
  }

  /**
   * If a session has already been initialized, nothing will happen ({@link #sessionCallback} is
   * skipped).
   * Otherwise, current session will be initialized by trying to restore a previously opened session
   * (sessionCallback will be called). If the restored session is expiring within 30 days, it will
   * be extended automatically.
   */
  public static void init(final Context context, final SessionCallback sessionCallback) {
    synchronized (STATIC_LOCK) {
      if (currentSession == null) {
        currentSession = new SessionHelper(context);
      }
    }
    currentSession.reopen(sessionCallback);
  }

  private static final String TAG = SessionHelper.class.getSimpleName();

  private static final long MIN_REFRESH_INTERVAL = DateUtils.DAY_IN_MILLIS * 30; // 30 days

  private static final Object STATIC_LOCK = new Object();

  private static SessionHelper currentSession;

  private SessionCallback sessionCallback;

  private AccessToken accessToken;

  private final Object lock = new Object();

  private final Pref pref;

  private static final String CUBIE_PACKAGE_NAME = "com.liquable.nemo";

  private SessionHelper(final Context context) {
    pref = Pref.getInstance(context);
  }

  private void close() {
    synchronized (lock) {
      pref.clearAccessToken();
      accessToken = null;
    }
  }

  /**
   * Call this when the connected user explicitly want to disconnect your app from Cubie
   */
  public void close(final Activity activity, final SessionCallback sessionCallback) {
    this.sessionCallback = sessionCallback;

    if (!isOpen()) {
      notifySessionCallback();
      return;
    }

    final DisconnectParameters disconnectParameters = new DisconnectParameters(Contexts.getAppKey(activity),
        getUid(),
        genAppUniqueDeviceId());
    if (CubieIntents.createDisconnectCubieIntent(activity, disconnectParameters) == null) {
      askUserToInstallOrUpdateCubie(activity);
      return;
    }

    activity.startActivity(ConnectCubieActivity.createDisconnectIntent(activity,
        disconnectParameters));
  }

  private AccessToken extractAccessTokenFromResultIntent(final Intent intent) {
    if (intent == null) {
      return null;
    }

    if (intent.getExtras() == null) {
      return null;
    }

    return AccessToken.fromBundle(intent.getExtras());
  }

  private String genAppUniqueDeviceId() {
    String appUniqueDeviceId = pref.load("appUniqueDeviceId");
    if (appUniqueDeviceId == null) {
      appUniqueDeviceId = UUID.randomUUID().toString();
      pref.store("appUniqueDeviceId", appUniqueDeviceId);
    }
    return appUniqueDeviceId;
  }

  public String getAccessToken() {
    synchronized (lock) {
      if (accessToken == null) {
        return null;
      }
      return accessToken.getAccessToken();
    }
  }

  public Date getExpireTime() {
    synchronized (lock) {
      if (accessToken == null) {
        return null;
      }
      return new Date(accessToken.getExpireTime());
    }
  }

  String getOpenApiAppId() {
    synchronized (lock) {
      if (accessToken == null) {
        return null;
      }
      return accessToken.getOpenApiAppId();
    }
  }

  String getUid() {
    synchronized (lock) {
      if (accessToken == null) {
        return null;
      }
      return accessToken.getUid();
    }
  }

  public void handleConnectResult(final int resultCode, final Intent data) {
    final AccessToken accessTokenFromCubie = extractAccessTokenFromResultIntent(data);
    if (resultCode == Activity.RESULT_OK && accessTokenFromCubie != null) {
      updateAccessToken(accessTokenFromCubie);
    }

    notifySessionCallback();
  }

  public void handleDisconnectResult(int resultCode, Intent data) {
    close();
    notifySessionCallback();
  }

  public boolean isOpen() {
    synchronized (lock) {
      return accessToken != null && accessToken.isValid();
    }
  }

  private boolean needToRefresh() {
    synchronized (lock) {
      return isOpen() && accessToken.getExpireTime() - System.currentTimeMillis() < MIN_REFRESH_INTERVAL;
    }
  }

  private void notifySessionCallback() {
    notifySessionCallback(sessionCallback);
  }

  private void notifySessionCallback(final SessionCallback sessionCallback) {
    if (sessionCallback == null) {
      return;
    }

    if (isOpen()) {
      sessionCallback.onOpen();
    } else {
      sessionCallback.onClose();
    }
  }

  /**
   * If the active session is opened and valid, {@link SessionCallback#onOpen()} will be called
   * immediately;
   * Otherwise, users will be redirected to Cubie app to authorize your app access to their
   * information
   * and permissions to send messages on behalf of them. After users confirm,
   * {@link SessionCallback#onOpen()} will
   * be called.
   */
  public void open(final Activity activity, final SessionCallback sessionCallback) {
    checkIfConnectCubieActivityIsDefined(activity);
    checkIfAppKeyIsDefined(activity);

    this.sessionCallback = sessionCallback;

    if (isOpen()) {
      notifySessionCallback();
      return;
    }

    final ConnectParameters connectParameters = new ConnectParameters(Contexts.getAppKey(activity),
        genAppUniqueDeviceId());
    if (CubieIntents.createConnectCubieIntent(activity, connectParameters) == null) {
      askUserToInstallOrUpdateCubie(activity);
      return;
    }

    activity.startActivity(ConnectCubieActivity.createConnectIntent(activity, connectParameters));
  }

  private void refreshIfNecessary() {
    if (!needToRefresh()) {
      return;
    }

    Cubie.getService().extendAccessToken(new CubieServiceCallback<CubieUserAccessToken>() {
      @Override
      public void done(CubieUserAccessToken token, CubieException e) {
        if (e != null && e instanceof CubieServiceException) { // ignore other type of exceptions
          close();
        } else if (token != null) {
          updateAccessToken(new AccessToken(token.getUid(),
              token.getAppId(),
              token.getAccessToken(),
              token.getExpireDate().getTime()));
        }
      }
    });
  }

  private void reopen(final SessionCallback sessionCallback) {
    if (isOpen()) {
      return;
    }

    AccessToken loadedAccessToken;
    try {
      loadedAccessToken = pref.loadAccessToken();
    } catch (final JSONException e) {
      Log.e(TAG, "failed to restore token from pref", e);
      close();
      notifySessionCallback(sessionCallback);
      return;
    }

    synchronized (lock) {
      accessToken = loadedAccessToken;
    }

    notifySessionCallback(sessionCallback);
    refreshIfNecessary();
  }

  public void testInvalidAccessToken() {
    if (!isOpen()) {
      return;
    }

    updateAccessToken(new AccessToken(accessToken.getUid(),
        accessToken.getOpenApiAppId(),
        accessToken.getAccessToken(),
        System.currentTimeMillis()));
  }

  public void testMakeAccessTokenNeedToRefresh() {
    if (!isOpen()) {
      return;
    }

    updateAccessToken(new AccessToken(accessToken.getUid(),
        accessToken.getOpenApiAppId(),
        accessToken.getAccessToken(),
        System.currentTimeMillis() + DateUtils.HOUR_IN_MILLIS));
  }

  private void updateAccessToken(final AccessToken input) {
    synchronized (lock) {
      accessToken = input;
      pref.storeAccessToken(accessToken);
    }
  }
}
