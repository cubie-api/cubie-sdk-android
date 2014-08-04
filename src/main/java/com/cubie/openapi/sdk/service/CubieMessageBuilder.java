package com.cubie.openapi.sdk.service;

public final class CubieMessageBuilder {
  private String buttonText;
  private String imageUrl;
  private int imageWidth;
  private int imageHeight;
  private String text;
  private String linkText;
  private CubieMessageActionParams linkAction;
  private CubieMessageActionParams buttonAction;

  public CubieMessage build() {
    return new CubieMessage(text,
        imageUrl,
        imageWidth,
        imageHeight,
        linkText,
        linkAction == null ? null : linkAction.getAndroidExecuteParam(),
        linkAction == null ? null : linkAction.getAndroidMarketParam(),
        linkAction == null ? null : linkAction.getIosExecuteParam(),
        buttonText,
        buttonAction == null ? null : buttonAction.getAndroidExecuteParam(),
        buttonAction == null ? null : buttonAction.getAndroidMarketParam(),
        buttonAction == null ? null : buttonAction.getIosExecuteParam());
  }

  public CubieMessageBuilder setAppButton(final String buttonText) {
    this.buttonText = buttonText;
    return this;
  }

  public CubieMessageBuilder setAppButton(final String buttonText,
      final CubieMessageActionParams action) {
    buttonAction = action;
    return setAppButton(buttonText);
  }

  public CubieMessageBuilder setAppLink(final String linkText) {
    this.linkText = linkText;
    return this;
  }

  public CubieMessageBuilder setAppLink(final String linkText, final CubieMessageActionParams action) {
    linkAction = action;
    return setAppLink(linkText);
  }

  public CubieMessageBuilder setImage(final String imageUrl,
      final int imageWidth,
      final int imageHeight) {
    this.imageUrl = imageUrl;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    return this;
  }

  public CubieMessageBuilder setText(final String text) {
    this.text = text;
    return this;
  }
}