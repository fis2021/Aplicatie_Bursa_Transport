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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class myTransactionsPageTransportClientControllerTest {
    public static final String APA = "apa";

    @Start
    public void handleGoodsList(Stage window) throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-example";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser(APA, APA, "Trucking operator", "apa~victor~250 euro~2021-05-20~No information!~Closed|15 tone-Chisinau-Timisoara-TransSRL-str. Albita 11");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/myTransactionsPageTransportClient.fxml"));
            Parent root = (Parent) loader.load();
            myTransactionsPageTransportClientController log = loader.getController();
            log.setUsername(APA);
            window.setScene(new Scene(root, 500, 400));
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        UserService.database.close();
    }

    @Test
    void testMyTransactionsList(FxRobot robot){
        robot.clickOn("#listViewTransactions").clickOn("apa victor 250 euro 2021-05-20 No information!");;
        Assertions.assertThat(robot.lookup("#listViewTransactions").queryListView().getItems().get(0)).isEqualTo("apa victor 250 euro 2021-05-20 No information!");
        robot.clickOn("#returnHomeMyTransactions");
    }
}