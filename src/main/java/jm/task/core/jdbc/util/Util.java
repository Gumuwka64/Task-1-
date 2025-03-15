package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:postgresql://localhost:5432/SomeForStudy";
    private static final String user = "postgres";
    private static final String password = "Groza2012";

    //подключение к БД
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //    создание или удаление таблицы Очищаем таблицу
    public static boolean executeTable(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Query executed successfully.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //добавление user в таблицу
    public static long executeAddUser(String name, String lastName, Byte age, String query) {
        long id = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("Query executed successfully.");
            id = executeGetId(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    //удаляем user по Id из таблицы
    public static boolean executeRemoveUserById(String query, long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();
            if (result > 0)
                System.out.println("Query executed successfully.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static Long executeGetId(PreparedStatement preparedStatement) throws SQLException {
        long id = -1;
        try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                return id;
            }
        }
        return id;
    }
}

