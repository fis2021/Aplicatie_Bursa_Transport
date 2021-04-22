package org.app.transport.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.app.transport.model.User;
import org.app.transport.services.UserService;

import java.io.IOException;

public class GoodListController {
    @FXML
    private Button ReturnHome;
    @FXML
    private ListView<String> listView;
    private String username;
    private String listElement;
    public void setUsername(String username)
    {
        this.username=username;
        String[] splits;
        String [] splits2;
        String b;
        for(User user: UserService.userRepository.find())
            if(user.getRole().compareTo("Transport client")==0) {
                splits=user.getGood().split("/");
                for (String s : splits) {
                    if(s.compareTo("*")!=0&&s.compareTo("")!=0) {
                        splits2 = s.split("-");
                        b=splits2[3]+":"+splits2[1]+"-"+splits2[2];
                        listView.getItems().add(b);
                    }
                }
            }
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                listElement = listView.getSelectionModel().getSelectedItem();
            }
        });

    }

    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/truckingHomePage.fxml"));
            Parent root = (Parent) loader.load();
            TruckingHomePageController log=loader.getController();
            log.setUserName(username);
            Stage window = (Stage) ReturnHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
