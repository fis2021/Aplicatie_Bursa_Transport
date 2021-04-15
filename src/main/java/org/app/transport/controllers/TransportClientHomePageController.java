package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class TransportClientHomePageController {
    @FXML
     Button closeButton;
    @FXML
    private Button DisplayGoodsList;
    public void handle4(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void handleGoodsList(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/MyGoodsList.fxml"));
        Stage  window = (Stage)DisplayGoodsList.getScene().getWindow();
        window.setScene(new Scene(root, 500,400));

    }
}
