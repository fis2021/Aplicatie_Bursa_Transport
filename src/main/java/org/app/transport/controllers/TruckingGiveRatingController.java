package org.app.transport.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class TruckingGiveRatingController {
    @FXML
    private Button ReturnHome;
    private String username;
    public void setUsername(String userName)
    {
        this.username=userName;
    }
    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyTransactionsPage.fxml"));
            Parent root = (Parent) loader.load();
            MyTransactionsPageController log = loader.getController();
            log.setUsername(username);
            Stage window = (Stage) ReturnHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
