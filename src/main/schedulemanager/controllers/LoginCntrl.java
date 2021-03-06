package main.schedulemanager.controllers;

import main.schedulemanager.domain.Appointments;
import main.schedulemanager.database.UserDao;
import main.schedulemanager.domain.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.schedulemanager.services.Display;
import main.schedulemanager.services.JavaFXFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.*;


public class LoginCntrl implements Initializable {

    JavaFXFunctions navigation = new JavaFXFunctions();
    JavaFXFunctions alertInfoBox = new JavaFXFunctions();
    UserDao userDao = new UserDao();
    Logger logger = LogManager.getLogger(LoginCntrl.class);

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
    private String information_dialog;
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
    public void login(ActionEvent event) throws IOException {
        String username = usernameText.getText();
        String password = passwordText.getText();
        if (logger.isDebugEnabled()) {
            logger.debug("Checking login info for user");
        }
        Users databaseUser = null;

        information_dialog = "Information Dialog";
        try {
            databaseUser = userDao.getUsersByUserName(username);
            logedinUsername = databaseUser.getUsername();
            logedinUserID = databaseUser.getUserId();
        } catch (SQLException e) {
            logger.error("Error occured for user " +  databaseUser.getUserId() + "during login",e);
        } catch (NullPointerException e){
            logger.warn("user used wrong username or password", e);
            if (userLanguage.equals("fr")) {
                alertInfoBox.informationAlert(information_dialog,"Nom d'utilisateur ou mot de passe incorrect!",null );
            } else {
                alertInfoBox.informationAlert(information_dialog,"Wrong username or password!",null );

            }
        }

        loginTime = LocalDateTime.now();
        Timestamp time = Timestamp.valueOf(loginTime);
        String item = "User " + username + " made an unsuccessful login attempt at " +time+ "\n";

        if (databaseUser != null && databaseUser.getPassword().equals(password)) {

            item = "User " + logedinUsername + " successfully logged in at "  + time+"\n";

                navigation.navigateToPage(event, "/main/schedulemanager/views/Home.fxml");

            Display display = new Display();
            ObservableList<Appointments> listofApptIn15 = display.listApptIn15MinSinceLogIn(LoginCntrl.loginTime);

            if (!listofApptIn15.isEmpty()) {

                 String alertMessage = "There is an appointment within 15 minutes!";

                for (Appointments appointments : listofApptIn15) {
                    int apptId = appointments.getAppointmentId();
                    LocalDateTime start = appointments.getStart();
                    LocalDate startDate = start.toLocalDate();
                    LocalTime startTime = start.toLocalTime();

                    String appendedMessage = "\n Appointment ID: " + apptId + ", Start Date: " + startDate + ", Start Time: " + startTime;
                    alertMessage = alertMessage + appendedMessage;
                }
                alertInfoBox.informationAlert(information_dialog,null,alertMessage);
            }
        }
        String filename = "log\\login_activity.txt";
        FileWriter fwriter = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.print(item);
        outputFile.close();
    }

    public void navigateToSignUp(ActionEvent mouseEvent) throws IOException {
        navigation.navigateToPage(mouseEvent,"/main/schedulemanager/views/SignUp.fxml");
    }
}
