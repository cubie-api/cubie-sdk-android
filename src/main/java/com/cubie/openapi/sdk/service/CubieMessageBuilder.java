package com.cubie.openapi.sdk.service;

/**
 * You must call {@link #setNotification(String)} to set the notification text of the message and
 * provide one of the following 4 fields: text, image, link button for a message to be valid
 */
public final class CubieMessageBuilder {
  private static final int IMAGE_WIDTH = 192;
  private static final int IMAGE_HEIGHT = 192;
  private String buttonText;
  private String imageUrl;
  private int imageWidth;
  private int imageHeight;
  private String notification;
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
        buttonAction == null ? null : buttonAction.getIosExecuteParam(),
        notification);
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

  public CubieMessageBuilder setImage(final String imageUrl) {
    this.imageUrl = imageUrl;
    imageWidth = IMAGE_WIDTH;
    imageHeight = IMAGE_HEIGHT;
    return this;
  }

  public CubieMessageBuilder setNotification(String notification) {
    this.notification = notification;
    return this;
  }

  public CubieMessageBuilder setText(final String text) {
    this.text = text;
    return this;
  }
}
