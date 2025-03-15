package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        //ВСЯ РАБОТА ДОЛЖНА ИДТИ ЧЕРЕЗ USERSERVICE
        UserServiceImpl service = new UserServiceImpl();
        service.dropUsersTable();
        service.createUsersTable();
        service.saveUser("Иван","Иванов", (byte) 2);
        service.saveUser("Стас","Стасов", (byte) 13);
        service.saveUser("Алексей","Алешин", (byte) 23);
        service.saveUser("Марьяна","Марьянова", (byte) 30);
        printAllUsers(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();


    }
    public static void printAllUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }

    }
}
