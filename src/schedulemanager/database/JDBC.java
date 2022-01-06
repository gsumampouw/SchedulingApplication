package schedulemanager.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class for opening and closing database connections.
 */
public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone=SERVER";//Local. if timestamp will auto convert from UTC
    private static final String driver = "com.mysql.cj.jdbc.Driver"; //Driver reference
    private static String userName = "LabUser";
    private static String password = "Passw0rd!";
    public static Connection connection; //connection interface

    /**
     * Opens database connection.
     */
    public static void openConnection() {
        try {
            Class.forName(driver);//Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); //Reference Connection object

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * Closes database connection.
      */
    public static void closeConnection() {
        try {
            connection.close();

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

}
