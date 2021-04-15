package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TruckingHomePageController {
    @FXML
    private Button closeButton;
    public void GetColor(MouseEvent mouseEvent) {
    }

    public void handlecloseButton(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
