package org.app.transport.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.app.transport.FileSystemService;
import org.app.transport.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class MyGoodsListTest {
    public static final String APA = "apa";
    @Start
    public void handleGoodsList(Stage window) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser(APA,APA,"Transport client","3 tone-Arad-Timisoara-ROM-str.1");
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
    @Test
    void testMyGoodList(FxRobot robot){
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems().get(0)).isEqualTo("3 tone-Arad-Timisoara-ROM-str.1");
        robot.clickOn("#EditButton");
        Assertions.assertThat(robot.lookup("#message").queryText()).hasText("No item selected!");
        robot.clickOn("#listView").clickOn("3 tone-Arad-Timisoara-ROM-str.1");
        robot.clickOn("#EditButton");
        robot.clickOn("#weight");
        robot.write(APA);
        robot.clickOn("#locationFrom");
        robot.write(APA);
        robot.clickOn("#locationTo");
        robot.write(APA);
        robot.clickOn("#companyName");
        robot.write(APA);
        robot.clickOn("#detailedAddress");
        robot.write(APA);
        robot.clickOn("#addInList");
        robot.clickOn("#returnToHome");
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems().get(0)).isEqualTo("3 toneapa-Aradapa-Timisoaraapa-ROMapa-str.1apa");
    }
}
