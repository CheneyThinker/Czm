package org.czm.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public abstract class CzmRequest {
  
  public static final String POST = "POST";
  public static final String GET = "GET";
  public static final String DELETE = "DELETE";
  public static final String PUT = "PUT";
  public static final String DEFAULT_CHARSET = "UTF-8";
  
  public String execute(String url, String method, Map<String, String> requestProperty, String body, String charset) {
    InputStream is = null;
    OutputStream os = null;
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    InputStreamReader isr = null;
    try {
      connection = (HttpURLConnection) new URL(url).openConnection();
      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setRequestMethod(method);
      Set<String> keys = requestProperty.keySet();
      for (String key : keys) {
        connection.setRequestProperty(key, requestProperty.get(key));
      }
      if (body != null) {
        os = connection.getOutputStream();
        os.write(body.getBytes(charset));
        os.flush();
      }
      if (200 == connection.getResponseCode()) {
        is = connection.getInputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        StringBuffer buffer = new StringBuffer();
        while ((len = is.read(bytes)) != -1) {
          buffer.append(new String(bytes, 0, len, charset));
        }
        return buffer.toString();
        /*
        isr = new InputStreamReader(is);
        reader = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
          buffer.append(temp);
        }
        return buffer.toString();
        */
      } else {
        return null;
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } finally {
      try {
        if (is != null) {
          is.close();
        }
        if (os != null) {
          os.close();
        }
        if (reader != null) {
          reader.close();
        }
        if (isr != null) {
          isr.close();
        }
        if (connection != null) {
          connection.disconnect();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}