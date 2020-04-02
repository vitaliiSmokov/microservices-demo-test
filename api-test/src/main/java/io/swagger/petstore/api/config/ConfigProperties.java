package io.swagger.petstore.api.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface ConfigProperties extends Config {

  @DefaultValue("prod")
  String env();

  @Key("${env}.baseUrl")
  String baseUrl();

  boolean logs();

  @Key("${env}.username")
  String username();

  @Key("${env}.password")
  String password();

  String application();

  @DefaultValue("10000")
  long timeout();
}
