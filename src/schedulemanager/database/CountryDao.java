package schedulemanager.database;

import schedulemanager.domain.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static schedulemanager.database.JDBC.*;

public class CountriesTable {

    /**
     * Gets all countries from the Countries table in the database.
     * @return Returns an observable list of Coutnries.
     */
    public static ObservableList<Countries> getAllCountries() {
        openConnection();
        ObservableList allCountries = FXCollections.observableArrayList();
        String sqlStatement = "select * from countries;";

        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int countryId = result.getInt("country_id");
                String country = result.getString("country");
                System.out.println("country: " + country);

                Countries count = new Countries(countryId, country);
                allCountries.add(count);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection();
        return allCountries;
    }


    /**
     * Gets a country from the Countries table in the database associated with a selected country ID.
     * @param countryID Selected country ID.
     * @return Returns a country.
     */
    public static Countries getACountry(int countryID) {
        openConnection();
        int id = countryID;
        String countryName = "wrong";
        Countries country = null;
        String sqlStatement = "select * from countries where country_id =" + id + ";";


        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);

            while (result.next()) {
                countryName = result.getString("country");
                country = new Countries(id, countryName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return country;
    }
}
