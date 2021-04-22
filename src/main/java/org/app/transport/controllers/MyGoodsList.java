package org.app.transport.controllers;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.model.Good;
import org.app.transport.model.User;
import org.app.transport.services.UserService;

import javax.swing.*;
import java.io.IOException;
import java.util.Iterator;


public class MyGoodsList {
    @FXML
    private Button returnHome;
    @FXML
    private ListView<String> listView;
    private String userName1;
    private String listItem;
@FXML
private Button EditButton;
@FXML
private Text message;
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
        User c = UserService.FindTheUser(userName1);
        if(c.getGood().isEmpty()||c.getGood().compareTo("*")==0)
            message.setText("No element in the list");
        else {
            String[] splits = c.getGood().split("/");
            for (String s : splits) {
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

    public void handleEdit(MouseEvent mouseEvent) {
        if(listItem==null)
            message.setText("No item selected!");
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/editGoods.fxml"));
                Parent root = (Parent) loader.load();
                EditGoodsController log = loader.getController();
                log.setUserNameListItem(listItem, userName1);
                Stage window = (Stage) EditButton.getScene().getWindow();
                window.setScene(new Scene(root, 500, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleDelete(MouseEvent mouseEvent) {

        if (listItem==null)
            message.setText("No item selected!");
        else {
            User c = UserService.FindTheUser(userName1);
            String[] splits = c.getGood().split("/");
            String b = "*";
            boolean sw = true;
            for (String s : splits) {
                if (s.compareTo(listItem) != 0) {
                    if (sw) {
                        b = b + s;
                        sw = false;
                    } else {
                        b = b + "/" + s;
                    }
                }
            }
            b = b.substring(1);
            c.set(b);
            UserService.updateUser(c, userName1);
            listView.getItems().remove(listItem);
            message.setText("The item was deleted");
        }
    }
}

