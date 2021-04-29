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
    @FXML
    private Button addgoods;
    @FXML
    private Button receiveButton;
    private String userName;
    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    public void handle4(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void handleGoodsList(MouseEvent mouseEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyGoodsList.fxml"));
            Parent root = (Parent) loader.load();
            MyGoodsList log=loader.getController();
            log.setUserName1(userName);
            Stage window = (Stage) DisplayGoodsList.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void handleAddgoods(MouseEvent mouseEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddGoods.fxml"));
            Parent root = (Parent) loader.load();
            AddGoodsController log=loader.getController();
            log.setUser(userName);
            Stage window = (Stage) addgoods.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleReceive(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReceivedOffersPage.fxml"));
            Parent root = (Parent) loader.load();
            ReceivedOffersPageController log=loader.getController();
            log.setUsername(userName);
            Stage window = (Stage) receiveButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
