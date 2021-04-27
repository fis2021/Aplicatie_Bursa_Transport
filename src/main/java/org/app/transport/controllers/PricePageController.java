package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PricePageController {
    @FXML
    private Button returnButton;
    @FXML
    private DatePicker date;
    @FXML
    private TextField price;
    @FXML
    private TextArea info;
    private String username;
    private String listItem;
    private String LocT,LocF;
    private String goodInfo;
    @FXML
    private ChoiceBox<String> currency;
    public void setUserDetails(String username,String listItem,String LocF,String LocT,String goodInfo)
    {
        this.username=username;
        this.listItem=listItem;
        this.LocF=LocF;
        this.LocT=LocT;
        this.goodInfo=goodInfo;
    }
    @FXML
    public void initialize() {
        currency.getItems().addAll("lei", "euro");
    }
    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GoodList.fxml"));
            Parent root = (Parent) loader.load();
            GoodListController log=loader.getController();
            log.setUsername(username,LocF,LocT);
            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleClear(MouseEvent mouseEvent) {
       date.getEditor().clear();
        price.setText("");
        info.setText("");
    }
}
