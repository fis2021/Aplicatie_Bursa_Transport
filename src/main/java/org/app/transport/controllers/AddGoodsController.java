package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.controllers.LoginController;
import org.app.transport.model.Good;
import org.app.transport.model.User;
import org.app.transport.services.UserService;
import  org.app.transport.controllers.MyGoodsList;
import java.io.IOException;

public class AddGoodsController {
    @FXML
    private Button returnToHome;
    @FXML
    private TextField weight;
    @FXML
    private TextField locationFrom;
    @FXML
    private TextField locationTo;
    @FXML
    private TextField companyName;
    @FXML
    private TextField detailedAddress;
    @FXML
    private Text exception;
    private String username;
    public void handleReturn(MouseEvent mouseEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/transportHomePage.fxml"));
            Parent root = (Parent) loader.load();
            TransportClientHomePageController log=loader.getController();
            log.setUserName(username);
            Stage window = (Stage) returnToHome.getScene().getWindow();
            window.setScene(new Scene(root, 500, 400));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
public void setUser(String userName)
{
    this.username=userName;
}
    public void handleAdd(MouseEvent mouseEvent) {
       User c= UserService.FindTheUser(username);
       if(weight.getText().isEmpty()==true||locationFrom.getText().isEmpty()==true||locationTo.getText().isEmpty()==true||companyName.getText().isEmpty()==true||detailedAddress.getText().isEmpty()==true)
           exception.setText("All fields must be completed!");
       else {
           Good g = new Good(weight.getText(), locationFrom.getText(), locationTo.getText(), companyName.getText(), detailedAddress.getText());
           c.setSomething(g.toString());
           UserService.updateUser(c,username);
           for(User user:UserService.userRepository.find())
               if(user.getRole().compareTo("Trucking operator")==0) {
                   user.setSomething(g.toString());
                   UserService.updateUser(user,user.getUsername());
               }
           exception.setText("Your good is public!");
       }
    }
}
