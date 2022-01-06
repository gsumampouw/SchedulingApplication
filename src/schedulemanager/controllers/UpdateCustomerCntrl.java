package schedulemanager.controllers;

import schedulemanager.domain.Appointments;
import schedulemanager.domain.Countries;
import schedulemanager.domain.Customers;
import schedulemanager.database.CountriesTable;
import schedulemanager.database.CustomersTable;
import schedulemanager.database.DivisionsTable;
import schedulemanager.domain.Divisions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static schedulemanager.controllers.AddApptCntrl.setCustomerIdInAddApptCntrl;
import static schedulemanager.database.AppointmentsTable.*;
import static schedulemanager.database.CountriesTable.getACountry;
import static schedulemanager.database.DivisionsTable.getADivision;
import static schedulemanager.database.DivisionsTable.getAssociatedDivisions;

public class UpdateCustomerCntrl implements Initializable {


    Stage stage;
    Parent scene;

    private static Customers selectedCustomer;


    @FXML
    private TextField nameText;

    @FXML
    private ComboBox<Countries> countryComboBox;

    @FXML
    private ComboBox<Divisions> divisionBox;

    @FXML
    private TextField zipText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField addressText;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label usernameLabel;

    @FXML
    private TabPane appointmentTabPane;

    @FXML
    private TableView<Appointments> allApptTblView;

    @FXML
    private TableColumn<Appointments, Integer> apptIDClmn;

    @FXML
    private TableColumn<Appointments, String> titleClmn;

    @FXML
    private TableColumn<Appointments, String> descriptionClmn;

    @FXML
    private TableColumn<Appointments, String> locationclmn;

    @FXML
    private TableColumn<Appointments, String> contactClmn;

    @FXML
    private TableColumn<Appointments, String> typeClmn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> startClmn;

    @FXML
    private TableColumn<Appointments, LocalDateTime> endClmn;

    @FXML
    private TableColumn<Appointments, Integer> customerIdClmn;

    @FXML
    private TableColumn<Appointments, Integer> userIdClmn;

    @FXML
    private Tab allTab1;

    @FXML
    private TableView<Appointments> currentMonthTblView;

    @FXML
    private TableColumn<?, ?> apptIDClmn1;

    @FXML
    private TableColumn<?, ?> titleClmn1;

    @FXML
    private TableColumn<?, ?> descriptionClmn1;

    @FXML
    private TableColumn<?, ?> locationclmn1;

    @FXML
    private TableColumn<?, ?> contactClmn1;

    @FXML
    private TableColumn<?, ?> typeClmn1;

    @FXML
    private TableColumn<?, ?> startClmn1;

    @FXML
    private TableColumn<?, ?> endClmn1;

    @FXML
    private TableColumn<?, ?> customerIdClmn1;

    @FXML
    private TableColumn<?, ?> userIdClmn1;

    @FXML
    private Tab allTab2;

    @FXML
    private TableView<Appointments> currWeekAptTblView;

    @FXML
    private TableColumn<?, ?> apptIDClmn2;

    @FXML
    private TableColumn<?, ?> titleClmn2;

    @FXML
    private TableColumn<?, ?> descriptionClmn2;

    @FXML
    private TableColumn<?, ?> locationclmn2;

    @FXML
    private TableColumn<?, ?> contactClmn2;

    @FXML
    private TableColumn<?, ?> typeClmn2;

    @FXML
    private TableColumn<?, ?> startClmn2;

    @FXML
    private TableColumn<?, ?> endClmn2;

    @FXML
    private TableColumn<?, ?> customerIdClmn2;

    @FXML
    private TableColumn<?, ?> userIdClmn2;

    @FXML
    private TextField customerIdText;

    /**
     * Initializes the update customer form and auto-populates fields with selected customer information and the tableview with associated appointments.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String appointmentId = "appointmentId";
        String title = "title";
        String description = "description";
        String location = "location";
        String contactId = "contactId";
        String type = "type";
        String start = "start";
        String end = "end";
        String customerId = "customerId";
        String userId = "userId";

        usernameLabel.setText("Username: " + LoginCntrl.logedinUsername);

        //auto populate customer data
        customerIdText.setText(Integer.toString(selectedCustomer.getCustomerId()));
        nameText.setText(selectedCustomer.getCustomerName());
        zipText.setText(selectedCustomer.getPostalCode());
        phoneText.setText(selectedCustomer.getPhone());
        addressText.setText(selectedCustomer.getAddress());
        countryComboBox.setItems(CountriesTable.getAllCountries());
        divisionBox.setItems(DivisionsTable.getAllDivisions());
        Divisions customerDiv = getADivision(selectedCustomer.getDivisionId());
        Countries customerCountry = getACountry(customerDiv.getCountryId());
        countryComboBox.setValue(customerCountry);
        divisionBox.setValue(customerDiv);

        //populates all tab tableview
        try {
            allApptTblView.setItems(getAllAssociatedAppt(selectedCustomer.getCustomerId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        apptIDClmn.setCellValueFactory(new PropertyValueFactory<>(appointmentId));
        titleClmn.setCellValueFactory(new PropertyValueFactory<>(title));
        descriptionClmn.setCellValueFactory(new PropertyValueFactory<>(description));
        locationclmn.setCellValueFactory(new PropertyValueFactory<>(location));
        contactClmn.setCellValueFactory(new PropertyValueFactory<>(contactId));
        typeClmn.setCellValueFactory(new PropertyValueFactory<>(type));
        startClmn.setCellValueFactory(new PropertyValueFactory<>(start));
        endClmn.setCellValueFactory(new PropertyValueFactory<>(end));
        customerIdClmn.setCellValueFactory(new PropertyValueFactory<>(customerId));
        userIdClmn.setCellValueFactory(new PropertyValueFactory<>(userId));

        //populates month tab tableview
        try {
            currentMonthTblView.setItems(getMonthAssociatedAppt(selectedCustomer.getCustomerId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        apptIDClmn1.setCellValueFactory(new PropertyValueFactory<>(appointmentId));
        titleClmn1.setCellValueFactory(new PropertyValueFactory<>(title));
        descriptionClmn1.setCellValueFactory(new PropertyValueFactory<>(description));
        locationclmn1.setCellValueFactory(new PropertyValueFactory<>(location));
        contactClmn1.setCellValueFactory(new PropertyValueFactory<>(contactId));
        typeClmn1.setCellValueFactory(new PropertyValueFactory<>(type));
        startClmn1.setCellValueFactory(new PropertyValueFactory<>(start));
        endClmn1.setCellValueFactory(new PropertyValueFactory<>(end));
        customerIdClmn1.setCellValueFactory(new PropertyValueFactory<>(customerId));
        userIdClmn1.setCellValueFactory(new PropertyValueFactory<>(userId));

        //populates week tab tableview
        try {
            currWeekAptTblView.setItems(getWeekAssociatedAppt(selectedCustomer.getCustomerId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        apptIDClmn2.setCellValueFactory(new PropertyValueFactory<>(appointmentId));
        titleClmn2.setCellValueFactory(new PropertyValueFactory<>(title));
        descriptionClmn2.setCellValueFactory(new PropertyValueFactory<>(description));
        locationclmn2.setCellValueFactory(new PropertyValueFactory<>(location));
        contactClmn2.setCellValueFactory(new PropertyValueFactory<>(contactId));
        typeClmn2.setCellValueFactory(new PropertyValueFactory<>(type));
        startClmn2.setCellValueFactory(new PropertyValueFactory<>(start));
        endClmn2.setCellValueFactory(new PropertyValueFactory<>(end));
        customerIdClmn2.setCellValueFactory(new PropertyValueFactory<>(customerId));
        userIdClmn2.setCellValueFactory(new PropertyValueFactory<>(userId));

    }

    /**
     * Sets the selected customer object from the home page for use to auto populate fields in the update customer page.
      * @param selectedCustomer selected customer object from the home page.
     */
    public static void setSelectedCustomer(Customers selectedCustomer) {
        UpdateCustomerCntrl.selectedCustomer = selectedCustomer;
    }

    /**
     * Navigates to the add appointment page and passes on the customer id.
      * @param event This event is triggered when the add appointment button is clicked.
     * @throws IOException
     */
    @FXML
    public void addApptButton(ActionEvent event) throws IOException {


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        setCustomerIdInAddApptCntrl(selectedCustomer.getCustomerId());
        scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    /**
     * Navigates to the home page.
     * @param event This event is triggered when the cancel button is clicked.
     * @throws IOException
     */
    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/Home.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }


    /**
     * Removes the selected appointment from the database and notifies user.
     * @param event This event is triggered when the remove appointment button is clicked.
     */
    @FXML
    public void removeApptButton(ActionEvent event) {
        Tab selectedTab = appointmentTabPane.getSelectionModel().getSelectedItem();
        Node selectedContent = selectedTab.getContent().lookup("TableView");
        String tableviewID = selectedContent.getId();
        Appointments selectedAppt = null;
        try {
            if (tableviewID.equals("allApptTblView")) {
                selectedAppt = allApptTblView.getSelectionModel().getSelectedItem();
            }
            if (tableviewID.equals("currentMonthTblView")) {
                selectedAppt = currentMonthTblView.getSelectionModel().getSelectedItem();
            }
            if (tableviewID.equals("currWeekAptTblView")) {
                selectedAppt = currWeekAptTblView.getSelectionModel().getSelectedItem();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (selectedAppt != null) {
            deleteSelectedApptUsingApptId(selectedAppt.getAppointmentId());

            try {
                allApptTblView.setItems(getAllAssociatedAppt(selectedCustomer.getCustomerId()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Appointment was deleted.");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No appointment was selected.");

            alert.showAndWait();
        }


    }

    /**
     * Updates customer information in the database and notifies user if there are empty fields.
      * @param event
     * @throws IOException
     */
    @FXML
    public void saveButton(ActionEvent event) throws IOException {

        String name = nameText.getText();
        String zip = zipText.getText();
        String phone = phoneText.getText();
        String address = addressText.getText();

        try {
            int divId = divisionBox.getSelectionModel().getSelectedItem().getDivisionId();
            int customerId = Integer.parseInt(customerIdText.getText());

            if (name.isBlank() || zip.isBlank() || phone.isBlank() || address.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("All fields must be completed");

                alert.showAndWait();
            } else {

                Customers updatedcustomer = new Customers(customerId, name, address, zip, phone, divId);
                CustomersTable.updateCustomer(updatedcustomer);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/Home.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be completed");

            alert.showAndWait();

        }


    }

    /**
     * Navigates to the update appointment page ip an appointment is selected and passes on selected appointment information for use in auto-population of fields in the update appointment page.
     * @param event This event is triggered when the update appointment button is clicked.
     * @throws IOException
     */
    @FXML
    public void updateAptButton(ActionEvent event) throws IOException {

        Tab selectedTab = appointmentTabPane.getSelectionModel().getSelectedItem();
        Node selectedContent = selectedTab.getContent().lookup("TableView");
        String tableviewID = selectedContent.getId();
        Appointments selectedAppt = null;
        try {
            if (tableviewID.equals("allApptTblView")) {
                selectedAppt = allApptTblView.getSelectionModel().getSelectedItem();
            }
            if (tableviewID.equals("currentMonthTblView")) {
                selectedAppt = currentMonthTblView.getSelectionModel().getSelectedItem();
            }
            if (tableviewID.equals("currWeekAptTblView")) {
                selectedAppt = currWeekAptTblView.getSelectionModel().getSelectedItem();
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            schedulemanager.controllers.UpdateApptCntrl.setSelectedAppt(selectedAppt);
            scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/UpdateAppointment.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No appointment was selected!");

            alert.showAndWait();
        }

    }

    /**
     * * Navigates to the login page.
     * @param actionEvent This event is triggered when the logout button is clicked.
     * @throws IOException
     */
    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    //

    /**
     * Function to change division box values based on country id input
     * @param countryId Selected country id from the country combo box.
     */
    private void setDivisionBoxValues(int countryId) {

        ObservableList<Divisions> associatedDiv = getAssociatedDivisions(countryId);
        divisionBox.setItems(associatedDiv);
    }

    /**
     * Event handler to change division box values to show associated divisions based on selected country in country combo box.
     * @param actionEvent This event is triggered when a selection is made in the country combo box.
     */
    public void filterDivision(ActionEvent actionEvent) {
        int countryID = countryComboBox.getSelectionModel().getSelectedItem().getCountryId();
        setDivisionBoxValues(countryID);
        divisionBox.setValue(null);
    }


}
