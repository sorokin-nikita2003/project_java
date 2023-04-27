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
import java.lang.reflect.Array;
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
        //slaunch();




        final Random random = new Random();
        int[][] player1 = new int[10][10];
        int[][] player2 = new int[10][10];
        int x = random.nextInt(9);
        int y = random.nextInt(9);
        player2[x][y] = 4;
        int ship = 4;
        String move2 = "";
        //System.out.println(Arrays.deepToString(player2));
        int move =  random.nextInt(3);
            try {
                switch (move) {
                    case (0):
                        player2[x + 1][y] = 4;
                        move2 = "right";
                        x = x + 1;
                        break;
                    case (1):
                        player2[x - 1][y] = 4;
                        move2 = "left";
                        x = x - 1;
                        break;
                    case (2):
                        player2[x][y + 1] = 4;
                        move2 = "up";
                        y = y + 1;
                        break;
                    case (3):
                        player2[x][y - 1] = 4;
                        move2 = "down";
                        y = y - 1;
                        break;
                }
            } catch (Exception e) {
                switch (move) {
                    case (0):
                        player2[x - 1][y] = 4;
                        move2 = "left";
                        x = x - 1;
                        break;
                    case (1):
                        player2[x + 1][y] = 4;
                        move2 = "right";
                        x = x + 1;
                        break;
                    case (2):
                        player2[x][y - 1] = 4;
                        move2 = "down";
                        y = y - 1;
                        break;
                    case (3):
                        player2[x][y + 1] = 4;
                        move2 = "up";
                        y = y + 1;
                        break;
                }
            }

        PrintArray(player2);
    }
    private static void PrintArray(int[][] Array){
        for (int i = 0; i < 10; i++) {  //идём по строкам
            for (int j = 0; j < 10; j++) { //идём по столбцам
                System.out.print(" " + Array[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }
}