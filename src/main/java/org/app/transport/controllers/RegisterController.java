package org.app.transport.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.app.transport.exceptions.IncorrectPassword;
import org.app.transport.exceptions.IncorrectUsername;
import org.app.transport.exceptions.RoleException;
import org.app.transport.exceptions.UsernameAlreadyExistsException;
import org.app.transport.UserService;

import java.io.IOException;

public class RegisterController {
    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Transport client", "Trucking operator");
    }

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue(),"*");
            registrationMessage.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
        catch (IncorrectUsername e)
        {
            registrationMessage.setText(e.getMessage());
        }
        catch (IncorrectPassword e)
        {
            registrationMessage.setText(e.getMessage());
        }
        catch (RoleException e)
        {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    Button button2;

    public void handle2(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/firstpage.fxml"));
        Stage window = (Stage)button2.getScene().getWindow();
        window.setScene(new Scene(root, 500,400));
    }

}
