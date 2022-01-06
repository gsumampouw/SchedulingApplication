package schedulemanager.database;

import schedulemanager.domain.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static schedulemanager.database.JDBC.*;

public class UserTable {

    /**
     * Gets a users object from the database by username.
     * @param userName selected username.
     * @return Returns a user.
     * @throws SQLException
     */
    public static Users getUsersByUserName(String userName) throws SQLException {

        openConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_Name = '" + userName + "' ;";
        Users databaseUser = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);


            while (result.next()) {
                int userId = result.getInt("User_ID");
                String username = result.getString("User_Name");
                String password = result.getString("Password");

                databaseUser = new Users(userId, username, password);
                System.out.println("username: " + username + " password: " + password);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

        return databaseUser;
    }

    /**
     * Gets a user from the database by user ID.
     * @param userId selected userID.
     * @return Returns a user.
     * @throws SQLException
     */
    public static Users getUsersById(int userId) throws SQLException {

        openConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_Id = '" + userId + "' ;";
        Users databaseUser = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);


            while (result.next()) {
                String username = result.getString("User_Name");
                String password = result.getString("Password");

                databaseUser = new Users(userId, username, password);
                System.out.println("username: " + username + " password: " + password);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

        return databaseUser;
    }

    /**
     * Gets all users from Users table in the database.
     * @return Returns an observable list of users.
     */
    public static ObservableList<Users> getAllUserId (){

        openConnection();
        String sqlStatement = "SELECT * FROM users ;";
        Users databaseUser = null;
        ObservableList<Users> allUserIds = FXCollections.observableArrayList();

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);


            while (result.next()) {
                int userId = result.getInt("User_ID");
                String username = result.getString("User_Name");
                String password = result.getString("Password");

                databaseUser = new Users(userId, username, password);
                allUserIds.add(databaseUser);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();

        return allUserIds;


    }


}
