package com.cubie.openapi.sdk.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public final class CubieTransactionRequest {
  private final String orderId;

  private final String productId;

  private String currency;

  private final String price;

  private final Date purchaseDate;

  private final Map<String, Object> extra;

  public CubieTransactionRequest(String orderId,
      String productId,
      String price,
      long purchaseDate,
      Map<String, Object> extra) {
    this(orderId, productId, null, price, purchaseDate, extra);
  }

  public CubieTransactionRequest(String orderId,
      String productId,
      String currency,
      String price,
      long purchaseDate,
      Map<String, Object> extra) {
    this.orderId = orderId;
    this.productId = productId;
    this.currency = currency;
    this.price = price;
    this.purchaseDate = new Date(purchaseDate);
    this.extra = extra;
  }

  private String convertExtraToString() {
    if (extra == null) {
      return "";
    }
    String str = "{";
    final Iterator<Entry<String, Object>> entries = extra.entrySet().iterator();
    while (entries.hasNext()) {
      final Entry<String, Object> entry = entries.next();
      str += "{" + entry.getKey() + ":" + entry.getValue() + "}";
    }
    str += "}";
    return str;
  }

  public JSONObject encode() throws JSONException {
    final JSONObject jsonObject = new JSONObject();
    jsonObject.put("purchase_id", orderId);
    jsonObject.put("item_name", productId);
    jsonObject.put("currency", currency);
    jsonObject.put("price", price);
    jsonObject.put("purchase_date",
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US).format(purchaseDate));

    if (extra != null) {
      final JSONObject jObj = new JSONObject();
      final Iterator<Entry<String, Object>> entries = extra.entrySet().iterator();
      while (entries.hasNext()) {
        final Entry<String, Object> entry = entries.next();
        jObj.put(entry.getKey(), entry.getValue());
      }
      jsonObject.put("extra", jObj);
    }
    return jsonObject;
  }

  public String getCurrency() {
    return currency;
  }

  public Map<String, Object> getExtra() {
    return extra;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getPrice() {
    return price;
  }

  public String getProductId() {
    return productId;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public String toString() {
    return "CubieTransactionRequest [orderId=" + orderId
        + ", productId="
        + productId
        + ", currency="
        + currency
        + ", price="
        + price
        + ", purchaseDate="
        + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.US).format(purchaseDate)
        + ", extra="
        + convertExtraToString()
        + "]";
  }
}
