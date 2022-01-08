package schedulemanager.controllers;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulemanager.database.AppointmentDao;
import schedulemanager.domain.TotalApptbyMonth;
import schedulemanager.services.JavaFXFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportCustomerApptCntrl implements Initializable {
    JavaFXFunctions navigation = new JavaFXFunctions();
    AppointmentDao appointmentDao = new AppointmentDao();

    @FXML
    private Label usernameLabel;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TableView<TotalApptbyMonth> monthTotalApptTable;

    @FXML
    private TableColumn<?,?> monthClmn;

    @FXML
    private TableColumn<?,?> totalApptClmn;


    /**
     * Initilazes the customer appointment report page.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> listOfType = appointmentDao.getListOfTypeOfAppts();
        usernameLabel.setText("Username: " + LoginCntrl.logedinUsername);
        typeComboBox.setItems(listOfType);
        typeComboBox.setValue("All Types");
        monthTotalApptTable.setItems(appointmentDao.getTotalApptByMonth("All Types"));
        monthClmn.setCellValueFactory(new PropertyValueFactory<>("month"));
        totalApptClmn.setCellValueFactory(new PropertyValueFactory<>("total"));


    }

    /**
     * Navigates to the login page.
      * @param actionEvent This event is triggered when the logout button is clicked.
     * @throws IOException
     */
    public void logout(ActionEvent actionEvent) throws IOException {
        navigation.navigateToPage(actionEvent,"/schedulemanager/views/Login.fxml");
    }

    /**
     * Updates the table view depending on the type selected in the combo box.
     * @param actionEvent This event is trigggered when a type is selected from the combo box.
     */
    public void typeSelected(ActionEvent actionEvent) {
       String type =  typeComboBox.getValue();
       ObservableList<TotalApptbyMonth> listTotalApptByMonth =  appointmentDao.getTotalApptByMonth(type);
       monthTotalApptTable.setItems(listTotalApptByMonth);
    }

    /**
     * Navigates to the home page.
      * @param actionEvent This event is triggered when the home button is clicked.
     * @throws IOException
     */
    public void returnToHomePage(ActionEvent actionEvent) throws IOException {
        navigation.navigateToPage(actionEvent,"/schedulemanager/views/Home.fxml");
    }
}
