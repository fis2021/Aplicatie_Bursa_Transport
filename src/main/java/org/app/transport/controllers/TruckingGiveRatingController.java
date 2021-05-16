package org.app.transport.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import org.app.transport.UserService;
import org.app.transport.exceptions.UsernameAlreadyExistsException;
import org.app.transport.model.User;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TruckingGiveRatingController implements Initializable {
    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> rating;
    @FXML
    private Button ReturnHome;
    @FXML
    private Button GiveTruckingRating;
    private String username;
    private Text message;

    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    private void loadData() {
        list.removeAll(list);
        String a = "1";
        String b = "2";
        String c = "3";
        String d = "4";
        String e = "5";
        list.addAll(a, b, c, d, e);
        rating.getItems().addAll(list);
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyTransactionsPage.fxml"));
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
        if (truckRating == null)
            message.setText("No item selected!");
        else
            for (User user : UserService.userRepository.find())
                if (user.getRole().compareTo("Trucking operator") == 0) {
                    if (user.getGood().compareTo("*") != 0 && user.getGood().compareTo("") != 0) {
                        String[] split = user.getGood().split("/");
                        for (String s : split) {
                            String[] split2 = s.split("\\|");
                            String[] split3 = split2[0].split("~");
                            GiveTruckingRating.setOnAction(e -> {
                                message.setText("You give the rating!!");
                                user.setClose(s);
                                UserService.updateUser(user, user.getUsername());
                            });
                            UserService.updateStatus(user, "Closed" + rating.getValue());
                            user.setClose(s);
                            System.out.println(rating.getValue());
                            System.out.println(Arrays.toString(split3));
                        }
                    }
                }
    }

}

