package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

<<<<<<< HEAD
        userService.saveUser("Tom", "Serenity", (byte) 19);
        userService.saveUser("Jimmy", "Neutron", (byte) 12);
        userService.saveUser("John", "Travolta", (byte) 28);
        userService.saveUser("Alex", "Pistoletov", (byte) 33);
=======
        userService.saveUser("Tom","Serenity", (byte)19);
        userService.saveUser("Jimmy","Neutron", (byte)12);
        userService.saveUser("John","Travolta", (byte)28);
        userService.saveUser("Alex","Pistoletov", (byte)33);
>>>>>>> daa43304cb457c139eeb3552b068efd00611fdc9

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
