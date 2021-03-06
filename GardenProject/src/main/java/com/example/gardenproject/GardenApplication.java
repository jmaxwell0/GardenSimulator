package com.example.gardenproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GardenApplication extends Application {
    static final long MS = System.currentTimeMillis();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GardenApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150, 775);
        Garden.worldCreation();
        stage.setTitle("Garden");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Logger.startup(true, false);
        launch();
    }
}