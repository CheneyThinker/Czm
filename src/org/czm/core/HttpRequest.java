package org.czm.core;

import java.util.Map;

public class HttpRequest extends CzmRequest {
  
  public String executePost(String url, Map<String, String> requestProperty, String body) {
    return execute(url, POST, requestProperty, body, DEFAULT_CHARSET);
  }
  
  public String executeGet(String url, Map<String, String> requestProperty, String body) {
    return execute(url, GET, requestProperty, body, DEFAULT_CHARSET);
  }
  
}