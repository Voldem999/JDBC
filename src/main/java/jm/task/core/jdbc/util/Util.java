package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "student";
    private static final String PASSWORD = "chocolatefrog";

    public static Connection getConnection() {

        try {
            System.out.println("Соединение с БД установлено");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            System.err.println("Соединение с БД не установлено");
        }
        return null;
    }
}
