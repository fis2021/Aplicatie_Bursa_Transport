package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MyOffersPageController {
    @FXML
    private Button returnButton;

    private String username;

    public void setUsername(String username)
    {
        this.username=username;
    }

    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/truckingHomePage.fxml"));
            Parent root = (Parent) loader.load();
             TruckingHomePageController log=loader.getController();
            log.setUserName(username);
            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
