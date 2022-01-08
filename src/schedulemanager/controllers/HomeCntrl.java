package schedulemanager.controllers;

import schedulemanager.domain.Customers;
import schedulemanager.database.CustomerDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulemanager.services.Checks;
import schedulemanager.services.JavaFXFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeCntrl implements Initializable {
    Stage stage;
    Parent scene;

    JavaFXFunctions navigation = new JavaFXFunctions();
    JavaFXFunctions alertInfoBox = new JavaFXFunctions();
    CustomerDao customerDao = new CustomerDao();

    @FXML
    private TableView<Customers> customerTable;

    @FXML
    private TableColumn<?, ?> customerIdClmn;

    @FXML
    private TableColumn<?, ?> nameClmn;

    @FXML
    private TableColumn<?, ?> addressClmn;

    @FXML
    private TableColumn<?, ?> postalCodeClmn;

    @FXML
    private TableColumn<?, ?> phoneClmn;

    @FXML
    private TableColumn<?, ?> divisionIdClmn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label usernameLabel;


    /**
     * Initializes the home page and auto populates the customer table view with all customers.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerTable.setItems(customerDao.getAllCustomers());

        customerIdClmn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameClmn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressClmn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeClmn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneClmn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionIdClmn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        usernameLabel.setText("Username: " + LoginCntrl.logedinUsername);


    }

    /**
     * Navigates to the add customer page.
      * @param event This event is triggered when the add customer button is clicked.
     * @throws IOException
     */
    @FXML
    public void addCustomer(ActionEvent event) throws IOException {
        navigation.navigateToPage(event,"/schedulemanager/views/AddCustomer.fxml");
    }

    /**
     * Deletes the selected customer from the database and checks if the customer has associated appointments prior to deletion.
     * Lambda function interface DeleteCustomer is used to delete customer from the database.
      * @param event This event is triggered when the delete button is clicked.
     * @throws SQLException
     */
    @FXML
    public void deleteCustomer(ActionEvent event) throws SQLException {
        Customers selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        int customerId = selectedCustomer.getCustomerId();
        Checks check = new Checks();
        boolean haveAppt = check.checkIfCustomerHasApptUsingCustId(customerId);

        if (!haveAppt) {
            customerDao.deleteCustomer(customerId);
            customerTable.setItems(customerDao.getAllCustomers());
            alertInfoBox.informationAlert("Information Dialog",null,"Customer was deleted");
        } else {
            alertInfoBox.informationAlert("Information Dialog",null,"All appointments associated with a customer must be deleted prior to customer deletion.");
        }
    }

    /**
     * Navigates to the login page.
      * @param event This event is triggered when the logout button is clicked.
     * @throws IOException
     */
    @FXML
    public void logout(ActionEvent event) throws IOException {
        navigation.navigateToPage(event,"/schedulemanager/views/Login.fxml");
    }


    /**
     * Navigates to the update customer page and passes on customer info to that page for auto-population of fields.
      * @param event This event is triggered when the update button is clicked.
     * @throws IOException
     */
    @FXML
    public void updateCustomer(ActionEvent event) throws IOException {

        Customers selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            UpdateCustomerCntrl.setSelectedCustomer(selectedCustomer);
            scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/UpdateCustomer.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }else{
            alertInfoBox.informationAlert("No Customer Selected",null, "Select a customer to update.");
        }
    }

    /**
     * Navigates to Report: Customer Appointments page which shows the total number of customer appointments by type and month.
     * @param actionEvent This even is triggered when the Report Customer Appointment button is clicked.
     * @throws IOException
     */
    public void toReportCustomerAppt(ActionEvent actionEvent) throws IOException {
        navigation.navigateToPage(actionEvent,"/schedulemanager/views/ReportCustomerAppt.fxml");
    }

    /**
     * Navigates to the schedule by contact report page which shows the schedule by contact.
     * @param actionEvent This event is triggered when the Report: Contact Schedule button is clicked.
     * @throws IOException
     */
    public void toReportContactSchedule(ActionEvent actionEvent) throws IOException {
        navigation.navigateToPage(actionEvent,"/schedulemanager/views/ReportContactSchedule.fxml");
    }

    /**
     * Navigates to the Report: User Schedule page which shows the schedule of appointments by user.
     * @param actionEvent This event is triggered the the Report: User Schedule button is clicked.
     * @throws IOException
     */
    public void toUserSchedule(ActionEvent actionEvent) throws IOException {
        navigation.navigateToPage(actionEvent,"/schedulemanager/views/ReportUserSchedule.fxml");
    }
}
