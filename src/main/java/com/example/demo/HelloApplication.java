package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

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

        String musicFile = "StayTheNight.mp3";     // For example

        
    }
    static int[][] player1 = new int[10][10];
    static int[][] player2 = new int[10][10];
    public static void main(String[] args) {
        launch();

        Scanner in = new Scanner(System.in);
        Logic logic = new Logic();
        logic.generate_ships(player2);
        logic.PrintArray(player2);
        int X = in.nextInt();
        int Y = in.nextInt();
        System.out.println(logic.shot(X, Y, player2));
        logic.PrintArray(player2);
    }
}