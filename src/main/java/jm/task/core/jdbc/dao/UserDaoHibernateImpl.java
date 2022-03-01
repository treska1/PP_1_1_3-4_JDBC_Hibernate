package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();
    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users(id bigInt PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(64), lastName VARCHAR(64), age tinyInt) ";
    private final String DELETE_TABLE = "DROP TABLE IF EXISTS users";
    private Transaction transaction;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createSQLQuery(DELETE_TABLE).executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = util.getSessionFactory().openSession();
        try  {
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));

            System.out.println("User с именем - "+name + " добавлен в базу данных");
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = util.getSessionFactory().openSession();
        try  {
            transaction = session.beginTransaction();

            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = util.getSessionFactory().openSession();
        try  {
            transaction = session.beginTransaction();

            users = session.createQuery("FROM User", User.class).getResultList();

            transaction.commit();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = util.getSessionFactory().getCurrentSession();
        try  {
            transaction = session.beginTransaction();

            session.createQuery("DELETE User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
