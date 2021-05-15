package org.app.transport.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.model.User;
import org.app.transport.services.UserService;

import java.io.IOException;
import java.util.Arrays;

public class myTransactionsPageTransportClientController {
    @FXML
    private Button ReturnHome;
    @FXML
    private ListView<String> listView;
    @FXML
    private Text message;

    private String username;
    private String listElement;
    private int n = 0;
    private String LocT;
    private String LocF;
    private String listItem;

    public void setUsername(String username) {
        this.username = username;
        User c = UserService.FindTheUser(username);
        if(c.getGood().isEmpty()||c.getGood().compareTo("*")==0)
            message.setText("No element in the list");
        else {
            String[] splits = c.getGood().split("/");
            for (String s : splits) {
                System.out.println(Arrays.toString(splits));
                String[] split2 = s.split("\\|");
                String[] split3 = split2[0].split("~");
                if(split3[5].equals("Closed"))
                    listView.getItems().add(s);
            }
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    listItem = listView.getSelectionModel().getSelectedItem();
                }
            });
        }
    }

    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transportHomePage.fxml"));
            Parent root = (Parent) loader.load();
            TransportClientHomePageController log = loader.getController();
            log.setUserName(username);
            Stage window = (Stage) ReturnHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}