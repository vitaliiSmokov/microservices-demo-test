package io.swagger.petstore.tests;

import io.swagger.petstore.api.config.AllureProperties;
import io.swagger.petstore.api.config.ConfigProperties;
import io.swagger.petstore.api.utils.PropertyUtil;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseApiTest {

  protected static ConfigProperties configs = PropertyUtil.getConfigs();

  @BeforeAll
  static void initAllureProperties() {
    List<String> props = new ArrayList<>();
    props.add("Stand=" + configs.env().toUpperCase());
    props.add("OS=" + System.getProperty("os.name"));
    props.add("API_response_timeout(ms)=" + configs.timeout());
    new AllureProperties().createAllureProperties(props);
  }
}
