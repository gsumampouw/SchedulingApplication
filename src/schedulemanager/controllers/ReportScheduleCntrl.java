package schedulemanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulemanager.domain.Appointments;
import schedulemanager.domain.Contacts;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportScheduleCntrl implements Initializable {
    public Button logoutBtn;
    Stage stage;
    Parent scene;

    @FXML
    private Label usernameLabel;

    @FXML
    private TableView<Appointments> scheduleTable;
    @FXML
    private ComboBox<Contacts> contactComboBox;
    @FXML
    private TableColumn<?,?> apptIdClmn;
    @FXML
    private TableColumn<?,?> titleClmn;
    @FXML
    private TableColumn<?,?> typeClmn;
    @FXML
    private TableColumn<?,?> descriptionClmn;
    @FXML
    private TableColumn<?,?> startClmn;
    @FXML
    private TableColumn<?,?> endClmn;
    @FXML
    private TableColumn<?,?> customerIdClmn;


    /**
     * Initializes the contact schedule report page.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText("Username: " + LoginCntrl.logedinUsername);
        try {
            contactComboBox.setItems(schedulemanager.database.ContactsTable.getAllContacts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        scheduleTable.setItems(schedulemanager.database.AppointmentsTable.getApptUsingContactName("All"));

        apptIdClmn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleClmn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionClmn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startClmn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endClmn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdClmn.setCellValueFactory(new PropertyValueFactory<>("customerId"));


    }

    /**
     * Updates the schedule table to show the associated appointments based on the selected contact name.
     * @param actionEvent This event is triggered when a selection is made in the contact combo box.
     */
    public void contactSelected(ActionEvent actionEvent) {
        String contactName = contactComboBox.getValue().getContactName();

        scheduleTable.setItems(schedulemanager.database.AppointmentsTable.getApptUsingContactName(contactName));

    }

    /**
     * Navigates to the login page.
     * @param actionEvent This event is triggered when the logout button is clicked.
     * @throws IOException
     */
    public void logout(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Navigates to the home page.
     * @param actionEvent This event is triggered when the home button is clicked.
     * @throws IOException
     */
    public void returnToHome(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/Home.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
