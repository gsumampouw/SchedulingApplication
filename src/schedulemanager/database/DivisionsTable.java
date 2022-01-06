package schedulemanager.database;

import schedulemanager.domain.Divisions;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Statement;

import static schedulemanager.database.JDBC.*;

public class DivisionsTable {


    /**
     * Gets all divisions from the Divisions table in the database.
     * @return Returns an observable list of divisions.
     */
    public static ObservableList<Divisions> getAllDivisions() {

        openConnection();

        ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

        String sqlStatement = "select * from first_level_divisions;";

        try{
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {

                int divisionId = result.getInt("Division_ID");
                String division = result.getString("Division");
                int countryId = result.getInt("country_Id");
                Divisions div = new Divisions(divisionId, division,countryId);

                allDivisions.add(div);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeConnection();
        return allDivisions;

    }

    /**
     * Gets associated divisions based on country ID.
     * @param countryId selected country ID.
     * @return Returns an observable list of divisions.
     */
    public static ObservableList<Divisions> getAssociatedDivisions (int countryId){
        openConnection();
        String sqlStatement = "SELECT * FROM first_level_divisions WHERE Country_ID ="+countryId+ ";";
        ObservableList<Divisions> AssociatedDiv = FXCollections.observableArrayList();

       try {
           Statement stmt = connection.createStatement();
           ResultSet result = stmt.executeQuery(sqlStatement);

           while(result.next()) {
               int divisionID = result.getInt("Division_ID");
               String div = result.getString("Division");

               Divisions selectDiv = new Divisions(divisionID, div,countryId);
               AssociatedDiv.add(selectDiv);
           }

       }catch (SQLException e){
           e.printStackTrace();
       }

       return AssociatedDiv;
    }

    /**
     * Gets a division based on division ID.
      * @param divId selected division ID.
     * @return Returns a division.
     */
    public static Divisions getADivision(int divId){
        String division;
        Divisions div = null;
        int countryId;
        openConnection();
        String sqlStatement = "SELECT * FROM first_level_divisions WHERE Division_Id = "+divId+";";

        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while(result.next()){
                division = result.getString("division");
                countryId = result.getInt("country_id");
                div = new Divisions(divId,division,countryId);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return div;
    }



}
