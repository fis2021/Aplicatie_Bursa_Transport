package org.app.transport.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.app.transport.FileSystemService;
import org.app.transport.UserService;
import org.app.transport.exceptions.IncorrectPassword;
import org.app.transport.exceptions.IncorrectUsername;
import org.app.transport.exceptions.RoleException;
import org.app.transport.exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class LoginTest {
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }
    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
    @Test
    void testLogin(FxRobot robot) throws IncorrectUsername, UsernameAlreadyExistsException, IncorrectPassword, RoleException {
        UserService.addUser(USERNAME,PASSWORD,"Transport client","*");
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        robot.clickOn("#username");
        robot.write("use");
        robot.clickOn("#password");
        robot.write("passwor");
        robot.clickOn("#signUpButton");
        Assertions.assertThat(robot.lookup("#registrationMessage").queryText()).hasText("No account!");
        robot.clickOn("#username");
        robot.write("r");
        robot.clickOn("#signUpButton");
        Assertions.assertThat(robot.lookup("#registrationMessage").queryText()).hasText("incorrect password!");
    }
}
