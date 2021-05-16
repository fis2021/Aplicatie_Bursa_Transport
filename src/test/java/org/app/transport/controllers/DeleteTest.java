package org.app.transport.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.app.transport.FileSystemService;
import org.app.transport.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class DeleteTest {
    public static final String APA = "apa";
    @Start
    public void handleGoodsList(Stage window) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser(APA,APA,"Transport client","buna");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MyGoodsList.fxml"));
            Parent root = (Parent) loader.load();
            MyGoodsList log=loader.getController();
            log.setUserName1(APA);
            window.setScene(new Scene(root, 500, 400));
            window.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @AfterEach
    void tearDown() {
        UserService.database.close();
    }
    @Test
    void deleteTest(FxRobot robot)
    {
        robot.clickOn("#deleteButton");
        Assertions.assertThat(robot.lookup("#message").queryText()).hasText("No item selected!");
        robot.clickOn("#listView").clickOn("buna");
        robot.clickOn("#deleteButton");
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems()).isEmpty();
    }
}
