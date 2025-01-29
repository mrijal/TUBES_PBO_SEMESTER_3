package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/asset_management";
    private static final String USER = "root"; // Ganti sesuai dengan MySQL Anda
    private static final String PASSWORD = ""; // Ganti sesuai dengan MySQL Anda

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
