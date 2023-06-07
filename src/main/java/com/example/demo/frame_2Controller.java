package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.demo.HelloApplication.*;
import static com.example.demo.Logic.*;
//import static com.example.demo.HelloApplication.mediaPlayer2;

public class frame_2Controller {
    // 4    3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{79, 104, 127, 152, 175, 200, 223, 248, 272, 296, 318, 344, 368, 392, 415, 440, 463, 488, 510, 536, 561};
    // 2     3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{282, 307, 330, 355, 376, 403, 426, 451, 474, 499, 522, 546, 568, 595, 618, 643, 665}
    static int[][] matrix ;
    private Logic logic = new Logic();
    double x, y;
    int count_click = 0;
    int rotate_ship = 0;
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    private String lastButtonPressed;
    public void setLastButtonPressed(String lastButtonPressed) {
        this.lastButtonPressed = lastButtonPressed;
    }

    @FXML
    private void onPress(MouseEvent event) {
        //MediaPlayer mediaPlayer2 = HelloApplication.getMediaPlayer2();
        //mediaPlayer2.setVolume((double) volume);
        //MediaPlayer mediaPlayer2 = HelloApplication.mediaPlayer2();
        mediaPlayer2.play();
        mediaPlayer2.seek(Duration.ZERO);
    }

    @FXML
    public void onImagePressed(MouseEvent event) {
//        String imageViewId = ((ImageView) event.getSource()).getId();
        ImageView clickedImageView = (ImageView) event.getSource();
        x = event.getX() - clickedImageView.getLayoutBounds().getWidth();
//        System.out.println(x);
        y = event.getY() - clickedImageView.getLayoutBounds().getHeight();
        clickedImageView.setCursor(Cursor.MOVE);


        Object obj = event.getSource();
        ImageView img = (ImageView)obj;
        int rotate = (int)img.getRotate();
        switch (rotate) {
            case (0) -> {
                clear(mas_x, mas_y, rotate, img, matrix);
            }
            case (90) -> {
                clear(mas_x_turn, mas_y_turn, rotate, img, matrix);
            }
        }
    }
    @FXML
    public void onImageClicked(MouseEvent event) {
        count_click  += 1;
        Object obj = event.getSource();
        ImageView img = (ImageView)obj;
        int rotate = (int)img.getRotate();
         if(count_click == 2) {
             switch (rotate) {
                 case (0) -> {
                     try {
                         clear(mas_x, mas_y, rotate, img, matrix);
                         img.setRotate(90);
                         set_turn_ship(mas_x_turn, mas_y_turn, img, matrix);
                     }
                     catch (Exception e){}
                 }
                 case (90) -> {
                     try {
                         clear(mas_x_turn, mas_y_turn, rotate, img, matrix);
                         img.setRotate(0);
                         set_ship(mas_x, mas_y, img,matrix);
                     }
                     catch (Exception e){}
                 }
             }
             count_click = 0;
         }
    }

    @FXML
    public void onImageDragged(MouseEvent event) {
        count_click = 0;
        ImageView clickedImageView = (ImageView) event.getSource();
        clickedImageView.setLayoutX(event.getSceneX() + x);
        clickedImageView.setLayoutY(event.getSceneY() + y);
    }

    public void onImageReleased(MouseEvent mouseEvent) {
        Object obj = mouseEvent.getSource();
        ImageView img = (ImageView)obj;
        switch ((int)img.getRotate()){
            case (0) ->{
                set_ship(mas_x, mas_y, img, matrix);
            }
            case (90) ->{
                set_turn_ship(mas_x_turn, mas_y_turn, img, matrix);
            }
        }
    }

    @FXML
    private void handleButtonClickBack(ActionEvent event) throws IOException {
        player = 1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_1.fxml"));
        Parent pane = loader.load();
//            frame_2Controller controller = loader.getController();
//            controller.setLastButtonPressed("bot");
//        scene.setCursor(Cursor.HAND);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane);
        window.setMaximized(true);
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleButtonClickForward(ActionEvent event) throws IOException {
        count_click = 0;
        if (Objects.equals(lastButtonPressed, "bot") || Objects.equals(lastButtonPressed, "together_2")){
            player = 0;


            System.out.println("player 1");
            PrintArray(player1);
            System.out.println("player 2");
            PrintArray(player2);


            FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_3.fxml"));
            Parent pane = loader.load();
//            frame_2Controller controller = loader.getController();
//            controller.setLastButtonPressed("bot");
//        scene.setCursor(Cursor.HAND);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane);
            window.setMaximized(true);
            window.setScene(scene);
            window.show();
        }
        else if (Objects.equals(lastButtonPressed, "together_1")){
            player += 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_2.fxml"));
            Parent pane = loader.load();
            frame_2Controller controller = loader.getController();
            controller.setLastButtonPressed("together_2");
//        scene.setCursor(Cursor.HAND);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane);
            window.setMaximized(true);
            window.setScene(scene);
            window.show();
        }
    }
    public void initialize() {
        if (player == 1){
            matrix = player1;
        }
        if (player == 2){
            matrix = player2;
        }
    }
}
