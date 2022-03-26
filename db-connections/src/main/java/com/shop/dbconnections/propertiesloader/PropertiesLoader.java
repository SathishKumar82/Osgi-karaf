package com.shop.dbconnections.propertiesloader;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.Map;

/**
 * Properties loader.
 *
 * @author SathishKumarS
 */
@Component(immediate = true, configurationPid = "jdbc")
public class PropertiesLoader {

    private static Map<String, String> properties;

    /**
     * Activator for load the properties.
     *
     * @param properties
     */
    @Activate
    public void activate(Map<String, String> properties) {
        this.properties = properties;
    }

    /**
     * Gets the properties
     *
     * @return
     */
    public static Map<String, String> getProperties() {
        return properties;
    }
}
