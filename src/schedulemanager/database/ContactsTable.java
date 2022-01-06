package schedulemanager.database;

import schedulemanager.domain.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static schedulemanager.database.JDBC.*;

public class ContactsTable {

    /**
     * Gets all contacts from the Contacts table in the database.
     * @return Returns an observable list of contacts.
     * @throws SQLException
     */
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        openConnection();

        String sqlStatement = "select * from contacts;";

        Statement stmnt = connection.createStatement();
        ResultSet result = stmnt.executeQuery(sqlStatement);

        while(result.next()){
            int contactId = result.getInt("contact_id");
            String contactName = result.getString("contact_name");
            String email = result.getString("email");
            Contacts aContact = new Contacts(contactId,contactName,email);
            allContacts.add(aContact);
        }

        return allContacts;
    }

    /**
     * Gets a contact from the Contacts table associated with a contact ID.
     * @param contactId Selected contact ID.
     * @return Returns a contact.
     */
    public static Contacts getAContact(int contactId){
        Contacts aContact = null;

        openConnection();

        String sqlStatement = "select * from contacts where contact_id = "+contactId+";";
        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                String contactName = result.getString("contact_name");
                String email = result.getString("email");
                aContact = new Contacts(contactId, contactName, email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }

        return aContact;

    }


}
