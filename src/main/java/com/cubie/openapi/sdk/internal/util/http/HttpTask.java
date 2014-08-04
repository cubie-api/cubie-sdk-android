package com.cubie.openapi.sdk.internal.util.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map.Entry;

import android.os.AsyncTask;
import android.util.Log;

import com.cubie.openapi.sdk.internal.util.Strings;

public class HttpTask extends AsyncTask<HttpRequest, Void, HttpResponse> {

  private static final String TAG = HttpTask.class.getSimpleName();

  private final HttpCallback callback;

  private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

  public HttpTask(final HttpCallback callback) {
    this.callback = callback;
  }

  private void closeQuietly(final Closeable closeable) {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (final IOException ioe) {
      // ignore
    }
  }

  private long copy(final InputStream input, final Writer output) throws IOException {
    return copyLarge(new InputStreamReader(new BufferedInputStream(input)), output);
  }

  private long copyLarge(final Reader input, final Writer output) throws IOException {
    final char[] buffer = new char[DEFAULT_BUFFER_SIZE];
    long count = 0;
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
      count += n;
    }
    return count;
  }

  private void disconnectQuitely(final HttpURLConnection conn) {
    if (conn != null) {
      conn.disconnect();
    }
  }

  @Override
  protected HttpResponse doInBackground(final HttpRequest... params) {
    return executeHttpRequest(params[0]);
  }

  private HttpResponse executeHttpRequest(final HttpRequest request) {
    HttpURLConnection conn = null;
    InputStream input = null;
    OutputStream output = null;
    final StringWriter result = new StringWriter();

    try {
      try {
        Log.d(TAG, "executeHttpRequest:" + request.getUrl());
        final URL url = new URL(request.getUrl());
        conn = (HttpURLConnection) url.openConnection();
        if (request.isPosting()) {
          conn.setDoOutput(true);
          conn.setFixedLengthStreamingMode(request.getPostBody().getBytes("UTF-8").length);
        }
        conn.setConnectTimeout(request.getConnectTimeout());
        conn.setReadTimeout(request.getReadTimeout());
        conn.setRequestMethod(request.getMethod());
        for (final Entry<String, String> header : request.getHeaders().entrySet()) {
          conn.setRequestProperty(header.getKey(), Strings.trimToEmpty(header.getValue()));
        }
        conn.connect();
      } catch (final IOException e) {
        return HttpResponse.fail(e);
      }

      try {
        if (request.isPosting()) {
          output = new BufferedOutputStream(conn.getOutputStream());
          output.write(request.getPostBody().getBytes("UTF-8"));
          output.flush();
        }
        input = conn.getInputStream();
      } catch (final IOException e) {
        closeQuietly(input);
        input = conn.getErrorStream();
      }

      try {
        copy(input, result);
        return HttpResponse.complete(conn.getResponseCode(), result.toString());
      } catch (final IOException e) {
        return HttpResponse.fail(e);
      }
    } finally {
      disconnectQuitely(conn);
      closeQuietly(input);
      closeQuietly(output);
      closeQuietly(result);
    }
  }

  @Override
  protected void onPostExecute(final HttpResponse response) {
    if (callback == null) {
      return;
    }

    if (response.hasException()) {
      callback.onException(response.getIoException());
    } else if (response.isSuccessful()) {
      callback.onSuccess(response.getContent());
    } else if (response.isComplete()) {
      callback.onFailure(response.getContent());
    }
  }
}
