package io.swagger.petstore.api.utils;

import io.swagger.petstore.api.config.ConfigProperties;
import lombok.experimental.UtilityClass;
import org.aeonbits.owner.ConfigFactory;

@UtilityClass
public class PropertyUtil {
    private ConfigProperties configs;

    public ConfigProperties getConfigs() {
        if (configs == null) {
            configs = ConfigFactory.create(ConfigProperties.class, System.getProperties());
        }
        return configs;
    }
}
