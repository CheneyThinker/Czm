package org.czm.example;

import java.util.HashMap;
import java.util.Map;

import org.czm.core.SoapRequest;

public class WeatherSoap {
  
  private static final String SOAP_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
  
  /*
  request
    POST /WebServices/WeatherWebService.asmx HTTP/1.1
    Host: www.webxml.com.cn
    Content-Type: text/xml; charset=utf-8
    Content-Length: length
    SOAPAction: "http://WebXml.com.cn/getSupportCity"
      
    <?xml version="1.0" encoding="utf-8"?>
    <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
      <soap:Body>
        <getSupportCity xmlns="http://WebXml.com.cn/">
          <byProvinceName>string</byProvinceName>
        </getSupportCity>
      </soap:Body>
    </soap:Envelope>
    
  response
    HTTP/1.1 200 OK
    Content-Type: text/xml; charset=utf-8
    Content-Length: length
    
    <?xml version="1.0" encoding="utf-8"?>
    <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
      <soap:Body>
        <getSupportCityResponse xmlns="http://WebXml.com.cn/">
          <getSupportCityResult>
            <string>string</string>
            <string>string</string>
          </getSupportCityResult>
        </getSupportCityResponse>
      </soap:Body>
    </soap:Envelope>
   */
  public String getSupportCityBySoap1Dot1(String byProvinceName) {
    Map<String, String> requestProperty = new HashMap<String, String>();
    requestProperty.put("Host", "www.webxml.com.cn");
    requestProperty.put("Content-Type", "text/xml; charset=utf-8");
    requestProperty.put("Content-Length", String.valueOf(byProvinceName.getBytes().length));
    requestProperty.put("SOAPAction", "\"http://WebXml.com.cn/getSupportCity\"");
    
    StringBuffer soap = new StringBuffer();
    soap
    .append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n")
    .append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n")
    .append("  <soap:Body>\r\n")
    .append("    <getSupportCity xmlns=\"http://WebXml.com.cn/\">\r\n")
    .append("      <byProvinceName>").append(byProvinceName).append("</byProvinceName>\r\n")
    .append("    </getSupportCity>\r\n")
    .append("  </soap:Body>\r\n")
    .append("</soap:Envelope>");
    
    return new SoapRequest().executeSoap(SOAP_URL, requestProperty, soap.toString());
  }
  
  /*
  request
    POST /WebServices/WeatherWebService.asmx HTTP/1.1
    Host: www.webxml.com.cn
    Content-Type: application/soap+xml; charset=utf-8
    Content-Length: length
      
    <?xml version="1.0" encoding="utf-8"?>
    <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
      <soap12:Body>
        <getSupportCity xmlns="http://WebXml.com.cn/">
          <byProvinceName>string</byProvinceName>
        </getSupportCity>
      </soap12:Body>
    </soap12:Envelope>
   
  response
    HTTP/1.1 200 OK
    Content-Type: application/soap+xml; charset=utf-8
    Content-Length: length
      
    <?xml version="1.0" encoding="utf-8"?>
    <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
      <soap12:Body>
        <getSupportCityResponse xmlns="http://WebXml.com.cn/">
          <getSupportCityResult>
            <string>string</string>
            <string>string</string>
          </getSupportCityResult>
        </getSupportCityResponse>
      </soap12:Body>
    </soap12:Envelope>
   */
  public String getSupportCityBySoap1Dot2(String byProvinceName) {
    Map<String, String> requestProperty = new HashMap<String, String>();
    requestProperty.put("Host", "www.webxml.com.cn");
    requestProperty.put("Content-Type", "application/soap+xml; charset=utf-8");
    requestProperty.put("Content-Length", String.valueOf(byProvinceName.getBytes().length));
    
    StringBuffer soap = new StringBuffer();
    soap
    .append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n")
    .append("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n")
    .append("  <soap12:Body>\r\n")
    .append("    <getSupportCity xmlns=\"http://WebXml.com.cn/\">\r\n")
    .append("      <byProvinceName>").append(byProvinceName).append("</byProvinceName>\r\n")
    .append("    </getSupportCity>\r\n")
    .append("  </soap12:Body>\r\n")
    .append("</soap12:Envelope>");
    
    return new SoapRequest().executeSoap(SOAP_URL, requestProperty, soap.toString());
  }
  
  public static void main(String[] args) {
    String result = null;
    String byProvinceName = "四川";
    WeatherSoap soap = new WeatherSoap();
    result = soap.getSupportCityBySoap1Dot1(byProvinceName);
    System.err.println(result);
    result = soap.getSupportCityBySoap1Dot2(byProvinceName);
    System.err.println(result);
  }

}