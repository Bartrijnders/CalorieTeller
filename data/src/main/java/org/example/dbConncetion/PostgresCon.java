package org.example.dbConncetion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresCon implements DBconnection {

    private final String url = "jdbc:postgresql://localhost:5432/calorieteller";
    private final String user = "bartrijnders";
    private final String password = "postgres";

    @Override
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


}
