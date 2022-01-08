package schedulemanager.database;

import schedulemanager.domain.Divisions;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Statement;

import static schedulemanager.database.JDBC.*;

public class DivisionDao {


    public static final String DIVISION_ID = "Division_ID";
    public static final String DIVISION = "Division";
    public static final String COUNTRY_ID = "country_Id";

    public DivisionDao() {
    }

    /**
     * Gets all divisions from the Divisions table in the database.
     *
     * @return Returns an observable list of divisions.
     */
    public ObservableList<Divisions> getAllDivisions() {

       Connection connection = openConnection();

        ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

        String sqlStatement = "select * from first_level_divisions;";

        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {

                int divisionId = result.getInt(DIVISION_ID);
                String division = result.getString(DIVISION);
                int countryId = result.getInt(COUNTRY_ID);
                Divisions div = new Divisions(divisionId, division, countryId);

                allDivisions.add(div);
            }
        } catch (SQLException throwables) {
        throwables.printStackTrace();
    } finally {
        closeConnection(connection);
    }
        return allDivisions;
    }

    /**
     * Gets associated divisions based on country ID.
     *
     * @param countryId selected country ID.
     * @return Returns an observable list of divisions.
     */
    public ObservableList<Divisions> getAssociatedDivisions(int countryId) {
       Connection connection = openConnection();
        String sqlStatement = "SELECT * FROM first_level_divisions WHERE Country_ID =" + countryId + ";";
        ObservableList<Divisions> AssociatedDiv = FXCollections.observableArrayList();

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                int divisionID = result.getInt(DIVISION_ID);
                String div = result.getString(DIVISION);

                Divisions selectDiv = new Divisions(divisionID, div, countryId);
                AssociatedDiv.add(selectDiv);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return AssociatedDiv;
    }

    /**
     * Gets a division based on division ID.
     *
     * @param divId selected division ID.
     * @return Returns a division.
     */
    public Divisions getADivision(int divId) {
        String division;
        Divisions div = null;
        int countryId;
        Connection connection = openConnection();
        String sqlStatement = "SELECT * FROM first_level_divisions WHERE Division_Id = " + divId + ";";

        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                division = result.getString("division");
                countryId = result.getInt("country_id");
                div = new Divisions(divId, division, countryId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return div;
    }
}
