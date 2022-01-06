package schedulemanager.controllers;


import schedulemanager.domain.Countries;
import schedulemanager.domain.Divisions;
import schedulemanager.database.CountriesTable;
import schedulemanager.database.CustomersTable;
import schedulemanager.database.DivisionsTable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static schedulemanager.database.DivisionsTable.getAssociatedDivisions;

public class AddCustomerCntrl implements Initializable {


    Stage stage;
    Parent scene;

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
        countryComboBox.setItems(CountriesTable.getAllCountries());
        divisionBox.setItems(DivisionsTable.getAllDivisions());

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
     * Adds new customer to the database and checks if all fields are completed.
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("All fields must be completed");

                alert.showAndWait();
            } else {

                CustomersTable.addCustomer(name, address, zip, phone, divId);

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
     * Returns to login page.
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


    /**
     * Gets associated divisions based on country selection from countryComboBox.
      * @param countryId - Country ID from division ComboBox.
     */
    private void setDivisionBoxValues(int countryId) {

        ObservableList<Divisions> associatedDiv = getAssociatedDivisions(countryId);

        divisionBox.setItems(associatedDiv);
    }

    //Event handler to change division box values

    /**
     * Populates divisionBox with associated divisions of selected country.
     * @param actionEvent This event is triggered when a selection is made in the countryComboBox.
     */
    public void filterDivision(ActionEvent actionEvent) {
        int countryID = countryComboBox.getSelectionModel().getSelectedItem().getCountryId();
        setDivisionBoxValues(countryID);

        divisionBox.setValue(null);
    }


}
