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
  private final String purchaseId;

  private final String itemName;

  private final String currency;

  private final String price;

  private final Date purchaseDate;

  private final Map<String, Object> extra;

  public CubieTransactionRequest(String purchaseId,
      String itemName,
      String currency,
      String price,
      Date purchaseDate,
      Map<String, Object> extra) {
    this.purchaseId = purchaseId;
    this.itemName = itemName;
    this.currency = currency;
    this.price = price;
    this.purchaseDate = purchaseDate;
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
    jsonObject.put("purchase_id", purchaseId);
    jsonObject.put("item_name", itemName);
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

  public String getItemName() {
    return itemName;
  }

  public String getPrice() {
    return price;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public String getPurchaseId() {
    return purchaseId;
  }

  @Override
  public String toString() {
    return "CubieTransactionRequest [purchaseId=" + purchaseId
        + ", itemName="
        + itemName
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
