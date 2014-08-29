package com.cubie.openapi.sdk.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cubie.openapi.sdk.CubieConnectButton;

public abstract class CubieBaseActivity extends Activity implements CubieActivitySessionListener {

  protected CubieActivityHelper helper;

  public void connect() {
    helper.connect();
  }

  public void disconnect() {
    helper.disconnect();
  }

  protected abstract void onBaseCreate(Bundle savedInstanceState);

  protected void onBaseResume() {}

  @Override
  protected final void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    helper = new CubieActivityHelper(this, this);
    onBaseCreate(savedInstanceState);
    helper.onCreate(savedInstanceState);
  }

  @Override
  protected final void onResume() {
    super.onResume();
    onBaseResume();
    helper.onResume();
  }

  /**
   * The user has disconnected Cubie from your app, you should redirect him/her the launching
   * activity with a {@link CubieConnectButton} for him/her to connect again.
   */
  @Override
  public void onSessionClose() {}

  /**
   * If this method is called on your connect screen, the user has connected with Cubie so you can
   * redirect
   * him/her to your main activity.
   */
  @Override
  public void onSessionOpen() {}
}
