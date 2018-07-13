package org.czm.example;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.czm.core.HttpRequest;

public class WeatherHttp {
  
  private static final String HTTP_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getSupportCity";
  
  /*
  request
    GET /WebServices/WeatherWebService.asmx/getSupportCity?byProvinceName=string HTTP/1.1
    Host: www.webxml.com.cn
  
  response
    HTTP/1.1 200 OK
    Content-Type: text/xml; charset=utf-8
    Content-Length: length
    
    <?xml version="1.0" encoding="utf-8"?>
    <ArrayOfString xmlns="http://WebXml.com.cn/">
      <string>string</string>
      <string>string</string>
    </ArrayOfString>
   */
  public String getSupportCityByGet(String byProvinceName) {
    Map<String, String> requestProperty = new HashMap<String, String>();
    requestProperty.put("Host", "www.webxml.com.cn");
    return new HttpRequest().executeGet(HTTP_URL.concat("?byProvinceName=").concat(byProvinceName), requestProperty, null);
  }
  
  /*
  request   
    POST /WebServices/WeatherWebService.asmx/getSupportCity HTTP/1.1
    Host: www.webxml.com.cn
    Content-Type: application/x-www-form-urlencoded
    Content-Length: length

    byProvinceName=string
    
  response
    HTTP/1.1 200 OK
    Content-Type: text/xml; charset=utf-8
    Content-Length: length

    <?xml version="1.0" encoding="utf-8"?>
    <ArrayOfString xmlns="http://WebXml.com.cn/">
      <string>string</string>
      <string>string</string>
    </ArrayOfString>
   */
  public String getSupportCityByPost(String byProvinceName) throws UnsupportedEncodingException {
    String body = "byProvinceName=".concat(URLEncoder.encode(byProvinceName, "UTF-8"));
    Map<String, String> requestProperty = new HashMap<String, String>();
    requestProperty.put("Host", "www.webxml.com.cn");
    requestProperty.put("Content-Type", "application/x-www-form-urlencoded");
    requestProperty.put("Content-Length", String.valueOf(byProvinceName.getBytes().length));
    return new HttpRequest().executePost(HTTP_URL, requestProperty, body);
  }
  
  public static void main(String[] args) {
    try {
      String result = null;
      String byProvinceName = "四川";
      WeatherHttp http = new WeatherHttp();
      result = http.getSupportCityByGet(byProvinceName);
      System.err.println(result);
      result = http.getSupportCityByPost(byProvinceName);
      System.err.println(result);
    } catch (UnsupportedEncodingException e) {
    }
  }

}