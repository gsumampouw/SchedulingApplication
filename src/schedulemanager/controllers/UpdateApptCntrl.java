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
import schedulemanager.domain.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;

import static schedulemanager.database.AppointmentsTable.*;
import static schedulemanager.database.AppointmentsTable.listOfTime;
import static schedulemanager.database.ContactsTable.getAContact;
import static schedulemanager.database.UserTable.getUsersById;

public class UpdateApptCntrl implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField titleText;

    @FXML
    private TextField descriptionText;

    @FXML
    private TextField locationText;

    @FXML
    private TextField typeTextField;

    @FXML
    private Label usernameLabel;

    @FXML
    private TableView<Appointments> allApptTblView;

    @FXML
    private TableColumn<?, ?> apptIDClmn;

    @FXML
    private TableColumn<?, ?> titleClmn;

    @FXML
    private TableColumn<?, ?> descriptionClmn;

    @FXML
    private TableColumn<?, ?> locationclmn;

    @FXML
    private TableColumn<?, ?> contactClmn;

    @FXML
    private TableColumn<?, ?> typeClmn;

    @FXML
    private TableColumn<?, ?> startClmn;

    @FXML
    private TableColumn<?, ?> endClmn;

    @FXML
    private TableColumn<?, ?> customerIdClmn;

    @FXML
    private TableColumn<?, ?> userIdClmn;

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

    @FXML
    private TextField appointmentIdText;

    @FXML
    private ComboBox<Contacts> contactBox;

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<LocalTime> startTimeBox;
    @FXML
    private ComboBox<LocalTime> endTimeBox;
    @FXML
    private ComboBox<Users> userIdComboBox;

    private static Appointments selectedAppt;


    /**
     * Sets selected appointment object from the update customer page for use in the update appointment page.
     * This information is used to auto-populate fields in the update appointment form.
     * @param selectedAppt contains information on the selected appointment from the update customer page.
     */
    public static void setSelectedAppt(Appointments selectedAppt) {
        UpdateApptCntrl.selectedAppt = selectedAppt;
    }

    /**
     *Initializes the update appointment form and auto-populate fields and table views.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText("Username: " + LoginCntrl.logedinUsername);

        titleText.setText(selectedAppt.getTitle());
        descriptionText.setText(selectedAppt.getDescription());
        locationText.setText(selectedAppt.getLocation());
        typeTextField.setText(selectedAppt.getType());
        customerIdText.setText(Integer.toString(selectedAppt.getCustomerId()));
        appointmentIdText.setText(Integer.toString(selectedAppt.getAppointmentId()));
        try {
            contactBox.setItems(schedulemanager.database.ContactsTable.getAllContacts());
            contactBox.setValue(getAContact(selectedAppt.getContactId()));
            userIdComboBox.setItems(schedulemanager.database.UserTable.getAllUserId());
            userIdComboBox.setValue(getUsersById(selectedAppt.getUserId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        startDatePicker.setValue(selectedAppt.getStart().toLocalDate());
        endDatePicker.setValue(selectedAppt.getEnd().toLocalDate());

        startTimeBox.setItems(listOfTime());
        startTimeBox.setValue(selectedAppt.getStart().toLocalTime());
        endTimeBox.setItems(listOfTime());
        endTimeBox.setValue(selectedAppt.getEnd().toLocalTime());

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

        //populates all tab tableview
        try {
            allApptTblView.setItems(getAllAppt());
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
            currentMonthTblView.setItems(getMonthAppt());
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
            currWeekAptTblView.setItems(getWeekAppt());
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
     * Navigates to the update customer page.
     * @param event This event is triggered when the cancel button is clicked.
     * @throws IOException
     */
    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/UpdateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    /**
     * Navigates to the login page.
     * @param event This event is triggered when the logout button is clicked.
     * @throws IOException
     */
    @FXML
    public void logout(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * It updates the selected appointment in the database based on information in the update appointment page.
     * It checks if the appointment start and end time is within office hours and if it overlaps with other appointments.
     * Alerts are triggered if conditions are not met.
     * @param event This event is triggered when the save button is clicked.
     * @throws IOException
     */
    @FXML
    public void saveButton(ActionEvent event) throws IOException {
        /*get data from form and check if appointment start and end is within office hours and
        * does not overlap with other appointments. If so create appointment object.
        * then update appointment table by passing updated appointment object.*/

        String title = titleText.getText();
        String description = descriptionText.getText();
        String location = locationText.getText();
        String type = typeTextField.getText();
        int customerId = Integer.parseInt(customerIdText.getText());
        int appointmentId = Integer.parseInt(appointmentIdText.getText());

        int contactId  = contactBox.getValue().getContactId();
        int userId = userIdComboBox.getValue().getUserId();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        LocalTime startTime = startTimeBox.getValue();
        LocalTime endTime = endTimeBox.getValue();

        LocalDateTime start = LocalDateTime.of(startDate,startTime);
        LocalDateTime end = LocalDateTime.of(endDate,endTime);

       boolean inOfficeHours = checkIfTimeIsInOfficeHrs(start,end);
       boolean overlapsWithOtherAppts = checkIfUpdatedApptOverlapsWithOtherAppts(start,end,appointmentId);

       if(end.isBefore(start)){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Alert!");
           alert.setHeaderText(null);
           alert.setContentText("End time is before start time");

           alert.showAndWait();
       }else if(inOfficeHours && !overlapsWithOtherAppts){
           Appointments updatedAppt = new Appointments(appointmentId,title,description,location,type,start,end,customerId,userId,contactId);
           updateAppointment(updatedAppt);

           stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
           scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/UpdateCustomer.fxml"));
           stage.setScene(new Scene(scene));
           stage.show();

       } else if (!inOfficeHours){

           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Appointment is not in office hours");
           alert.setHeaderText(null);
           alert.setContentText("Appointment is not in office hours. Pick a different time.");

           alert.showAndWait();

       } else {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Appointment overlaps with another appointment");
           alert.setHeaderText(null);
           alert.setContentText("Appointment overlaps with another appointment. Pick a different time.");

           alert.showAndWait();
       }

    }

}
