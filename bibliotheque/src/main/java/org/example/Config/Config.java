package org.example.Config;

import java.sql.Connection;
import java.sql.DriverManager;


public class Config {

    public Connection createConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/bibliotheque";
            String username = "postgres";
            String password = "12";

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
