package schedulemanager.controllers;

import schedulemanager.domain.Appointments;
import schedulemanager.domain.Contacts;
import schedulemanager.domain.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulemanager.util.alertMsg;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static schedulemanager.database.AppointmentsTable.*;


public class AddApptCntrl implements Initializable {


    Stage stage;
    Parent scene;

    @FXML
    private TextField appointmentIdText;

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

    public static int customerId = -1;

    public static void setCustomerIdInAddApptCntrl(int customerId){
        AddApptCntrl.customerId = customerId;
    }

    /**
     * Initializes add appointment page. It autopopulates the customerId and tableviews. It also populates the comboboxes.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText("Username: " + LoginCntrl.logedinUsername);
        customerIdText.setText(Integer.toString(customerId));

        try {
            contactBox.setItems(schedulemanager.database.ContactsTable.getAllContacts());
            userIdComboBox.setItems(schedulemanager.database.UserTable.getAllUserId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        startTimeBox.setItems(listOfTime());
        endTimeBox.setItems(listOfTime());

        String appointmentId = "appointmentId";
        String title = "title";
        String description = "description";
        String location = "location";
        String contactId = "contactId";
        String type = "type";
        String start = "start";
        String end = "end";
        String customerID = "customerId";
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
        customerIdClmn.setCellValueFactory(new PropertyValueFactory<>(customerID));
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
        customerIdClmn1.setCellValueFactory(new PropertyValueFactory<>(customerID));
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
        customerIdClmn2.setCellValueFactory(new PropertyValueFactory<>(customerID));
        userIdClmn2.setCellValueFactory(new PropertyValueFactory<>(userId));

    }

    /**
     *  Navigates to the update customer form.
     * @param event  activates when the cancel button is clicked
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
     * Returns to login page.
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
     * Adds new appointment to database if the appointment start and end is within office hours and does not overlap with other appointments.
     * There is a lambda function of interface alertMsg that alerts users when the end time is before the start time.
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


        int contactId  = contactBox.getValue().getContactId();
        int userId = userIdComboBox.getValue().getUserId();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        LocalTime startTime = startTimeBox.getValue();
        LocalTime endTime = endTimeBox.getValue();

        LocalDateTime start = LocalDateTime.of(startDate,startTime);
        LocalDateTime end = LocalDateTime.of(endDate,endTime);

       boolean inOfficeHours = checkIfTimeIsInOfficeHrs(start,end);
       boolean overlapsWithOtherAppts = checkIfNewApptOverlapsWithOtherAppts(start,end);

       //lambda use
       if(end.isBefore(start)){
           String textmsg = "End time is before start time";
           alertMsg msg = (a) -> {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Alert!");
               alert.setHeaderText(null);
               alert.setContentText(a);
               alert.showAndWait();
           };
           msg.showAlert(textmsg);


       }else if(inOfficeHours && !overlapsWithOtherAppts){
           addAppointment(title,description,location,type,start,end,customerId,userId,contactId);
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
