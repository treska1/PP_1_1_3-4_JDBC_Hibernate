package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL ="jdbc:mysql://localhost:3306/newdb";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="root";

    private static Connection connection;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection error: " + e);
        }
        return connection;
    }




}
