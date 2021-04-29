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

public class MyOffersPageController {
    @FXML
    private Button returnButton;
    @FXML
    private TreeView<String> treeView;
   @FXML
   private Text message;
    private String username;

    public void setUsername(String username)
    {
        this.username=username;
        User c= UserService.FindTheUser(username);
        TreeItem<String> rootItem=new TreeItem<>("My proposed offers:");
        String [] split=c.getGood().split("/");
        for (String s : split)
        {
            if(s.compareTo("*")!=0&&s.compareTo("")!=0) {
                String[] split2 = s.split("\\|");
                String[] split3 = split2[0].split("~");
                String[] split4 = split2[1].split("-");
                TreeItem<String> branchItem = new TreeItem<>("The Offer was sent to user:" + split3[0]);
                TreeItem<String> leafItem0 = new TreeItem<>("Accept/Reject status");
                TreeItem<String> leafItem = new TreeItem<>("Information about good");
                TreeItem<String> leafItem1 = new TreeItem<>("Price and due date");
                TreeItem<String> leafItem2 = new TreeItem<>("Information about delivery");
                TreeItem<String> leaf0 = new TreeItem<>(split4[3] + ":" + split4[1] + "-" + split4[2] + "(" + split4[0] + ")");
                leafItem.getChildren().add(leaf0);
                TreeItem<String> leaf2 = new TreeItem<>("Only if the offer was accepted!");
                leafItem2.getChildren().add(leaf2);
                TreeItem<String> leaf3 = new TreeItem<>(split3[5]+"!");
               leafItem0.getChildren().add(leaf3);
                branchItem.getChildren().addAll(leafItem0, leafItem, leafItem1, leafItem2);
                rootItem.getChildren().add(branchItem);
            }
        }
        treeView.setRoot(rootItem);
   if(rootItem.getChildren().isEmpty())
       message.setText("You do not make any offer!");
    }

    public void handleReturn(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/truckingHomePage.fxml"));
            Parent root = (Parent) loader.load();
             TruckingHomePageController log=loader.getController();
            log.setUserName(username);
            Stage window = (Stage) returnButton.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
