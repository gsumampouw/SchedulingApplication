package schedulemanager.database;

import schedulemanager.domain.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static schedulemanager.database.JDBC.*;

public class UserDao {

    public static final String USER_ID = "User_ID";
    public static final String USER_NAME = "User_Name";
    public static final String PASSWORD = "Password";

    public UserDao() {
    }

    /**
     * Gets a users object from the database by username.
     * @param userName selected username.
     * @return Returns a user.
     * @throws SQLException
     */
    public Users getUsersByUserName(String userName) throws SQLException {

        Connection connection = openConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_Name = '" + userName + "' ;";
        Users databaseUser = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                int userId = result.getInt(USER_ID);
                String username = result.getString(USER_NAME);
                String password = result.getString(PASSWORD);

                databaseUser = new Users(userId, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return databaseUser;
    }

    /**
     * Gets a user from the database by user ID.
     * @param userId selected userID.
     * @return Returns a user.
     * @throws SQLException
     */
    public Users getUsersById(int userId) throws SQLException {

        Connection connection = openConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_Id = '" + userId + "' ;";
        Users databaseUser = null;

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                String username = result.getString(USER_NAME);
                String password = result.getString(PASSWORD);
                databaseUser = new Users(userId, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return databaseUser;
    }

    /**
     * Gets all users from Users table in the database.
     * @return Returns an observable list of users.
     */
    public ObservableList<Users> getAllUserId (){

        Connection connection = openConnection();
        String sqlStatement = "SELECT * FROM users ;";
        Users databaseUser = null;
        ObservableList<Users> allUserIds = FXCollections.observableArrayList();

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);


            while (result.next()) {
                int userId = result.getInt(USER_ID);
                String username = result.getString(USER_NAME);
                String password = result.getString(PASSWORD);

                databaseUser = new Users(userId, username, password);
                allUserIds.add(databaseUser);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return allUserIds;
    }


}
