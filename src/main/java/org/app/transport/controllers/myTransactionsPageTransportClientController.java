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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.model.User;
import org.app.transport.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class myTransactionsPageTransportClientController {
    @FXML
    private Button ReturnHome;
    @FXML
    private Button GiveRating;
    @FXML
    private ListView<String> listView, listView2;
    @FXML
    private Text message;

    private String username;
    private String listElement;
    private int n = 0;
    private String rating;

    public void setUsername(String username) {
        this.username = username;
        User c = UserService.FindTheUser(username);


        String b1, b2;

        List<User> all = UserService.getAllUsers();

        for (User user : all) {

            if (user.getRole().compareTo("Trucking operator") == 0){
                String[] split = user.getGood().split("/");
                for(String sOperator:split){
                    String[] split2 = sOperator.split("\\|");
                    String[] split3 = split2[0].split("~");
                    if(split3[0].compareTo(c.getUsername()) == 0) {
                        rating = split3[5].substring(split3[5].length());

                            split3[5] = split3[5].substring(0, split3[5].length() - 1);
                            if (split3[5].length() > 6)
                                split3[5] = split3[5].substring(0, split3[5].length() - 1);
                            if (split3[5].equals("Closed")) {
                                b1 = split3[0] + " " + split3[1] + " " + split3[2] + " " + split3[3] + " " + split3[4] + rating;
                                listView2.getItems().add(b1);
                                System.out.println(rating);
                            }else {
                                b2 = split3[0] + " " + split3[1] + " " + split3[2] + " " + split3[3] + " " + split3[4] + rating;
                                listView.getItems().add(b2);
                                System.out.println(rating);
                            }

                    }
                }
            }

        }


            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed (ObservableValue < ? extends String > observableValue, String s, String t1){
                n = listView.getSelectionModel().getSelectedIndex();
                listElement = listView.getSelectionModel().getSelectedItem();
            }
        });

        listView2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed (ObservableValue < ? extends String > observableValue, String s, String t1){
                n = listView.getSelectionModel().getSelectedIndex();
                listElement = listView.getSelectionModel().getSelectedItem();
            }
        });
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

    public void handleRating(MouseEvent mouseEvent) {
        if (listElement == null)
            message.setText("No item selected!");
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClientGiveRating.fxml"));
                Parent root = (Parent) loader.load();
                ClientGiveRatingController log = loader.getController();
                log.setUsername(username);
                Stage window = (Stage) GiveRating.getScene().getWindow();
                window.setScene(new Scene(root, 500, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}