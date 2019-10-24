package io.swagger.petstore.api.utils;

import io.swagger.petstore.api.config.DbProperties;
import org.aeonbits.owner.ConfigFactory;

import java.sql.*;
import java.util.Properties;

public class DbUtil {

    private DbProperties propertyDB = ConfigFactory.create(DbProperties.class);

    private final String LOGIN = propertyDB.username();
    private final String PASSWORD = propertyDB.password();
    private final String DATABASE_NAME = propertyDB.name();
    private final String DATABASE_ADDRESS = propertyDB.url();

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    private Connection connectToMySQL() {
        try {
            connection = DriverManager.getConnection(DATABASE_ADDRESS + DATABASE_NAME, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private Statement createStatement() {
        try {
            statement = connectToMySQL().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public ResultSet executeQuery(String query) {
        try {
            resultSet = createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAllDBConnections();
        }
        return resultSet;
    }

    public void closeAllDBConnections() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
