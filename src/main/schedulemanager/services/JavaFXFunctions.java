package main.schedulemanager.services;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXFunctions {
    Stage stage;
    Parent scene;
    Event event;

    public JavaFXFunctions() {
    }

    public void navigateToPage(Event event, String page) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(page));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void informationAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
