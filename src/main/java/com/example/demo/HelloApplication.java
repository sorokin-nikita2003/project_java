package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.example.demo.Logic.score_player2;

public class HelloApplication extends Application {
    protected static int time_sleep = 10000;

    //210000
    protected static MediaPlayer mediaPlayer;
    protected static double sliderValue = 100;
    static String[] songs = {"music/main_sound.mp3", "music/battle_theme.mp3"};

    public static Thread t = new Thread(new MyRunnable());
    protected static MediaPlayer mediaPlayer2 = new MediaPlayer(new Media(new File("music/buttonClick.mp3").toURI().toString()));
    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            int i =0;
              while (true) {
                  i += 1;
                  System.out.println(1);
                  try {
                      mediaPlayer.play();
                      Thread.sleep(time_sleep);
                  } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                  }
                  mediaPlayer.seek(Duration.ZERO);
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


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("frame_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.setTitle("Морской бой");
        stage.setScene(scene);
        stage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_4.fxml"));
        Parent root = loader.load();
        frame_4Controller controller = loader.getController();


        mediaPlayer = new MediaPlayer(new Media(new File(songs[0]).toURI().toString()));
//        Thread bgThread = new Thread(() -> {
//            while (true) {
//                System.out.println(1);
//                try {
//                    mediaPlayer.play();
//                    Thread.sleep(time_sleep);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                mediaPlayer.seek(Duration.ZERO);
//            }
//
//        });

        t.start();
        //bgThread.start();


        //controller.settVolume(mediaPlayer); // передача ссылки на экземпляр HelloApplication

        //mediaPlayer2 = new MediaPlayer(new Media(new File("music/jeleznaya-knopka-vyiklyucheniya1.mp3").toURI().toString()));


    }
    static int[][] player1 = new int[10][10];
    static int[][] player2 = new int[10][10];
    public static void main(String[] args) {
        launch();

        /**Logic logic = new Logic();
        logic.generate_ships(player2);
        logic.PrintArray(player2);
        logic.shot(player2);
        System.out.println(score_player2);
        logic.PrintArray(player2);
        System.out.println("второй шанс");
        logic.shot(player2);
        logic.PrintArray(player2);**/
    }
}