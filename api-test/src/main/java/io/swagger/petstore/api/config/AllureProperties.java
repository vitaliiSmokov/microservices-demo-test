package io.swagger.petstore.api.config;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class AllureProperties {

  public void createAllureProperties(List<String> properties) {
    Path allureResults;
    try {
      allureResults = Paths.get(ClassLoader.getSystemResource("").toURI()).getParent();
      allureResults =
          Paths.get(
              allureResults.toAbsolutePath().toString(),
              "../../allure-results",
              "environment.properties");
      if (!Files.exists(allureResults.getParent())) {
        Files.createDirectories(allureResults.getParent());
      }
      Files.write(allureResults, properties);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
