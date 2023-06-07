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

public class HelloApplication extends Application {
    // 4    3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{79, 104, 128, 152, 176, 200, 223, 248, 272, 296, 319, 344, 369, 392, 415, 440, 464, 488, 511, 536, 561}
    // 2     3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{282, 307, 330, 355, 377, 403, 426, 451, 474, 499, 522, 546, 569, 595, 618, 643, 665}
    protected static int score_player1 = 0;
    protected static int score_player2 = 0;
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
        t.start();
        //bgThread.start();


        //controller.settVolume(mediaPlayer); // передача ссылки на экземпляр HelloApplication

        //mediaPlayer2 = new MediaPlayer(new Media(new File("music/jeleznaya-knopka-vyiklyucheniya1.mp3").toURI().toString()));


    }
    public static void main(String[] args) {
        launch();
        t.interrupt();
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