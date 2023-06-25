package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.example.demo.HelloApplication.*;
import static com.example.demo.Logic.*;
import static com.example.demo.Logic.RandomShips.*;
import static com.example.demo.Logic.Clear.*;
import static com.example.demo.Logic.RandomShips.random_set_ship_image;
import static com.example.demo.Logic.SetShips.*;
import static com.example.demo.Logic.Default.*;
import static com.example.demo.Logic.Support.*;

public class frame_2Controller {
    // 4    3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{79, 104, 127, 152, 175, 200, 223, 248, 272, 296, 318, 344, 368, 392, 415, 440, 465, 488, 510, 536, 561};
    // 2     3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3   4,2   3    2
    //{282, 307, 330, 355, 376, 403, 426, 451, 474, 499, 522, 546, 568, 595, 618, 643, 665}

    static int[][] matrix ;
    double x, y;
    int count_click = 0;
    int rotate_ship = 0;
    @FXML
    private Group group1;
    @FXML
    public AnchorPane rect2;
    @FXML
    private ImageView Ship4_1, Ship3_1, Ship3_2, Ship2_1, Ship2_2, Ship2_3, Ship1_1, Ship1_2, Ship1_3, Ship1_4;
    private final ImageView[] ships = new ImageView[10];
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn7;
    @FXML
    private TextField text;

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
        if((img.getLayoutX() <= 632 && img.getLayoutX() >= 105) && (img.getLayoutY() <= 737 && img.getLayoutY() >= 210)) {
            switch (rotate) {
                case (0) -> {
                    clear(mas_x, mas_y, rotate, img, matrix);
                }
                case (90) -> {
                    clear(mas_x_turn, mas_y_turn, rotate, img, matrix);
                }
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
             if((img.getLayoutX() <= 632 && img.getLayoutX() >= 105) && (img.getLayoutY() <= 737 && img.getLayoutY() >= 210)){
                 switch (rotate) {
                     case (0) -> {
                         try {
                             clear(mas_x, mas_y, rotate, img, matrix);
                             img.setRotate(90);
                             set_turn_ship(img, matrix);
                         }
                         catch (Exception e){}
                     }
                     case (90) -> {
                         try {
                             clear(mas_x_turn, mas_y_turn, rotate, img, matrix);
                             img.setRotate(0);
                             set_ship(img,matrix);

                         }
                         catch (Exception e){}
                     }
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
        if ((img.getLayoutX() > 632 || img.getLayoutX() < 105) || (img.getLayoutY() > 737 || img.getLayoutY() < 210)){
            int default_x = default_ship_x(img.getId());
            int default_y = default_ship_y(img.getId());
            img.setLayoutX(default_x);
            img.setLayoutY(default_y);
            img.setRotate(0);
        }
        else {
            switch ((int)img.getRotate()){
                case (0) ->{
                    set_ship(img, matrix);
                }
                case (90) ->{
                    set_turn_ship(img, matrix);
                }
            }
        }
    }

    @FXML
    private void handleButtonClickBack(ActionEvent event) throws IOException {
        count_click = 0;
        player = 1;
        name_player2 = "Игрок 2";
        name_player1 = "Игрок 1";

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_1.fxml"));
//        Parent pane = loader.load();
////            frame_2Controller controller = loader.getController();
////            controller.setLastButtonPressed("bot");
////        scene.setCursor(Cursor.HAND);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(pane);
//        window.setMaximized(true);
//        window.setScene(scene);
//        window.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Logic.changeScene(loader, stage);

    }

    @FXML
    private void onButtonClick(MouseEvent event) throws IOException {
        set_random_ships(ships, matrix);
    }

    @FXML
    private void handleButtonClickForward(ActionEvent event) throws IOException {
        count_click = 0;
        if ((Objects.equals(lastButtonPressed, "bot") || Objects.equals(lastButtonPressed, "together_2")) && count_ships(matrix) == 20){
            player = 0;
            game = "together";
            if ((Objects.equals(lastButtonPressed, "bot"))){
                matrix = player2;
                set_random_ships(ships, matrix);
                name_player2 = Logic.bot_names[random.nextInt(20)];
                game = "bot";
            }

            System.out.println(name_player1);
            PrintArray(player1);
            System.out.println(name_player2);
            PrintArray(player2);

            score_player1 = 0;
            score_player2 = 0;

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_3.fxml"));
//            Parent pane = loader.load();
//            AnchorPane anchorpane = (AnchorPane) pane.getChildrenUnmodifiable().get(0);
//            frame_3Controller controller = loader.getController();
//            controller.getAnchorPane(anchorpane);
////            frame_2Controller controller = loader.getController();
////            controller.setLastButtonPressed("bot");
////        scene.setCursor(Cursor.HAND);
//            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(pane);
//            window.setMaximized(true);
//            window.setScene(scene);
//            window.show();

            mediaPlayer.dispose();
            mediaPlayer = new MediaPlayer(new Media(new File(songs[1]).toURI().toString()));
            mediaPlayer.setVolume(sliderValue / 100);
            flag = true;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_3.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Logic.changeScene(loader, stage);
//            AnchorPane anchorpane = (AnchorPane) pane.getChildrenUnmodifiable().get(0);
            frame_3Controller controller = loader.getController();
//            controller.getAnchorPane(anchorpane);
            controller.setLastButtonPressed(lastButtonPressed);
        }
        else if (Objects.equals(lastButtonPressed, "together_1" ) && count_ships(matrix) == 20){
            if (count_ships(matrix) == 20){
                player += 1;
            }

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_2.fxml"));
//            Parent pane = loader.load();
//            frame_2Controller controller = loader.getController();
//            controller.setLastButtonPressed("together_2");
////        scene.setCursor(Cursor.HAND);
//            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(pane);
//            window.setMaximized(true);
//            window.setScene(scene);
//            window.show();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_2.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Logic.changeScene(loader, stage);
            frame_2Controller controller = loader.getController();
            controller.setLastButtonPressed("together_2");

        }
    }
    public void initialize() {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
//        double parentWidth1 = group1.getParent().getLayoutBounds().getWidth();
//        double parentHeight = group1.getParent().getLayoutBounds().getHeight();
        double groupWidth1 = group1.getBoundsInParent().getWidth();
//        double groupHeight = group1.getBoundsInParent().getHeight();
        group1.setLayoutX(screenWidth/2 - groupWidth1/1.85);
        rect2.setStyle(rect2Color);

        if (ships[0] == null){
            ships[0] = Ship4_1;
            ships[1] = Ship3_1;
            ships[2] = Ship3_2;
            ships[3] = Ship2_1;
            ships[4] = Ship2_2;
            ships[5] = Ship2_3;
            ships[6] = Ship1_1;
            ships[7] = Ship1_2;
            ships[8] = Ship1_3;
            ships[9] = Ship1_4;
        }

        if (player == 1){
            text.setPromptText(name_player1);
            matrix = player1;
            clear_matrix(matrix);
            text.textProperty().addListener((Observable, oldValue, newValue) -> {
                name_player1 = newValue;
            });
        }
        if (player == 2){
            text.setPromptText(name_player2);
            matrix = player2;
            clear_matrix(matrix);
            text.textProperty().addListener((Observable, oldValue, newValue) -> {
                name_player2 = newValue;
            });
        }
    }
}
