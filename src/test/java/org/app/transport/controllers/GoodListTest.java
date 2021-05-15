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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class GoodListTest {
    public static final String APA = "apa";
    @Start
    public void handleGoodsList(Stage window) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser(APA,APA,"Transport client","3 tone-Arad-Timisoara-ROM-Str nr.1");
        UserService.addUser(APA+"2",APA,"Trucking operator","*");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GoodList.fxml"));
            Parent root = (Parent) loader.load();
            GoodListController log=loader.getController();
            log.setUsername(APA+"2","Everywhere","Everywhere");
            window.setScene(new Scene(root, 500, 400));
            window.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Test
    void GoodListTest(FxRobot robot) throws IncorrectUsername, UsernameAlreadyExistsException, IncorrectPassword, RoleException {
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems().get(0)).isEqualTo("apa:Arad-Timisoara(3 tone)");
        UserService.addUser(APA+1,APA,"Transport client","2 tone-Arad-Pecica-ROM-Str nr.1");
        assertThat(UserService.getAllUsers()).size().isEqualTo(3);
        robot.clickOn("#refresh");
        Assertions.assertThat(robot.lookup("#message").queryText()).hasText("The page was refreshed!");
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems().get(1)).isEqualTo("apa1:Arad-Pecica(2 tone)");

    }
}
