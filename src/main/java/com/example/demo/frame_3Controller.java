package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.example.demo.HelloApplication.*;
import static com.example.demo.Logic.Support.*;
import static com.example.demo.Logic.Clear.*;
import static com.example.demo.Logic.in;
import static com.example.demo.Logic.random;

public class frame_3Controller {
    //    private static final Thread time_game = new Thread(new Logic.Threads.Time_game());
//    private AnchorPane anchorPane;
//
//    public void getAnchorPane(AnchorPane anchorPane) {
//        this.anchorPane = anchorPane;
//    }

    private String lastButtonPressed;

    public void setLastButtonPressed(String lastButtonPressed) {
        this.lastButtonPressed = lastButtonPressed;
    }

    @FXML
    Button btn1;
    @FXML
    public AnchorPane rect2;
    @FXML
    private Group group1;
    @FXML
    Label text1, text2;
    @FXML
    Label game_time;
    @FXML
    Polygon polyg;
    @FXML
    Rectangle shot00, shot01, shot02, shot03, shot04, shot05, shot06, shot07, shot08, shot09;
    @FXML
    Rectangle shot10, shot11, shot12, shot13, shot14, shot15, shot16, shot17, shot18, shot19;
    @FXML
    Rectangle shot20, shot21, shot22, shot23, shot24, shot25, shot26, shot27, shot28, shot29;
    @FXML
    Rectangle shot30, shot31, shot32, shot33, shot34, shot35, shot36, shot37, shot38, shot39;
    @FXML
    Rectangle shot40, shot41, shot42, shot43, shot44, shot45, shot46, shot47, shot48, shot49;
    @FXML
    Rectangle shot50, shot51, shot52, shot53, shot54, shot55, shot56, shot57, shot58, shot59;
    @FXML
    Rectangle shot60, shot61, shot62, shot63, shot64, shot65, shot66, shot67, shot68, shot69;
    @FXML
    Rectangle shot70, shot71, shot72, shot73, shot74, shot75, shot76, shot77, shot78, shot79;
    @FXML
    Rectangle shot80, shot81, shot82, shot83, shot84, shot85, shot86, shot87, shot88, shot89;
    @FXML
    Rectangle shot90, shot91, shot92, shot93, shot94, shot95, shot96, shot97, shot98, shot99;

    @FXML
    Rectangle shot2_00, shot2_01, shot2_02, shot2_03, shot2_04, shot2_05, shot2_06, shot2_07, shot2_08, shot2_09;
    @FXML
    Rectangle shot2_10, shot2_11, shot2_12, shot2_13, shot2_14, shot2_15, shot2_16, shot2_17, shot2_18, shot2_19;
    @FXML
    Rectangle shot2_20, shot2_21, shot2_22, shot2_23, shot2_24, shot2_25, shot2_26, shot2_27, shot2_28, shot2_29;
    @FXML
    Rectangle shot2_30, shot2_31, shot2_32, shot2_33, shot2_34, shot2_35, shot2_36, shot2_37, shot2_38, shot2_39;
    @FXML
    Rectangle shot2_40, shot2_41, shot2_42, shot2_43, shot2_44, shot2_45, shot2_46, shot2_47, shot2_48, shot2_49;
    @FXML
    Rectangle shot2_50, shot2_51, shot2_52, shot2_53, shot2_54, shot2_55, shot2_56, shot2_57, shot2_58, shot2_59;
    @FXML
    Rectangle shot2_60, shot2_61, shot2_62, shot2_63, shot2_64, shot2_65, shot2_66, shot2_67, shot2_68, shot2_69;
    @FXML
    Rectangle shot2_70, shot2_71, shot2_72, shot2_73, shot2_74, shot2_75, shot2_76, shot2_77, shot2_78, shot2_79;
    @FXML
    Rectangle shot2_80, shot2_81, shot2_82, shot2_83, shot2_84, shot2_85, shot2_86, shot2_87, shot2_88, shot2_89;
    @FXML
    Rectangle shot2_90, shot2_91, shot2_92, shot2_93, shot2_94, shot2_95, shot2_96, shot2_97, shot2_98, shot2_99;


    @FXML
    private void shot(MouseEvent event) {
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(0.0);
        coordinates.add(0.0);
        coordinates.add(0.0);
        coordinates.add(140.0);
        coordinates.add(50.0);
        coordinates.add(70.0);
        Logic.shoot_enemy(event, player1, 1, "#shot", polyg, name_player2, Color.RED, Color.GREEN, rect2, btn1, 4, 5, coordinates);
        System.out.println("score_player " + score_player1);
    }

    @FXML
    private void shot2(MouseEvent event) {
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(50.0);
        coordinates.add(0.0);
        coordinates.add(50.0);
        coordinates.add(140.0);
        coordinates.add(0.0);
        coordinates.add(70.0);
        Logic.shoot_enemy(event, player2, 2, "#shot2_", polyg, name_player1, Color.GREEN, Color.RED, rect2, btn1, 6, 7, coordinates);
        System.out.println("score_player " + score_player2);
        System.out.println(polyg.getFill() == Color.RED);
        Logic.shoot_bot(player1, "#shot", polyg, name_player2, rect2, btn1, lastButtonPressed);

    }
//        if (polygColor){
//            if (player1[i][j] == 0 || player1[i][j] == 8) {
//                clickedImageView.setFill(Color.BLACK);
//            } else {
//                clickedImageView.setFill(Color.BLUE);
//            }
//        }


    @FXML
    private void onPress(MouseEvent event) {
        //MediaPlayer mediaPlayer2 = HelloApplication.getMediaPlayer2();
        //mediaPlayer2.setVolume((double) volume);
        //MediaPlayer mediaPlayer2 = HelloApplication.mediaPlayer2();
        mediaPlayer2.play();
        mediaPlayer2.seek(Duration.ZERO);
    }

    @FXML
    private void handleButtonClickExit(ActionEvent event) throws IOException {
        Stage window = new Stage();

//        Pane pane = new Pane();

        Group root = new Group();
        Scene scene = new Scene(root, 600, 100);

        Label label = new Label("Вы уверены, что хотите выйти?");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size:40;-fx-font-size:40");

        Button btn_no = new Button("Нет");
        btn_no.setLayoutX(400);
        btn_no.setLayoutY(60);


        Button btn_yes = new Button("Да");
        btn_yes.setLayoutX(100);
        btn_yes.setLayoutY(60);


        btn_yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.dispose();
                mediaPlayer = new MediaPlayer(new Media(new File(songs[0]).toURI().toString()));
                mediaPlayer.setVolume(sliderValue / 100);
                flag = true;
                player = 1;
                Logic.stop_tread_time(game_time);
                clear_matrix(player1);
//                System.out.println("player 1-------------------------");
                clear_matrix(player2);
//                System.out.println("player 2-------------------------");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_1.fxml"));
                Parent pane = null;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//            frame_2Controller controller = loader.getController();
//            controller.setLastButtonPressed("bot");
//        scene.setCursor(Cursor.HAND);
                Stage window1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Stage window2 = (Stage) btn1.getScene().getWindow();
                Scene scene = new Scene(pane);
                window.close();
                window2.close();
                window1.setScene(scene);
                window1.setTitle("Морской бой");
                window1.setFullScreen(full_screan);
                window1.show();
            }
        });
        btn_no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });

        root.getChildren().add(label);
        root.getChildren().add(btn_yes);
        root.getChildren().add(btn_no);

        window.setScene(scene);
        window.initModality(Modality.WINDOW_MODAL);
//        window.initOwner(parentStage);
        window.showAndWait();
    }

    // @Override
    public void initialize() {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
//        double parentWidth1 = group1.getParent().getLayoutBounds().getWidth();
//        double parentHeight = group1.getParent().getLayoutBounds().getHeight();
        double groupWidth1 = group1.getBoundsInParent().getWidth();
//        double groupHeight = group1.getBoundsInParent().getHeight();
        group1.setLayoutX(screenWidth / 2 - groupWidth1 / 1.85);

        double screenHeight = Screen.getPrimary().getBounds().getHeight();
//        double parentWidth1 = group1.getParent().getLayoutBounds().getWidth();
//        double parentHeight = group1.getParent().getLayoutBounds().getHeight();
        double groupHeight1 = group1.getBoundsInParent().getHeight();
//        double groupHeight = group1.getBoundsInParent().getHeight();
        group1.setLayoutY(screenHeight / 2 - groupWidth1 / 3.3);

        rect2.setStyle(rect2Color);
        Thread time_game = new Thread(new Logic.Threads.Time_game());
        Logic.set_label_time(game_time);
        time_game.start();
        mediaPlayer.dispose();
        mediaPlayer = new MediaPlayer(new Media(new File(songs[1]).toURI().toString()));
        mediaPlayer.setVolume(sliderValue / 100);
        flag = true;
//        System.out.println(name_player1);
        text1.setText(name_player1);
        text2.setText(name_player2);
//        System.out.println(text1.getText());
    }
}
