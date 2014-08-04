package com.cubie.openapi.sdk.activity;

import android.app.Activity;
import android.os.Bundle;

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

  @Override
  public void onSessionClose() {}

  @Override
  public void onSessionOpen() {}
}
