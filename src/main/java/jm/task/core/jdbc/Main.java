package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("User_1", "U_LastName_1", (byte) 10);
        userDao.saveUser("User_2", "U_LastName_2", (byte) 15);
        userDao.saveUser("User_3", "U_LastName_3", (byte) 30);
        userDao.saveUser("User_4", "U_LastName_4", (byte) 35);

        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}