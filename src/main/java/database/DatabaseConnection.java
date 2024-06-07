/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.gguqhbzubkwjfrghlrip";
    private static final String PASSWORD = "Database777!777";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Не найден класс драйвера" + e.getMessage());
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
