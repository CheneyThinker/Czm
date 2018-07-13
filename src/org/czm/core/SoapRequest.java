package org.czm.core;

import java.util.Map;

public class SoapRequest extends CzmRequest {
  
  public String executeSoap(String url, Map<String, String> requestProperty, String soap) {
    return execute(url, POST, requestProperty, soap, DEFAULT_CHARSET);
  }

}