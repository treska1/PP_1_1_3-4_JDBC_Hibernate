package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users(id bigInt PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(64), lastName VARCHAR(64), age tinyInt) ";
    private final String DELETE_TABLE = "DROP TABLE IF EXISTS users";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery(DELETE_TABLE).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User с именем - "+name + " добавлен в базу данных");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = util.getSessionFactory().openSession()) {
            session.beginTransaction();
            users = session.createQuery("from User").getResultList();

            session.getTransaction().commit();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
