package org.app.transport.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.services.UserService;

import java.io.IOException;

public class LoginController {
    @FXML
    private Text registrationMessage;
    @FXML
    private TextField usernameField;
    public void handleLogIn(ActionEvent actionEvent) {
        if(UserService.checkIsInDataBase(usernameField.getText())==true)
            registrationMessage.setText("Log in!");
        else registrationMessage.setText("No account!");
    }
    @FXML
    Button button3;
    public void handle3(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/firstpage.fxml"));
        Stage window = (Stage)button3.getScene().getWindow();
        window.setScene(new Scene(root, 500,400));
    }
}
