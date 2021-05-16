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

public class MyTransactionsPageController {
    @FXML
    public Button ClosedTransaction;
    @FXML
    private Button ReturnHome;
    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> listView2;
    @FXML
    private Text message;
    private String username;
    private String listElement;
    private String LocT;
    private String LocF;
    private int n = 0;
    @FXML
    private Button GiveRating;

    public void setUsername(String username) {
        this.LocF = LocF;
        this.LocT = LocT;
        this.username = username;
        User c = UserService.FindTheUser(username);

        String[] split = c.getGood().split("/");
        String b1, b2;

        for (String s : split) {
            if (s.compareTo("*") != 0 && s.compareTo("") != 0) {
                String[] split2 = s.split("\\|");
                String[] split3 = split2[0].split("~");

                if (split3[5].equals("Accepted")) {
                    b1 = split3[0] + " " + split3[1] + " " + split3[2] + " " + split3[3] + " " + split3[4];
                    listView.getItems().add(b1);
                } else if (split3[5].equals("Closed")) {
                    b2 = split3[0] + " " + split3[1] + " " + split3[2] + " " + split3[3] + " " + split3[4];
                    listView2.getItems().add(b2);
                }
            }
        }

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                n = listView.getSelectionModel().getSelectedIndex();
                listElement = listView.getSelectionModel().getSelectedItem();
            }
        });

        listView2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                n = listView2.getSelectionModel().getSelectedIndex();
                listElement = listView2.getSelectionModel().getSelectedItem();
            }
        });

    }

    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/truckingHomePage.fxml"));
            Parent root = (Parent) loader.load();
            TruckingHomePageController log = loader.getController();
            log.setUserName(username);
            Stage window = (Stage) ReturnHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleClose(MouseEvent mouseEvent) {
        if (listElement == null)
            message.setText("No item selected!");
        else {
            for (User user : UserService.userRepository.find())
                if (user.getRole().compareTo("Trucking operator") == 0) {
                    if (user.getGood().compareTo("*") != 0 && user.getGood().compareTo("") != 0) {
                        String[] split = user.getGood().split("/");
                        for (String s : split) {
                            String[] split2 = s.split("\\|");
                            String[] split3 = split2[0].split("~");
                            ClosedTransaction.setOnAction(e -> {
                                message.setText("You accepted the offer!!");
                                user.setClose(s);
                                UserService.updateUser(user, user.getUsername());
                            });
                            UserService.updateStatus(user, "Closed");
                            user.setClose(s);
                            System.out.println(Arrays.toString(split3));
                        }
                    }
                }
        }
    }

    public void handleRating(MouseEvent mouseEvent) {
        if (listElement == null)
            message.setText("No item selected!");
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TruckingGiveRating.fxml"));
                Parent root = (Parent) loader.load();
                TruckingGiveRatingController log = loader.getController();
                log.setUsername(username);
                Stage window = (Stage) GiveRating.getScene().getWindow();
                window.setScene(new Scene(root, 500, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
