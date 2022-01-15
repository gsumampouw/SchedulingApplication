package main.schedulemanager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.schedulemanager.database.UserDao;
import main.schedulemanager.domain.Users;
import main.schedulemanager.services.JavaFXFunctions;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpCntrl {

    @FXML
    private TextField newUsername;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
    private JavaFXFunctions navigate = new JavaFXFunctions();
    private JavaFXFunctions alert = new JavaFXFunctions();

    public void addNewUser(ActionEvent actionEvent) throws SQLException, IOException {
        String username = newUsername.getText().toLowerCase();
        String password1 = newPassword.getText();
        String password2 = confirmPassword.getText();

        UserDao userDao = new UserDao();
        Users user = null;
        try {
            user = userDao.getUsersByUserName(username);

        if( user != null){
            alert.informationAlert("Warning",null,"Username already exists. Choose a different username.");
        }else if(!password1.equals(password2)){
            alert.informationAlert("Warning",null, "Password does not match");
        }else{
            userDao.addNewUser(username,password1);
            alert.informationAlert("Welcome!", null, "New user added.");
            navigate.navigateToPage(actionEvent,"/main/schedulemanager/views/Login.fxml");
        }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void returnToLogin(ActionEvent actionEvent) throws IOException {
        navigate.navigateToPage(actionEvent,"/main/schedulemanager/views/LoginCntrl.fxml");
    }
}
