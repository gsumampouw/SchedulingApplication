package main.schedulemanager.database;

import main.schedulemanager.domain.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static main.schedulemanager.database.JDBC.*;

public class CountryDao {

    public static final String COUNTRY_ID = "country_id";
    public static final String COUNTRY = "country";

    public CountryDao() {
    }

    /**
     * Gets all countries from the Countries table in the database.
     *
     * @return Returns an observable list of Coutnries.
     */
    public ObservableList<Countries> getAllCountries() {
        Connection connection = openConnection();
        ObservableList allCountries = FXCollections.observableArrayList();
        String sqlStatement = "select * from countries;";

        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int countryId = result.getInt(COUNTRY_ID);
                String country = result.getString(COUNTRY);

                Countries count = new Countries(countryId, country);
                allCountries.add(count);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return allCountries;
    }


    /**
     * Gets a country from the Countries table in the database associated with a selected country ID.
     *
     * @param countryID Selected country ID.
     * @return Returns a country.
     */
    public Countries getCountryById(int countryID) {
        Connection connection = openConnection();
        int id = countryID;
        String countryName = "wrong";
        Countries country = null;
        // TODO: Use meaningful names for variables
        String getCountryByIdSQL = "select * from countries where country_id =" + id + ";";

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(getCountryByIdSQL);

            while (result.next()) {
                countryName = result.getString(COUNTRY);
                country = new Countries(id, countryName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return country;
    }
}
