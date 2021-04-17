package org.app.transport.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.app.transport.model.Good;
import org.app.transport.model.User;
import org.app.transport.services.UserService;

import java.io.IOException;
import java.util.Iterator;


public class MyGoodsList {
    @FXML
    private Button returnHome;
    @FXML
    private ListView<String> listView;
    private String userName1;

    public void handleReturn(MouseEvent mouseEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transportHomePage.fxml"));
            Parent root = (Parent) loader.load();
            TransportClientHomePageController log=loader.getController();
            log.setUserName(userName1);
            Stage window = (Stage) returnHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public void HandleView(MouseEvent mouseEvent) {
        User c = UserService.FindTheUser(userName1);
        String[] splits = c.getGood().split("/");
        for (String s : splits) {
           listView.getItems().add(s);
        }

    }
}

