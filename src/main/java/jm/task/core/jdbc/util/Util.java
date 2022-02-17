package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.mapping.Property;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection error: " + e);
        }
        return connection;
    }

    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties property = new Properties();

            property.put(Environment.URL, "jdbc:mysql://localhost:3306/newdb?SSL=false");
            property.put(Environment.USER, "root");
            property.put(Environment.PASS, "root");
            property.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            property.put(Environment.SHOW_SQL, "true");
//               property.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            property.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            property.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(property);
            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
