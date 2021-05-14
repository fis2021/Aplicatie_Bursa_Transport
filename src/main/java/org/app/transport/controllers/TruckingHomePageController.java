package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TruckingHomePageController {
    @FXML
    private Button closeButton;
    @FXML
    private Button  GoodButton;
    @FXML
    private Button  TransactionsButton;
    @FXML
    private Button  OfferButton;
    private String userName;
   public void setUserName(String userName)
   {
       this.userName=userName;
   }

    public void handlecloseButton(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void handleList(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GoodList.fxml"));
            Parent root = (Parent) loader.load();
            GoodListController log=loader.getController();
            log.setUsername(userName,"Everywhere","Everywhere");
            Stage window = (Stage) GoodButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleOffer(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyOffersPage.fxml"));
            Parent root = (Parent) loader.load();
            MyOffersPageController log=loader.getController();
            log.setUsername(userName);
            Stage window = (Stage) OfferButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleTransactions(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyTransactionsPage.fxml"));
            Parent root = (Parent) loader.load();
            MyTransactionsPageController log=loader.getController();
            log.setUsername(userName);
            Stage window = (Stage) TransactionsButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
