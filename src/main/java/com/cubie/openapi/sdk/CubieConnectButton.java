package com.cubie.openapi.sdk;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CubieConnectButton extends Button {

  public CubieConnectButton(Context context) {
    super(context);
    init();
  }

  public CubieConnectButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CubieConnectButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  private void init() {
    setBackgroundResource(R.drawable.cubie_connect_button);
    setText(R.string.connect_with_cubie);
  }

}
