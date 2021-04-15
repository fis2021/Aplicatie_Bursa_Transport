package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddGoodsController {
    @FXML
    private Button returnToHome;
    public void handleReturn(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/transportHomePage.fxml"));
        Stage window = (Stage)returnToHome.getScene().getWindow();
        window.setScene(new Scene(root, 500,400));
    }
}
