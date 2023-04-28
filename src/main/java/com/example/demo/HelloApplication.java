package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Group ships = new Group();
//        BorderPane root = new BorderPane();
//        BorderPane.setAlignment(root, Pos.CENTER);


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("frame_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.setTitle("Морской бой");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        Logic logic = new Logic();
        logic.generate_ships();
    }
}