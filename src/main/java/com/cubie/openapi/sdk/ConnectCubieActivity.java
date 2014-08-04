package com.cubie.openapi.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cubie.openapi.sdk.internal.intent.ConnectParameters;
import com.cubie.openapi.sdk.internal.intent.CubieIntents;
import com.cubie.openapi.sdk.internal.intent.DisconnectParameters;

public final class ConnectCubieActivity extends Activity {
  enum RequestCode {
    CONNECT, //
    DISCONNECT, //
    ;
  }

  public static Intent createConnectIntent(final Context context,
      final ConnectParameters connectParameter) {
    final Intent intent = new Intent(context, ConnectCubieActivity.class);
    intent.putExtra(ConnectParameters.class.getName(), connectParameter);
    return intent;
  }

  public static Intent createDisconnectIntent(final Context context,
      final DisconnectParameters disconnectParameters) {
    final Intent intent = new Intent(context, ConnectCubieActivity.class);
    intent.putExtra(DisconnectParameters.class.getName(), disconnectParameters);
    return intent;
  }

  private static ConnectParameters extractConnectParameters(final Intent intent) {
    return (ConnectParameters) intent.getSerializableExtra(ConnectParameters.class.getName());
  }

  private static DisconnectParameters extractDisconnectParameters(final Intent intent) {
    return (DisconnectParameters) intent.getSerializableExtra(DisconnectParameters.class.getName());
  }

  @Override
  protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RequestCode.CONNECT.ordinal()) {
      SessionHelper.getSession().handleConnectResult(resultCode, data);
    } else if (requestCode == RequestCode.DISCONNECT.ordinal()) {
      SessionHelper.getSession().handleDisconnectResult(resultCode, data);
    }

    finish();
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (tryConnect()) {
      return;
    }

    if (tryDisconnect()) {
      return;
    }

    throw new IllegalStateException();
  }

  private boolean tryConnect() {
    final ConnectParameters connectParameters = extractConnectParameters(getIntent());
    if (connectParameters == null) {
      return false;
    }

    final Intent connectIntent = CubieIntents.createConnectCubieIntent(this, connectParameters);
    if (connectIntent == null) {
      return false;
    }

    startActivityForResult(connectIntent, RequestCode.CONNECT.ordinal());
    return true;
  }

  private boolean tryDisconnect() {
    final DisconnectParameters disconnectParameters = extractDisconnectParameters(getIntent());
    if (disconnectParameters == null) {
      return false;
    }

    final Intent disconnectIntent = CubieIntents.createDisconnectCubieIntent(this,
        disconnectParameters);
    if (disconnectIntent == null) {
      return false;
    }

    startActivityForResult(disconnectIntent, RequestCode.DISCONNECT.ordinal());
    return true;
  }
}
