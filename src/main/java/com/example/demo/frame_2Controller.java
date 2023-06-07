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
import static com.example.demo.HelloApplication.mediaPlayer2;

public class frame_2Controller {
    private Logic logic = new Logic();
    private int[] mas_x = {152, 201, 249, 297, 345, 392, 441, 488, 537, 584};
    // 4    3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{79, 104, 127, 152, 175, 200, 223, 248, 272, 296, 318, 344, 368, 392, 415, 440, 463, 488, 510, 536, 561};
    // 2     3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{282, 307, 330, 355, 376, 403, 426, 451, 474, 499, 522, 546, 568, 595, 618, 643, 665}
    private int[] mas_x_turn = {79, 104, 127, 152, 175, 200, 223, 248, 272, 296, 318, 344, 368, 392, 415, 440, 463, 488, 510, 536, 561};
    private int[] mas_y_turn = {282, 307, 330, 355, 376, 403, 426, 451, 474, 499, 522, 546, 568, 595, 618, 643, 665};
    private int[] mas_y_turn4 = {330, 379, 427, 475, 522, 571, 616};
    private int[] mas_y_turn3 = {307, 355, 403, 451, 499, 546, 595, 643};
    private int[] mas_y_turn2 = {282, 330, 376, 426, 474, 522, 568, 618, 665};
    private int[] mas_y = {258, 306, 354, 402, 450, 498, 546, 594, 642, 690};
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
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        int rotate = (int)img.getRotate();
        int id = logic.id_ship(img.getId().substring(0, 5));
        try{
            switch (rotate) {
                case (0) -> {
                    logic.clear_ship(logic.index(mas_y, y), logic.index(mas_x, x), rotate);
                }
                case (90) -> {
                    logic.clear_ship(logic.index_turn_y(mas_y_turn, y, id), logic.index_turn_x(mas_x_turn, x, id), rotate);
                }
            }
//            System.out.print("y: ");
//            System.out.println(logic.index_turn_y(mas_y_turn, y, id));
//            System.out.print("x: ");
//            System.out.println(logic.index_turn_x(mas_x_turn, x, id));
        }
        catch (Exception e){
            System.out.println("ERROR");
        }

    }
    @FXML
    public void onImageClicked(MouseEvent event) {
        count_click  += 1;
        /**int id = 0;
        Object obj = event.getSource();
        ImageView img = (ImageView)obj;
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        switch (img.getId().substring(0, 5)){
            case ("Ship1") -> {
                id = 1;
            }
            case ("Ship2") -> {
                id = 2;
            }
            case ("Ship3") -> {
                id = 3;
            }
            case ("Ship4") -> {
                id = 4;
            }
        }
        try{
            logic.clear_ship(logic.index(mas_y,y), logic.index(mas_x,x));
            if(logic.chek_ship_and_around_for_turn(y, x, id)){
                logic.turn_ship(y, x, id);
            }
            else {
                logic.set_ship(logic.index(mas_y,y), logic.index(mas_x,x), id);
            }
        }
        catch (Exception e){
            System.out.println(logic.index(mas_x,x));
            System.out.println(logic.index(mas_y,y));
        }**/
        Object obj = event.getSource();
        ImageView img = (ImageView)obj;
        int rotate = (int)img.getRotate();
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        int id = logic.id_ship(img.getId().substring(0, 5));
         if(count_click == 2) {
             //int index_y = logic.index(mas_y,y);
             //int index_x = logic.index(mas_x,x);
             if (rotate == 0) {
                 logic.clear_ship(logic.index(mas_y, y), logic.index(mas_x, x), rotate);
                 img.setRotate(90);
                 logic.set_turn_ship(mas_x_turn, mas_y_turn, img);
             }
             if (rotate == 90) {
                 logic.clear_ship(logic.index_turn_y(mas_y_turn, y, id), logic.index_turn_x(mas_x_turn, x, id), rotate);
                 img.setRotate(0);
                 logic.set_ship(mas_x, mas_y, img);
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
//        System.out.print("x: ");
//        System.out.println(img.getLayoutX());
//        System.out.print("y: ");
//        System.out.println(img.getLayoutY());
        switch ((int)img.getRotate()){
            case (0) ->{
                logic.set_ship(mas_x, mas_y, img);
            }
            case (90) ->{
                logic.set_turn_ship(mas_x_turn, mas_y_turn, img);
            }
        }
    }

    @FXML
    private void handleButtonClickBack(ActionEvent event) throws IOException {
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
}
