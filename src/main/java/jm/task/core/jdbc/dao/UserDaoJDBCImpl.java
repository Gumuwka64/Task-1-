package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {
    //запрос на создание
    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
            "id BIGSERIAL  PRIMARY KEY," +
            "name VARCHAR(50) NOT NULL," + //сделать проверку на syze
            "lastName VARCHAR(100) NOT NULL," +
            "age SMALLINT NOT NULL)"; //сделать проверку на отрицательный возраст

    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS users";

    private static final String SQL_INSERT = "INSERT INTO users(name, lastname, age) VALUES (?,?,?)";

    private static final String SQL_CLEAN_ALL = "TRUNCATE TABLE users";

    private static final String  SQL_REMOVE_BY_ID = "DELETE FROM users WHERE id = ?";


    private static List<User> users = new ArrayList<User>();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        boolean success = executeTable(SQL_CREATE_TABLE);
        if (success)
            System.out.println("Table created\n");
        else
            System.out.println("Query failed\n");
    }

    public void dropUsersTable() {
        //удаление всей таблицы
        boolean success = executeTable(SQL_DROP_TABLE);
        if (success)
            System.out.println("Table dropped.\n");
        else
            System.out.println("Query failed.\n");
    }

    public void saveUser(String name, String lastName, byte age) {
//        создаю user
        //проверка на положительный возраст
        if (age >= 0) {
            User user = new User(name, lastName, age);
            user.setId(executeAddUser(name, lastName, age, SQL_INSERT));//Отправляем его в таблицу и присваиваю id

            if (user.getId() != -1) {//вытаскиваю id из таблицы//set id user
                users.add(user);//добавляю в список
                System.out.println("User saved\n");
            } else
                throw new NoResultException("User didn`t saved.\n");

        } else
            throw new NumberFormatException("Age cannot be negative!\n");

    }


    public void removeUserById(long id) {
        //delete from users d ID
        boolean success = executeRemoveUserById(SQL_REMOVE_BY_ID,id);
        if(success) {
           getAllUsers().removeIf(user -> user.getId() == id);
            System.out.println("User removed\n");
        }else
            System.out.println("Query failed.\n");

    }

    public List<User> getAllUsers() {

    return users;}

    public void cleanUsersTable() {
        //удаляем всех user
        boolean success = executeTable(SQL_CLEAN_ALL);
        if (success) {
            users.clear();
            System.out.println("Table cleaned\n");
        } else
            System.out.println("Query failed.\n");

    }
}
