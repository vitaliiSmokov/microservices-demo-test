package io.swagger.petstore.api.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class UrlUtil {

  public List<NameValuePair> parseUrlWithParams(String url) {
    List<NameValuePair> params = null;
    try {
      params = URLEncodedUtils.parse(new URI(url), Charset.defaultCharset());
    } catch (URISyntaxException e) {
        log.info("Error message: {}", e.getMessage());
    }
    return params;
  }

  public Map<String, String> parseUrlWithParamsToMap(String url) {
    return parseUrlWithParams(url).stream()
        .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
  }
}
