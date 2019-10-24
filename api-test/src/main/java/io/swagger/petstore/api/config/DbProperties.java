package io.swagger.petstore.api.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:db.properties"})
public interface DbProperties extends Config {

    @Key("db_url")
    String url();

    @Key("db_name")
    String name();

    String username();

    String password();
}
