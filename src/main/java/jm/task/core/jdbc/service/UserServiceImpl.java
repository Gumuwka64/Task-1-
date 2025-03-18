package jm.task.core.jdbc.service;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;


public class UserServiceImpl implements UserService {
    private static UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
    private static UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    private static boolean result = true;

    public void setWay(boolean hiber) {
        result = hiber;
        if (result)
            System.out.println("Starting Hibernate methods");
        else
            System.out.println("Starting JDBC methods");
    }


    public void createUsersTable() {
        if(!result)
        userDao.createUsersTable();
        else
            userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        if(!result)
        userDao.dropUsersTable();
        else
            userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        if(!result)
            userDao.saveUser(name, lastName, age);
        else
            userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        if(!result)
        userDao.removeUserById(id);
        else
            userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        if(!result){
        List<User> users = userDao.getAllUsers();
        return users;}
        else {
       List<User> users = userDaoHibernate.getAllUsers();
        return users;
    }
    }

    public void cleanUsersTable() {
        if(!result)
        userDao.cleanUsersTable();
        else {
            userDaoHibernate.cleanUsersTable();}
    }
}
