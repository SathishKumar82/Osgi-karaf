package com.shop.dbconnections.connections;

import com.shop.dbconnections.exceptions.UnableToConnectException;
import com.shop.dbconnections.propertiesloader.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DB connections.
 *
 * @author SathishKumarS
 */
public class DBConnections {

    /**
     * It connects database.
     * @return established connection by connection.
     */
    public Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            final Connection connection = DriverManager.getConnection(PropertiesLoader.getProperties().get("url"),
                    PropertiesLoader.getProperties().get("username"), PropertiesLoader.getProperties().get("password"));

            return connection;
        } catch (Exception exception) {
            throw new UnableToConnectException(exception.getMessage());
        }
    }
}
