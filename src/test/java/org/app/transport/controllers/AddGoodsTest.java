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

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class AddGoodsTest {

    public static final String APA = "apa";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }
    @Start
    public void handleAddgoods(Stage window) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddGoods.fxml"));
            Parent root = (Parent) loader.load();
            AddGoodsController log=loader.getController();
            log.setUser(APA);
            window.setScene(new Scene(root, 500, 400));
            window.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Test
    void testAddGood(FxRobot robot) throws IncorrectUsername, UsernameAlreadyExistsException, IncorrectPassword, RoleException {
        UserService.addUser(APA,APA,"Transport client","*");
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        robot.clickOn("#addInList");
        Assertions.assertThat(robot.lookup("#exception").queryText()).hasText("All fields must be completed!");
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
        Assertions.assertThat(robot.lookup("#exception").queryText()).hasText("Your good is public!");
        assertThat(UserService.getAllUsers().get(0).getGood()).isEqualTo(APA+"-"+APA+"-"+APA+"-"+APA+"-"+APA);

    }
}