package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;


public class Main {
    public static final boolean HIBER = true;
    private static UserServiceImpl service = new UserServiceImpl();

    public static void main(String[] args) throws InterruptedException {
        service.setWay(HIBER);
        allQuery();


    }
    public static void printAllUsers(List<User> users) {
        if(users == null){
            System.out.println("List is null");}
        else if(!users.isEmpty()) {
        for (User user : users) {
            System.out.println(user);
        }}else
           System.out.println("The list with users is empty");
       }


    public static void allQuery() {
        service.dropUsersTable();
        service.createUsersTable();
        service.saveUser("Иван","Иванов", (byte) 2);
        service.saveUser("Стас","Стасов", (byte) 13);
        service.saveUser("Алексей","Алешин", (byte) 23);
        service.saveUser("Марьяна","Марьянова", (byte) 30);
        printAllUsers(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();}
}




