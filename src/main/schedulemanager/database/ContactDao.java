package main.schedulemanager.database;

import main.schedulemanager.domain.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static main.schedulemanager.database.JDBC.*;

public class ContactDao {

    public static final String CONTACT_ID = "contact_id";
    public static final String CONTACT_NAME = "contact_name";
    public static final String EMAIL = "email";

    public ContactDao() {
    }

    /**
     * Gets all contacts from the Contacts table in the database.
     *
     * @return Returns an observable list of contacts.
     * @throws SQLException
     */
    public ObservableList<Contacts> getAllContacts() throws SQLException {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        Connection connection = openConnection();

        String sqlStatement = "select * from contacts;";
        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                int contactId = result.getInt(CONTACT_ID);
                String contactName = result.getString(CONTACT_NAME);
                String email = result.getString(EMAIL);
                Contacts aContact = new Contacts(contactId, contactName, email);
                allContacts.add(aContact);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return allContacts;
    }

    /**
     * Gets a contact from the Contacts table associated with a contact ID.
     *
     * @param contactId Selected contact ID.
     * @return Returns a contact.
     */
    public Contacts getAContact(int contactId) {
        Contacts aContact = null;

        Connection connection = openConnection();

        String sqlStatement = "select * from contacts where contact_id = " + contactId + ";";
        try {
            Statement stmnt = connection.createStatement();
            ResultSet result = stmnt.executeQuery(sqlStatement);

            while (result.next()) {
                String contactName = result.getString(CONTACT_NAME);
                String email = result.getString(EMAIL);
                aContact = new Contacts(contactId, contactName, email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return aContact;
    }
}
