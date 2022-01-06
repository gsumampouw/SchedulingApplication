package schedulemanager.controllers;

import schedulemanager.domain.Appointments;
import schedulemanager.database.UserTable;
import schedulemanager.domain.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

import static schedulemanager.database.AppointmentsTable.listApptIn15MinSinceLogIn;


public class LoginCntrl implements Initializable {


    Stage stage;
    Parent scene;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    private Label zoneIDLabel;

    @FXML
    private String userLocation;
    @FXML
    private String userLanguage;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;

    public static String logedinUsername;
    public static int logedinUserID;
    public static LocalDateTime loginTime;
    private static int numLoginAttmpt;


    /**
     * Initializes the login page. It checks the computer's language settings and translate the login form to show french or english.
     * To determine the user language settings  I looked at stackoverflow for the solution listed below.
     * Link:https://stackoverflow.com/questions/2469435/how-to-detect-operating-system-language-locale-from-java-code
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId systemId = (ZoneId.systemDefault());
        userLocation = systemId.toString();
        userLanguage = System.getProperty("user.language");

        zoneIDLabel.setText("User Location: " + userLocation);

        try {
            if (userLanguage.equals("fr")) {
                usernameLabel.setText("Nom d'utilisateur");
                passwordLabel.setText("le mot de passe");
                loginButton.setText("connexion");
                zoneIDLabel.setText("emplacement de l'utilisateur");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks the user input to see if the username and password matches the database and navigates to the home page. Based on the language settings alerts for unsuccessful login attempts are shown to user sin french or english.
     * It logs each login attempt  to an external text file. It also checks if there are any appointments coming up with in 15 minutes of logging in.
      * @param event This event is triggered when the login button is clicked.
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void login(ActionEvent event) throws IOException, SQLException {
        String username = usernameText.getText();
        String password = passwordText.getText();
        Users databaseUser = null;

        try {
            databaseUser = UserTable.getUsersByUserName(username);

            logedinUsername = databaseUser.getUsername();
            logedinUserID = databaseUser.getUserId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
            if (userLanguage.equals("fr")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Nom d'utilisateur ou mot de passe incorrect!");
                alert.setContentText("");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Wrong username or password!");
                alert.setContentText("");

                alert.showAndWait();
            }
        }

        loginTime = LocalDateTime.now();
        Timestamp time = Timestamp.valueOf(loginTime);

        String filename = "src/files/login_activity.txt";
        String item = "User " + username + " made an unsuccessful login attempt at " +time+ "\n";
        FileWriter fwriter = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fwriter);


        if (databaseUser != null && databaseUser.getPassword().equals(password)) {

            item = "User " + logedinUsername + " successfully logged in at "  + time+"\n";

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/schedulemanager/views/Home.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            ObservableList<Appointments> listofApptIn15 = listApptIn15MinSinceLogIn(LoginCntrl.loginTime);

            if (!listofApptIn15.isEmpty()) {

                 String alertMessage = "There is an appointment within 15 minutes!";


                for (int i = 0; i < listofApptIn15.size(); i++) {
                    int apptId = listofApptIn15.get(i).getAppointmentId();
                    LocalDateTime start = listofApptIn15.get(i).getStart();
                    LocalDate startDate = start.toLocalDate();
                    LocalTime startTime = start.toLocalTime();

                    String appendedMessage = "\n Appointment ID: " + apptId + ", Start Date: " + startDate + ", Start Time: " + startTime;
                   alertMessage = alertMessage + appendedMessage;



                }


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText(alertMessage);

                alert.showAndWait();

            }
        } /*else {




        }*/

        outputFile.print(item);
        outputFile.close();

    }


}
