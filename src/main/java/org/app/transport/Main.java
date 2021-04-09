package org.app.transport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.app.transport.services.FileSystemService;
import org.app.transport.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        initDirectory();
        UserService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("/firstpage.fxml"));
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
