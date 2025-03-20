package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    //константы подключения к БД
    private static final String URL = "jdbc:postgresql://localhost:5432/SomeForStudy";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Groza2012";

    //---------Подключение к Hibernate
    //драйвер подключения hibernate к Java
    private static final String DRIVER = "org.postgresql.Driver";

    static {Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);}

    private static SessionFactory sessionFactory;
//    private static StandardServiceRegistry registry;
//Закомментил оч длинный и неудобный метод подключения Hibernate
{
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//
//                Map<String, Object> settings = new HashMap<>();
//                settings.put(Environment.DRIVER, DRIVER);
//                settings.put(Environment.URL, URL);
//                settings.put(Environment.USER, USER);
//                settings.put(Environment.PASS, PASSWORD);
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
//                settings.put(Environment.SHOW_SQL, "true");
//                settings.put(Environment.HBM2DDL_AUTO, "update");
//
//                registry = new StandardServiceRegistryBuilder()
//                        .applySettings(settings)
//                        .build();
//
//                MetadataSources sources = new MetadataSources(registry);
//
//                sources.addAnnotatedClass(User.class);
//
//                Metadata metadata = sources.getMetadataBuilder().build();
//                sessionFactory = metadata.getSessionFactoryBuilder().build();
//            }catch (Exception e) {
//                e.printStackTrace();
//                if(registry != null) {
//                    StandardServiceRegistryBuilder.destroy(registry);
//                }
//            }
//        }
//        return sessionFactory;
//    }
}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                 return new Configuration()
                        .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                        .setProperty("hibernate.connection.driver_class", DRIVER)
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USER)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.hbm2ddl.auto", "update")
                        .buildSessionFactory();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return sessionFactory;
    }

    //JDBC----------------------------------------------------------------
    //подключение к БД
    public static Connection jdbcGetConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

