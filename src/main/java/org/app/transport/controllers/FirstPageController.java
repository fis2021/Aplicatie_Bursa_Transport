package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {


    @FXML
    Button button1,button,LoginButton;

    public void handle1(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/register.fxml"));
        Stage  window = (Stage)button1.getScene().getWindow();
        window.setScene(new Scene(root, 500,300));
    }

    public void handle(MouseEvent mouseEvent) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    public void handleLogin(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Stage window = (Stage)LoginButton.getScene().getWindow();
        window.setScene(new Scene(root, 500,300));
    }
}

