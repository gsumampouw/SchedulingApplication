package schedulemanager.database;

import schedulemanager.domain.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import static schedulemanager.database.JDBC.*;

public class CustomersTable {


    /**
     * Gets all customers from the Customers table from the database.
     * @return Returns an observable list of customers.
     */
    public static ObservableList<Customers> getAllCustomers() {

        Connection connection = openConnection();
        String sqlStatement = "SELECT * FROM customers ;";
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();


        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sqlStatement);


            while (result.next()) {

                int customerId = result.getInt("Customer_ID");
                String name = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String phone = result.getString("Phone");
                int divisionId = result.getInt("Division_ID");

                Customers customerDatabase = new Customers(customerId, name, address, postalCode, phone, divisionId);
                allCustomers.add(customerDatabase);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return allCustomers;

    }

    /**
     * Adds a new customer to the Customers table in the database.
     * @param customerName customer name.
     * @param address customer address.
     * @param postalCode customer postal code.
     * @param phone customer phone.
     * @param divisionid customer division id.
     * @throws SQLException
     */
    public static void addCustomer(String customerName,String address,String postalCode,String phone,int divisionid) throws SQLException {
        //add customerData to database
        Connection connection = openConnection();

        String sqlStatement = "INSERT INTO customers(customer_name,address,postal_code,phone,division_id) " +
                "values(?,?,?,?,?)";

        try {
            PreparedStatement stmnt = connection.prepareStatement(sqlStatement);
            stmnt.setString(1,customerName);
            stmnt.setString(2,address);
            stmnt.setString(3,postalCode);
            stmnt.setString(4,phone);
            stmnt.setInt(5,divisionid);
            stmnt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }

    }


    /**
     * Updates an existing customer in the Customers table in the database.
     * @param updatedCust Updated customer object containing updated information.
     */
    public static void updateCustomer(Customers updatedCust) {
       Connection connection = openConnection();

        int customerId = updatedCust.getCustomerId();
        String name = updatedCust.getCustomerName();
        String address = updatedCust.getAddress();
        String postal = updatedCust.getPostalCode();
        String phone = updatedCust.getPhone();
        int div = updatedCust.getDivisionId();

        String sqlStatement = "UPDATE customers\n" +
                "SET customer_name= '" + name + "',address='" + address + "',postal_code='" + postal + "',phone='" + phone +
                "',division_id=" + div + "\n" + "WHERE customer_id=" + customerId + ";";

        try (Statement stmnt = connection.createStatement()) {
            int count = stmnt.executeUpdate(sqlStatement);
            System.out.println("Customers updated: " + count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeConnection(connection);
        }

    }

public static void deleteCustomer(int customerId){

        String sqlStatement = "DELETE FROM customers WHERE Customer_ID = ?";
       Connection connection = openConnection();
try {
    PreparedStatement stmnt = connection.prepareStatement(sqlStatement);
    stmnt.setInt(1, customerId);
    stmnt.execute();
}catch (SQLException e){
    e.printStackTrace();
}finally {
    closeConnection(connection);
}



}
}


