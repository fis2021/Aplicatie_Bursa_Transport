package org.app.transport.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.app.transport.model.User;
import org.app.transport.services.UserService;

import java.io.IOException;
import java.util.Arrays;

public class myTransactionsPageTransportClientController {

    private String username;

    public void setUsername(String username) {
        this.username = username;
        User c = UserService.FindTheUser(username);

    }

    public void handleReturn(MouseEvent mouseEvent) {

    }

}