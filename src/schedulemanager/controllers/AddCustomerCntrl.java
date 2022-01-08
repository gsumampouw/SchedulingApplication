package schedulemanager.controllers;


import schedulemanager.database.AppointmentDao;
import schedulemanager.domain.Countries;
import schedulemanager.domain.Divisions;
import schedulemanager.database.CountryDao;
import schedulemanager.database.CustomerDao;
import schedulemanager.database.DivisionDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import schedulemanager.services.JavaFXFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddCustomerCntrl implements Initializable {

    JavaFXFunctions navigation = new JavaFXFunctions();
    JavaFXFunctions alertInfoBox = new JavaFXFunctions();
    CountryDao countryDao = new CountryDao();
    CustomerDao customerDao = new CustomerDao();
    DivisionDao divisionDao = new DivisionDao();

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
    private Label usernameLabel;

    @FXML
    private TextField customerIdText;


    /**
     * Initializes add customer page.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usernameLabel.setText("Username: " + LoginCntrl.logedinUsername);
        countryComboBox.setItems(countryDao.getAllCountries());
        divisionBox.setItems(divisionDao.getAllDivisions());

    }

    /**
     * Navigates to the home page.
     *
     * @param event This event is triggered when the cancel button is clicked.
     * @throws IOException
     */
    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
/*        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/Home.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();*/

        navigation.navigateToPage(event, "/schedulemanager/views/Home.fxml");

    }

    /**
     * Adds new customer to the database and checks if all fields are completed.
     *
     * @param event This event is triggered when the save button is clicked.
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

            if (name.isBlank() || zip.isBlank() || phone.isBlank() || address.isBlank()) {

                alertInfoBox.informationAlert("Information Dialog", null, "All fields must be completed");
            } else {
                customerDao.addCustomer(name, address, zip, phone, divId);
                navigation.navigateToPage(event, "/schedulemanager/views/Home.fxml");
            }
        } catch (Exception e) {
            e.printStackTrace();
            alertInfoBox.informationAlert("Information Dialog", null, "All fields must be completed");
        }
    }

    /**
     * Returns to login page.
     *
     * @param actionEvent This event is triggered when the logout button is clicked.
     * @throws IOException
     */
    @FXML
    public void logout(ActionEvent actionEvent) throws IOException {
        navigation.navigateToPage(actionEvent,"/schedulemanager/views/Login.fxml");
    }

    /**
     * Gets associated divisions based on country selection from countryComboBox.
     *
     * @param countryId - Country ID from division ComboBox.
     */
    private void setDivisionBoxValues(int countryId) {

        ObservableList<Divisions> associatedDiv = divisionDao.getAssociatedDivisions(countryId);
        divisionBox.setItems(associatedDiv);
    }

    //Event handler to change division box values
    /**
     * Populates divisionBox with associated divisions of selected country.
     *
     * @param actionEvent This event is triggered when a selection is made in the countryComboBox.
     */
    public void filterDivision(ActionEvent actionEvent) {
        int countryID = countryComboBox.getSelectionModel().getSelectedItem().getCountryId();
        setDivisionBoxValues(countryID);

        divisionBox.setValue(null);
    }


}
