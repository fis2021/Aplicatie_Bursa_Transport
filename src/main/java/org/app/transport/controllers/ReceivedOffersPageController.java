package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.model.User;
import org.app.transport.services.UserService;

import java.io.IOException;


public class ReceivedOffersPageController {
    @FXML
    private TreeView treeView;
    @FXML
    private Button returnButton;
    @FXML
    public Button acceptButton;
    @FXML
    public Button rejectButton;
    @FXML
    private Text message;
    private String username;

    public void setUsername(String username)
    {


        this.username=username;
        TreeItem<String> rootItem = new TreeItem<>("Received offers:");
        for(User user: UserService.userRepository.find())
            if(user.getRole().compareTo("Trucking operator")==0) {
                if (user.getGood().compareTo("*") != 0 && user.getGood().compareTo("") != 0)
                {
                String[] split = user.getGood().split("/");
                for (String s : split) {
                        String[] split2 = s.split("\\|");
                        String[] split3 = split2[0].split("~");
                        if(split3[0].compareTo(username)==0) {
                            TreeItem<String> branchItem = new TreeItem<>(split3[0] + " received an offer from:" + split3[1]);
                            TreeItem<String> leafItem = new TreeItem<>("Information about good");
                            TreeItem<String> leafItem1 = new TreeItem<>("Price and due date");
                            TreeItem<String> leafItem2 = new TreeItem<>("Information about trucking operator");
                            TreeItem<String> leaf0 = new TreeItem<>(split2[1]);
                            leafItem.getChildren().add(leaf0);
                            branchItem.getChildren().addAll(leafItem, leafItem1, leafItem2);
                            rootItem.getChildren().add(branchItem);

                            setButtonsDisabled();


                                treeView.getSelectionModel().selectedItemProperty().addListener((v, oldVal, newVal) -> {
                                    if (newVal.equals(branchItem)) {
                                        if(split3[5].compareTo("In pending") == 0) {

                                            setButtonsEnabled();

                                            message.setText("You have to accept or reject the offer!");

                                            acceptButton.setOnAction(e -> {
                                                message.setText("You accepted the offer!!");
                                                //split3[5] = "Accepted";
                                                user.setAccept(s);
                                                UserService.updateUser(user, user.getUsername());
                                                setButtonsDisabled();
                                            });


                                            rejectButton.setOnAction(e -> {
                                                message.setText("You rejected the offer!");
                                                //split[5] = "Rejected";
                                                user.setReject(s);
                                                UserService.updateUser(user, user.getUsername());
                                                setButtonsDisabled();
                                            });

                                        }else if(split3[5].compareTo("Accepted") == 0){
                                            setButtonsDisabled();
                                            message.setText("You accepted the offer!");

                                        }else if(split3[5].compareTo("Rejected") == 0){
                                            setButtonsDisabled();
                                            message.setText("You rejected the offer!");
                                        }
                                    }
                                });

                            }


                    }
                }
            }



        treeView.setRoot(rootItem);
        if(rootItem.getChildren().isEmpty())
            message.setText("You do not received any offer!");
    }


public void setButtonsDisabled(){
    acceptButton.setDisable(true);
    rejectButton.setDisable(true);
}

public void setButtonsEnabled(){
    acceptButton.setDisable(false);
    rejectButton.setDisable(false);
}

    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transportHomePage.fxml"));
            Parent root = (Parent) loader.load();
            TransportClientHomePageController log=loader.getController();
            log.setUserName(username);
            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
