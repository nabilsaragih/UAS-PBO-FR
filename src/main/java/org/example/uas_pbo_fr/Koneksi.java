package org.example.uas_pbo_fr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/uas-pbo";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection DBConnect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

