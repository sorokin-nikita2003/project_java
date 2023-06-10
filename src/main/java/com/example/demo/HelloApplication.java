package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.*;
import java.util.Scanner;

import static com.example.demo.Logic.WorkFile.*;

public class HelloApplication extends Application {
    protected static File file = new File("settings.txt");
    public static Stage Screan;
    // 4    3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{79, 104, 128, 152, 176, 200, 223, 248, 272, 296, 319, 344, 369, 392, 415, 440, 464, 488, 511, 536, 561}
    // 2     3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{282, 307, 330, 355, 377, 403, 426, 451, 474, 499, 522, 546, 569, 595, 618, 643, 665}
    protected static int score_player1 = 0;
    protected static int score_player2 = 0;
    protected static String name_player1 = "Игрок 1";
    protected static String name_player2 = "Игрок 2";
    protected static double sliderValue;
    protected static String theme_color;
    protected static boolean full_screan;
    protected static final int[] mas_x = {152, 201, 249, 297, 345, 392, 441, 488, 537, 584};
    protected static final int[] mas_y = {258, 306, 354, 402, 450, 498, 546, 594, 642, 690};
    protected static final int[] mas_x_turn = {79, 104, 128, 152, 176, 200, 223, 248, 272, 296, 319, 344, 369, 392, 415, 440, 464, 488, 511, 536, 561};
    protected static final int[] mas_y_turn = {282, 307, 330, 355, 377, 403, 426, 451, 474, 499, 522, 546, 569, 595, 618, 643, 665};
    static int[][] player1 = new int[10][10];
    static int[][] player2 = new int[10][10];
    protected static int player = 1;
    protected static int time_sleep = 210000;
    protected static boolean flag = false;
    protected static MediaPlayer mediaPlayer;
    static String[] songs = {"music/main_sound.mp3", "music/battle_theme.mp3"};

    Stage primaryStage = new Stage();

    protected Scene scene1 = new Scene(FXMLLoader.load(getClass().getResource("frame_1.fxml")));
    protected Scene scene2 = new Scene(FXMLLoader.load(getClass().getResource("frame_2.fxml")));
//    protected Scene scene3 = new Scene(FXMLLoader.load(getClass().getResource("frame_3.fxml")));
    protected Scene scene4 = new Scene(FXMLLoader.load(getClass().getResource("frame_4.fxml")));

    public static Thread t = new Thread(new MyRunnable());
    protected static MediaPlayer mediaPlayer2 = new MediaPlayer(new Media(new File("music/buttonClick.mp3").toURI().toString()));

    public HelloApplication() throws IOException {
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try{
              while (!t.isInterrupted()) {
                  mediaPlayer.play();
                      while (!(mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration()))){
                          Thread.sleep(1500);
                          if(flag){
                              flag = false;
                              break;
                          }
                      }
                  mediaPlayer.seek(Duration.ZERO);
              }
            }
            catch (InterruptedException e){

            }
        }
//        public void run() {  public static MediaPlayer getMediaPlayer2() {
//           // Код, который будет выполняться в потоке      return mediaPlayer2;
//        }  }
       }//protected MediaPlayer mediaPlayer2 = new MediaPlayer(new Media(new File("music/buttonClick.mp3").toURI().toString()));

    //private static MediaPlayer mediaPlayer2;
    /**public static MediaPlayer getMediaPlayer2() {
        return mediaPlayer2;
    }   **/
    // Метод для получения mediaPlayer
    @Override
    public void start(Stage stage) throws IOException {
//        Group ships = new Group();
//        BorderPane root = new BorderPane();
//        BorderPane.setAlignment(root, Pos.CENTER);


//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("frame_1.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
////        stage.centerOnScreen();
//        stage.setTitle("Морской бой");
////        stage.setMaximized(true);
//        //stage.setFullScreen(true);
//        stage.setScene(scene);
//        stage.show();
//        Screan = stage;
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_4.fxml"));
//        Parent root = loader.load();
//        frame_4Controller controller = loader.getController();
//
//        stage.setTitle("Морской бой");
//
//        Parent root = FXMLLoader.load(getClass().getResource("frame_1.fxml"));
//        Scene scene = new Scene(root);
//        stage.setFullScreen(true);
//        stage.setScene(scene);
//        stage.show();


        primaryStage.setScene(scene1);
        primaryStage.setFullScreen(full_screan);
        primaryStage.show();

        mediaPlayer = new MediaPlayer(new Media(new File(songs[0]).toURI().toString()));
        mediaPlayer.setVolume(sliderValue /100);
        t.start();

        //bgThread.start();
//        button2.setOnAction(event -> primaryStage.setScene(scene1));

        //controller.settVolume(mediaPlayer); // передача ссылки на экземпляр HelloApplication

        //mediaPlayer2 = new MediaPlayer(new Media(new File("music/jeleznaya-knopka-vyiklyucheniya1.mp3").toURI().toString()));


    }
//    public static void  (event -> primaryStage.setScene(scene2));
    public static void main(String[] args) {
        if (!file.exists()){
            create_file(file);
        }

        read_file(file);

        launch();
        t.interrupt();
        write_file(file);
        System.out.println("end");
    }
}