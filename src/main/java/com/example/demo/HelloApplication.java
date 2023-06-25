package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.util.Objects;

import static com.example.demo.Logic.WorkFile.*;

public class HelloApplication extends Application {
    protected static File file = new File("settings.txt");

    // 4    3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{79, 104, 128, 152, 176, 200, 223, 248, 272, 296, 319, 344, 369, 392, 415, 440, 464, 488, 511, 536, 561}
    // 2     3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{282, 307, 330, 355, 377, 403, 426, 451, 474, 499, 522, 546, 569, 595, 618, 643, 665}
    protected static int score_player1 = 0;
    protected static int score_player2 = 0;
    protected static String name_player1 = "Игрок 1";
    protected static String name_player2 = "Игрок 2";
    protected static double sliderValue;
    public static String rect1Color;
    public static String rect2Color;
    protected static String theme_color;
    protected static boolean full_screan;
    protected static boolean[] achievement = new boolean[4];
    protected static final int[] mas_x = {152, 201, 249, 297, 345, 392, 441, 488, 537, 584};
    protected static final int[] mas_y = {258, 306, 354, 402, 450, 498, 546, 594, 642, 690};
    protected static final int[] mas_x_turn = {79, 104, 128, 152, 176, 200, 223, 248, 272, 296, 319, 344, 369, 392, 415, 440, 464, 488, 511, 536, 561};
    protected static final int[] mas_y_turn = {282, 307, 330, 355, 377, 403, 426, 451, 474, 499, 522, 546, 569, 595, 618, 643, 665};
    static int[][] player1 = new int[10][10];
    static int[][] player2 = new int[10][10];
    protected static int player = 1;
    protected static boolean flag = false;
    protected static MediaPlayer mediaPlayer;
    static String[] songs = {"music/main_sound.mp3", "music/battle_theme.mp3"};

    public static Thread t = new Thread(new Logic.Threads.Music());
    private static final Thread time_game = new Thread(new Logic.Threads.Time_game());
    protected static MediaPlayer mediaPlayer2 = new MediaPlayer(new Media(new File("music/buttonClick.mp3").toURI().toString()));
    protected static MediaPlayer mediaPlayer3 = new MediaPlayer(new Media(new File("music/injure_the_ship.mp3").toURI().toString()));
    protected static MediaPlayer mediaPlayer4 = new MediaPlayer(new Media(new File("music/kill_the_ship.mp3").toURI().toString()));
    protected static MediaPlayer mediaPlayer5 = new MediaPlayer(new Media(new File("music/water_splashing.mp3").toURI().toString()));

    public HelloApplication() throws IOException {
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene1 = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("frame_1.fxml"))));

//        InputStream iconStream = getClass().getResourceAsStream("file:icon.png");
//        System.out.println(iconStream);
//        Image image = new Image(iconStream);
        stage.getIcons().add(new Image("file:icon.png"));

        stage.setAlwaysOnTop(true);
        stage.setScene(scene1);
        stage.setFullScreen(full_screan);
        stage.setTitle("Морской бой");
        stage.show();
        mediaPlayer = new MediaPlayer(new Media(new File(songs[0]).toURI().toString()));
        mediaPlayer.setVolume(sliderValue /100);
        Logic.start_tread_music();

        //time_game.start(); // поток на время игры

        t.start();
    }


    public static void main(String[] args) {
        System.out.println("start");
        if (!file.exists()){
            create_file(file);
        }

        read_file(file);

//        System.out.println(Logic.time("01:05:00"));
//        System.out.println(Logic.time("00:30"));
        Application.launch(args);

        Logic.stop_tread_music();
        write_file(file);
    }
}