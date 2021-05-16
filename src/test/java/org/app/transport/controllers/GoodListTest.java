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
import org.junit.jupiter.api.AfterEach;
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
    @AfterEach
    void tearDown() {
        UserService.database.close();
    }
    @Test
    void GoodListTest(FxRobot robot) throws IncorrectUsername, UsernameAlreadyExistsException, IncorrectPassword, RoleException {
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems().get(0)).isEqualTo("apa:Arad-Timisoara(3 tone)");
        UserService.addUser(APA+1,APA,"Transport client","2 tone-Arad-Pecica-ROM-Str nr.1");
        assertThat(UserService.getAllUsers()).size().isEqualTo(3);
        robot.clickOn("#refresh");
        Assertions.assertThat(robot.lookup("#message").queryText()).hasText("The page was refreshed!");
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems().get(1)).isEqualTo("apa1:Arad-Pecica(2 tone)");
        robot.clickOn("#filter");
        robot.clickOn("#choiceBox1").clickOn("Arad");
        robot.clickOn("#choiceBox2").scroll(100);
        robot.clickOn("Timisoara");
        robot.clickOn("#filter");
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems().get(0)).isEqualTo("apa:Arad-Timisoara(3 tone)");
        Assertions.assertThat(robot.lookup("#listView").queryListView().getItems().size()).isEqualTo(1);
        robot.clickOn("#ProposeButton");
        Assertions.assertThat(robot.lookup("#message").queryText()).hasText("No item selected!");
        robot.clickOn("#listView").clickOn("apa:Arad-Timisoara(3 tone)");
        robot.clickOn("#ProposeButton");
        robot.clickOn("#price");
        robot.write("130");
        robot.clickOn("#currency").clickOn("lei");
        robot.clickOn("#date");
        robot.write("12/05/2021");
        robot.clickOn("#clear");
        robot.clickOn("#offerButton");
        Assertions.assertThat(robot.lookup("#message").queryText()).hasText("The fields must be completed!");
        robot.clickOn("#date");
        robot.write("5/20/2021");
        robot.clickOn("#offerButton");
        robot.clickOn("#price");
        robot.write("130");
        robot.clickOn("#offerButton");
        Assertions.assertThat(robot.lookup("#message").queryText()).hasText("Your offer will be send!");
        assertThat(UserService.getAllUsers().get(1).getGood()).isEqualTo("apa~apa2~130 lei~2021-05-20~No information!~In pending|3 tone-Arad-Timisoara-ROM-Str nr.1");
    }
}
