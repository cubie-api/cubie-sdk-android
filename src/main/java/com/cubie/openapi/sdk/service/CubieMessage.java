package com.cubie.openapi.sdk.service;

import com.cubie.openapi.sdk.internal.util.Strings;

public final class CubieMessage {
  private final String text;
  private final String imageUrl;
  private final int imageWidth;
  private final int imageHeight;
  private final String linkText;
  private final String linkAndroidExecuteParam;
  private final String linkAndroidMarketParam;
  private final String linkIosExecuteParam;
  private final String buttonText;
  private final String buttonAndroidExecuteParam;
  private final String buttonAndroidMarketParam;
  private final String buttonIosExecuteParam;
  private final String notification;

  public CubieMessage(final String text,
      final String imageUrl,
      final int imageWidth,
      final int imageHeight,
      final String linkText,
      final String linkAndroidExecuteParam,
      final String linkAndroidMarketParam,
      final String linkIosExecuteParam,
      final String buttonText,
      final String buttonAndroidExecuteParam,
      final String buttonAndroidMarketParam,
      final String buttonIosExecuteParam,
      final String notification) {
    this.text = text;
    this.imageUrl = imageUrl;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
    this.linkText = linkText;
    this.linkAndroidExecuteParam = linkAndroidExecuteParam;
    this.linkAndroidMarketParam = linkAndroidMarketParam;
    this.linkIosExecuteParam = linkIosExecuteParam;
    this.buttonText = buttonText;
    this.buttonAndroidExecuteParam = buttonAndroidExecuteParam;
    this.buttonAndroidMarketParam = buttonAndroidMarketParam;
    this.buttonIosExecuteParam = buttonIosExecuteParam;
    this.notification = notification;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final CubieMessage other = (CubieMessage) obj;
    if (buttonAndroidExecuteParam == null) {
      if (other.buttonAndroidExecuteParam != null) {
        return false;
      }
    } else if (!buttonAndroidExecuteParam.equals(other.buttonAndroidExecuteParam)) {
      return false;
    }
    if (buttonAndroidMarketParam == null) {
      if (other.buttonAndroidMarketParam != null) {
        return false;
      }
    } else if (!buttonAndroidMarketParam.equals(other.buttonAndroidMarketParam)) {
      return false;
    }
    if (buttonIosExecuteParam == null) {
      if (other.buttonIosExecuteParam != null) {
        return false;
      }
    } else if (!buttonIosExecuteParam.equals(other.buttonIosExecuteParam)) {
      return false;
    }
    if (buttonText == null) {
      if (other.buttonText != null) {
        return false;
      }
    } else if (!buttonText.equals(other.buttonText)) {
      return false;
    }
    if (imageHeight != other.imageHeight) {
      return false;
    }
    if (imageUrl == null) {
      if (other.imageUrl != null) {
        return false;
      }
    } else if (!imageUrl.equals(other.imageUrl)) {
      return false;
    }
    if (imageWidth != other.imageWidth) {
      return false;
    }
    if (linkAndroidExecuteParam == null) {
      if (other.linkAndroidExecuteParam != null) {
        return false;
      }
    } else if (!linkAndroidExecuteParam.equals(other.linkAndroidExecuteParam)) {
      return false;
    }
    if (linkAndroidMarketParam == null) {
      if (other.linkAndroidMarketParam != null) {
        return false;
      }
    } else if (!linkAndroidMarketParam.equals(other.linkAndroidMarketParam)) {
      return false;
    }
    if (linkIosExecuteParam == null) {
      if (other.linkIosExecuteParam != null) {
        return false;
      }
    } else if (!linkIosExecuteParam.equals(other.linkIosExecuteParam)) {
      return false;
    }
    if (linkText == null) {
      if (other.linkText != null) {
        return false;
      }
    } else if (!linkText.equals(other.linkText)) {
      return false;
    }
    if (notification == null) {
      if (other.notification != null) {
        return false;
      }
    } else if (!notification.equals(other.notification)) {
      return false;
    }
    if (text == null) {
      if (other.text != null) {
        return false;
      }
    } else if (!text.equals(other.text)) {
      return false;
    }
    return true;
  }

  public String getButtonAndroidExecuteParam() {
    return buttonAndroidExecuteParam;
  }

  public String getButtonAndroidMarketParam() {
    return buttonAndroidMarketParam;
  }

  public String getButtonIosExecuteParam() {
    return buttonIosExecuteParam;
  }

  public String getButtonText() {
    return buttonText;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public int getImageWidth() {
    return imageWidth;
  }

  public String getLinkAndroidExecuteParam() {
    return linkAndroidExecuteParam;
  }

  public String getLinkAndroidMarketParam() {
    return linkAndroidMarketParam;
  }

  public String getLinkIosExecuteParam() {
    return linkIosExecuteParam;
  }

  public String getLinkText() {
    return linkText;
  }

  public String getNotification() {
    return notification;
  }

  public String getText() {
    return text;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((buttonAndroidExecuteParam == null) ? 0 : buttonAndroidExecuteParam.hashCode());
    result = prime * result
        + ((buttonAndroidMarketParam == null) ? 0 : buttonAndroidMarketParam.hashCode());
    result = prime * result
        + ((buttonIosExecuteParam == null) ? 0 : buttonIosExecuteParam.hashCode());
    result = prime * result + ((buttonText == null) ? 0 : buttonText.hashCode());
    result = prime * result + imageHeight;
    result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
    result = prime * result + imageWidth;
    result = prime * result
        + ((linkAndroidExecuteParam == null) ? 0 : linkAndroidExecuteParam.hashCode());
    result = prime * result
        + ((linkAndroidMarketParam == null) ? 0 : linkAndroidMarketParam.hashCode());
    result = prime * result + ((linkIosExecuteParam == null) ? 0 : linkIosExecuteParam.hashCode());
    result = prime * result + ((linkText == null) ? 0 : linkText.hashCode());
    result = prime * result + ((notification == null) ? 0 : notification.hashCode());
    result = prime * result + ((text == null) ? 0 : text.hashCode());
    return result;
  }

  public boolean isEmpty() {
    return Strings.isBlank(notification) || Strings.isBlank(linkText)
        && Strings.isBlank(buttonText)
        && Strings.isBlank(text)
        && Strings.isBlank(imageUrl);
  }

  @Override
  public String toString() {
    return "CubieMessage [text=" + text
        + ", imageUrl="
        + imageUrl
        + ", imageWidth="
        + imageWidth
        + ", imageHeight="
        + imageHeight
        + ", linkText="
        + linkText
        + ", linkAndroidExecuteParam="
        + linkAndroidExecuteParam
        + ", linkAndroidMarketParam="
        + linkAndroidMarketParam
        + ", linkIosExecuteParam="
        + linkIosExecuteParam
        + ", buttonText="
        + buttonText
        + ", buttonAndroidExecuteParam="
        + buttonAndroidExecuteParam
        + ", buttonAndroidMarketParam="
        + buttonAndroidMarketParam
        + ", buttonIosExecuteParam="
        + buttonIosExecuteParam
        + ", notification="
        + notification
        + "]";
  }
}
