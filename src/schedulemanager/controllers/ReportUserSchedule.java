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
import schedulemanager.domain.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportUserSchedule implements Initializable {



    Stage stage;
    Parent scene;

    @FXML
    private Label usernameLabel;
    @FXML
    private ComboBox<Users> userComboBox;
    @FXML
    private TableView<Appointments> scheduleTable;
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
     * Initializes the user schedule report page.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText("Username: " + LoginCntrl.logedinUsername);
        userComboBox.setItems(schedulemanager.database.UserTable.getAllUserId());
        try {
            userComboBox.setValue(schedulemanager.database.UserTable.getUsersByUserName(LoginCntrl.logedinUsername));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        scheduleTable.setItems(schedulemanager.database.AppointmentsTable.getApptUsingUsername(LoginCntrl.logedinUsername));

        apptIdClmn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleClmn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionClmn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startClmn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endClmn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdClmn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

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
     * Updates the schedule tableview to show associates appointments of the selected user.
     * @param actionEvent This event is triggered when a selection is made on the user combo box.
     */
    public void userSelected(ActionEvent actionEvent) {
        String userName = userComboBox.getValue().getUsername();

        scheduleTable.setItems(schedulemanager.database.AppointmentsTable.getApptUsingUsername(userName));
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
