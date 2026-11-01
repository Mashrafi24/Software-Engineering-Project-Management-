
package com.mashrafi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private static final String URL = setting(
            "MYSQL_URL",
            "jdbc:mysql://127.0.0.1:3306/mashrafi_lab?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
    );

    private static final String USER = setting("MYSQL_USER", "root");

    private static final String PASSWORD = setting("MYSQL_PASSWORD", "sql@123");

    private DatabaseConnection() {}

    private static String setting(String key, String fallback) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? fallback : value;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
