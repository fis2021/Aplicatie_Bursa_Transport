package org.app.transport.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientGiveRatingController implements Initializable{
    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> rating;
    @FXML
    private Button ReturnHome;
    @FXML
    private Button GiveRating;
    private String username;
    private Text message;


    public void initialize(URL url, ResourceBundle rb){
        loadData();
    }

    private void loadData(){
        list.removeAll(list);
        String a = "1";
        String b = "2";
        String c = "3";
        String d = "4";
        String e = "5";
        list.addAll(a,b,c,d,e);
        rating.getItems().addAll(list);
    }

    public void setUsername(String userName)
    {
        this.username=userName;
    }
    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/myTransactionsPageTransportClient.fxml"));
            Parent root = (Parent) loader.load();
            MyTransactionsPageController log = loader.getController();
            log.setUsername(username);
            Stage window = (Stage) ReturnHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleGiveRating(MouseEvent mouseEvent) {
        String truckRating = rating.getValue();
        if(truckRating == null)
            message.setText("No item selected!");
        else{

        }
    }
}
