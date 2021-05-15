package org.app.transport.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.model.User;
import org.app.transport.UserService;

import java.io.IOException;

public class LoginController {
    @FXML
    private Text registrationMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button SignInButton;
    public void handleLogIn(ActionEvent actionEvent) throws IOException {
        if(UserService.checkIsInDataBase(usernameField.getText())==true)
        {
            User c=UserService.FindTheUser(usernameField.getText());

             if(UserService.encodePassword(usernameField.getText(),passwordField.getText()).compareTo(c.getPassword())==0)
             {
                 if (c.getRole().compareTo("Trucking operator") == 0) {
                     try {
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/truckingHomePage.fxml"));
                         Parent root = (Parent) loader.load();
                         TruckingHomePageController log=loader.getController();
                         log.setUserName(usernameField.getText());
                         Stage window = (Stage) SignInButton.getScene().getWindow();
                         window.setScene(new Scene(root, 500, 400));
                     } catch (IOException e)
                     {
                         e.printStackTrace();
                     }
                 }
                 if (c.getRole().compareTo("Transport client") == 0) {
                     try {
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/transportHomePage.fxml"));
                         Parent root = (Parent) loader.load();
                         TransportClientHomePageController log=loader.getController();
                         log.setUserName(usernameField.getText());
                         Stage window = (Stage) SignInButton.getScene().getWindow();
                         window.setScene(new Scene(root, 500, 400));
                     } catch (IOException e)
                     {
                     e.printStackTrace();
                     }
                 }
             }
             else registrationMessage.setText("incorrect password!");

        }
        else registrationMessage.setText("No account!");
    }
    @FXML
    Button button3;
    public void handle3(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/firstpage.fxml"));
        Stage window = (Stage)button3.getScene().getWindow();
        window.setScene(new Scene(root, 500,400));
    }
    public String getUsernameField()
    {
        return usernameField.getText();
    }
}
