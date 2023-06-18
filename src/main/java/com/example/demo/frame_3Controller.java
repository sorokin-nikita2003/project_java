package com.example.demo;

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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

import static com.example.demo.HelloApplication.*;
import static com.example.demo.Logic.Support.*;
import static com.example.demo.Logic.Clear.*;

public class frame_3Controller {
    private AnchorPane anchorPane;
    public void getAnchorPane (AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
    @FXML
    Button btn1;
    @FXML
    public AnchorPane rect2;

    @FXML
    Label text1, text2;
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
        Rectangle clickedImageView = (Rectangle) event.getSource();
        int i = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(4)));
//        System.out.println(i);
        int j = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(5)));
//        System.out.println(j);
        Paint polygColor = polyg.getFill();
        if (polygColor == Color.RED && clickedImageView.getFill() != Color.BLACK && clickedImageView.getFill() != Color.GRAY && clickedImageView.getFill() != Color.BLUE) {
            //System.out.println(clickedImageView.getFill());
            //System.out.println(111111);
            if (player1[i][j] == 0 || player1[i][j] == 8) {
                clickedImageView.setFill(Color.BLUE);
                polyg.getPoints().setAll(0.0, 0.0,
                        0.0, 140.0,
                        50.0, 70.0);
                polyg.setFill(Color.GREEN);
            } else {
                score_player1 += 1;
                System.out.println("score_player1 " + score_player1);
                clickedImageView.setFill(Color.GRAY);
                player1[i][j] = -1;

                int flag_left = 0;
                int flag_right = 0;
                int flag_top = 0;
                int flag_down = 0;
                //System.out.println("length" + player1[0].length);

                while (player1[i][j] == -1) {
                    System.out.println("1111" + player1[i][j]);
                    i -= 1;
                    if (i == player1[0].length || i == -1 || player1[i][j] == 8) {
                        flag_left = 1;
                        //System.out.println("efwegfewg");
                        break;
                    }
                }
                i += 1;
                int we = player1[1].length - 1;
                //System.out.println("ewfewfewf " + we);

                while (player1[i][j] == -1) {
                    System.out.println("2222" + player1[i][j]);
                    i += 1;
                    if (i == player1[0].length || i == -1 || player1[i][j] == 8) {
                        flag_right = 1;
                        break;
                    }
                }
                i -= 1;

                while (player1[i][j] == -1) {
                    //System.out.println("3333" + player1[i][j]);
                    j -= 1;
                    if (j == player1[1].length || j == -1 || player1[i][j] == 8) {
                        flag_down = 1;
                        break;
                    }
                }
                j += 1;
                //System.out.println("3333" + player1[i][j]);

                while (player1[i][j] == -1) {
                    //System.out.println("4444" + player1[i][j]);
                    j += 1;
                    if (j == player1[1].length || j == -1 || player1[i][j] == 8) {
                        flag_top = 1;
                        break;
                    }
                }
                j -= 1;
                //System.out.println("3333" + player1[i][j]);

//                System.out.println("left " + flag_left);
//                System.out.println("right " + flag_right);
//                System.out.println("top " + flag_top);
//                System.out.println("down " + flag_down);

                if (flag_down == 1 && flag_left == 1 && flag_top == 1 && flag_right == 1) {
                    PrintArray(player1);
                    Rectangle rectangle = null;
                    Scene scene = null;
                    while (player1[i][j] == -1 || player1[i][j] == -2) {
                        String rect_id = "#shot" + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player1[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = "#shot" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player1[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = "#shot" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player1[0].length - 1 && player1[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = "#shot" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player1[1].length - 1 && player1[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = "#shot" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }

                        if (player1[i][j] == -1){
                            player1[i][j] = -2;
                        }
                        i -= 1;
                        if (i == player1[0].length || i == -1 || player1[i][j] == 8) {
                            break;
                        }
                    }
                    if (i != -1 && j != player1[1].length - 1 && player1[i][j+1] == 8) {
                        int j8 = j+1;
                        String rect_id2 = "#shot" + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (i != -1 && j != 0 && player1[i][j-1] == 8) {
                        int j8 = j-1;
                        String rect_id2 = "#shot" + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }

                    i += 1;
                    //System.out.println("wefwe" + player1[i][j]);
                    while (player1[i][j] == -1 || player1[i][j] == -2) {
                        String rect_id = "#shot" + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player1[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = "#shot" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player1[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = "#shot" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player1[0].length - 1 && player1[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = "#shot" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player1[1].length - 1 && player1[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = "#shot" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player1[i][j] == -1){
                            player1[i][j] = -2;
                        }
                        i += 1;
                        if (i == player1[0].length || i == -1 || player1[i][j] == 8) {
                            break;
                        }
                    }
                    if (i != player1[0].length && j != player1[1].length - 1 && player1[i][j+1] == 8) {
                        int j8 = j+1;
                        String rect_id2 = "#shot" + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (i != player1[0].length && j != 0 && player1[i][j-1] == 8) {
                        int j8 = j-1;
                        String rect_id2 = "#shot" + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    i -= 1;
                    //System.out.println("wefwe" + player1[i][j]);
                    while (player1[i][j] == -1 || player1[i][j] == -2) {
                        String rect_id = "#shot" + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player1[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = "#shot" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player1[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = "#shot" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player1[0].length - 1 && player1[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = "#shot" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player1[1].length - 1 && player1[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = "#shot" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player1[i][j] == -1){
                            player1[i][j] = -2;
                        }
                        j += 1;
                        if (j == player1[1].length || j == -1 || player1[i][j] == 8) {
                            break;
                        }
                    }
                    if (j != player1[1].length && i != player1[0].length - 1 && player1[i+1][j] == 8) {
                        int i8 = i+1;
                        String rect_id2 = "#shot" + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (j != player1[1].length && i != 0 && player1[i-1][j] == 8) {
                        int i8 = i-1;
                        String rect_id2 = "#shot" + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    j -= 1;
                    //System.out.println("wefwe" + player1[i][j]);
                    while (player1[i][j] == -1 || player1[i][j] == -2) {
                        String rect_id = "#shot" + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player1[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = "#shot" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player1[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = "#shot" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player1[0].length - 1 && player1[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = "#shot" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player1[1].length - 1 && player1[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = "#shot" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player1[i][j] == -1) {
                            player1[i][j] = -2;
                        }
                        j -= 1;
                        //System.out.println("wefwe" + rect_id);
                        if (j == player1[1].length || j == -1 || player1[i][j] == 8) {
                            break;
                        }
                    }
                    if (j != -1 && i != player1[0].length - 1 && player1[i+1][j] == 8) {
                        int i8 = i+1;
                        String rect_id2 = "#shot" + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (j != -1 && i != 0 && player1[i-1][j] == 8) {
                        int i8 = i-1;
                        String rect_id2 = "#shot" + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    j += 1;
                    PrintArray(player1);

//                    System.out.println("i: " + i);
//                    System.out.println("j: " + j);
//                    while (i != player1[0].length && i != -1 && (player1[i][j] == 8 || player1[i][j] == -2) && player1[i][j] == -2){
//                        i -= 1;
//                        //System.out.println("чему Ш  " + i);
//                    }
//                    i += 1;
//                    while (j != player1[1].length && j != -1 && (player1[i][j] == 8 || player1[i][j] == -2) && (player1[i-1][j] == -2 || player1[i][j-1] == -2 || player1[i+1][j] == -2 || player1[j+1][j] == -2)){
//                        j -= 1;
//                    }
//                    j += 1;
//
//                    System.out.println("i = " + i);
//                    System.out.println("j = " + j);
//
//                    while (j != player1[1].length && j != -1 && player1[i][j] == 8 && ((i + 1 != 10 && j + 1 != 10 && player1[i+1][j+1] == -2) || (i + 1 != 10 && player1[i+1][j] == -2))) {
//                        String rect_id = "#shot" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j += 1;
////                        System.out.println("i = " + i);
////                        System.out.println("j = " + j);
//                    }
//
////                    System.out.println("i: " + i);
////                    System.out.println("j: " + j);
//                    while (j != player1[1].length && j != -1 && (player1[i][j] == 8 || player1[i][j] == -2)){
//                        j += 1;
//                    }
//                    j -= 1;
//
//
//                    while (i != player1[0].length && i != -1 && player1[i][j] == 8 && ((i + 1 != 10 && j - 1 != -1 && player1[i+1][j-1] == -2) || (j - 1 != -1 && player1[i][j-1] == -2))) {
//                        String rect_id = "#shot" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i += 1;
//                    }
//                    while (i != player1[0].length && i != -1 && (player1[i][j] == 8 || player1[i][j] == -2)){
//                        i += 1;
//                    }
//                    i -= 1;
//
//                    while (j != player1[1].length && j != -1 && player1[i][j] == 8 && ((i - 1 != -1 && j - 1 != -1 && player1[i-1][j-1] == -2) || (i - 1 != -1 && player1[i-1][j] == -2))) {
//                        String rect_id = "#shot" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j -= 1;
//                    }
//                    while (j != player1[1].length && j != -1 && (player1[i][j] == 8 || player1[i][j] == -2)){
//                        j -= 1;
//                    }
//                    j += 1;
//
//                    while (i != player1[0].length && i != -1 && player1[i][j] == 8 && ((j + 1 != 10 && i - 1 != -1 && player1[i-1][j+1] == -2)  || (j + 1 != 10 && player1[i][j+1] == -2))) {
//                        String rect_id = "#shot" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i -= 1;
//                    }
//                    while (i != player1[1].length && i != -1 && (player1[i][j] == 8 || player1[i][j] == -2)){
//                        i -= 1;
//                    }
//                    i += 1;

//                    while (player1[i][j] == 8 && player1[i - 1][j] == -1) {
//                        String rect_id = "#shot" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i -= 1;
//                    }
//                    while (player1[i][j] == 8 && player1[i][j + 1] == -1) {
//                        String rect_id = "#shot" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j += 1;
//                    }
//                    while (player1[i][j] == 8 && player1[i - 1][j] == -1) {
//                        i -= 1;
//                        String rect_id = "#shot" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                    }
//                    while (player1[i][j] == 8 && player1[i][j - 1] == -1) {
//                        j -= 1;
//                        String rect_id = "#shot" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                    }
                }
                if (score_player1 == 20) {
                    Stage window = new Stage();

//        Pane pane = new Pane();

                    Group root = new Group();
                    Scene scene = new Scene(root, 600,100);

                    Label label = new Label("Игрок " + name_player2 + " выиграл");
                    label.setAlignment(Pos.CENTER);
                    label.setStyle("-fx-font-size:40;-fx-font-size:40");

                    Button btn_ok = new Button("Да");
                    btn_ok.setLayoutX(300);
                    btn_ok.setLayoutY(60);


                    btn_ok.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mediaPlayer.dispose();
                            mediaPlayer = new MediaPlayer(new Media(new File(songs[0]).toURI().toString()));
                            mediaPlayer.setVolume(sliderValue / 100);
                            time_sleep = 210000;
                            flag = true;
                            player = 1;
                            clear_matrix(player1);
                            //.out.println("player 1-------------------------");
                            PrintArray(player1);

                            clear_matrix(player2);
                            //System.out.println("player 2-------------------------");
                            PrintArray(player2);

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
                            Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                            Stage window2 = (Stage)btn1.getScene().getWindow();
                            Scene scene = new Scene(pane);
                            window.close();
                            window2.close();
                            window1.setMaximized(true);
                            window1.setScene(scene);
                            window1.show();
                        }
                    });
                    root.getChildren().add(label);
                    root.getChildren().add(btn_ok);

                    window.setScene(scene);
                    window.initModality(Modality.WINDOW_MODAL);
//        window.initOwner(parentStage);
                    window.showAndWait();
                }
                //PrintArray(player1);
            }
            //polyg.getPoints().removeAll();
        }
    }

    @FXML
    private void shot2(MouseEvent event) {
        Rectangle clickedImageView = (Rectangle) event.getSource();
        int i = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(6)));
//        System.out.println(i);
        int j = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(7)));
//        System.out.println(j);
        Paint polygColor = polyg.getFill();
//        System.out.println(polygColor);
        if (polygColor == Color.GREEN && clickedImageView.getFill() != Color.BLACK && clickedImageView.getFill() != Color.GRAY && clickedImageView.getFill() != Color.BLUE) {
            //System.out.println(111111);
            if (player2[i][j] == 0 || player2[i][j] == 8) {
                clickedImageView.setFill(Color.BLUE);
                polyg.getPoints().setAll(50.0, 0.0,
                        50.0, 140.0,
                        0.0, 70.0);
                polyg.setFill(Color.RED);
            } else {
                score_player2 += 1;
                clickedImageView.setFill(Color.GRAY);
                player2[i][j] = -1;

                int flag_left = 0;
                int flag_right = 0;
                int flag_top = 0;
                int flag_down = 0;
//                System.out.println("length" + player2[0].length);

                while (player2[i][j] == -1) {
//                    System.out.println("1111" + player2[i][j]);
                    i -= 1;
                    if (i == player2[0].length || i == -1 || player2[i][j] == 8) {
                        flag_left = 1;
                        //System.out.println("efwegfewg");
                        break;
                    }
                }
                i += 1;
                int we = player2[1].length - 1;
//                System.out.println("ewfewfewf " + we);

                while (player2[i][j] == -1) {
//                    System.out.println("2222" + player2[i][j]);
                    i += 1;
                    if (i == player2[0].length || i == -1 || player2[i][j] == 8) {
                        flag_right = 1;
                        break;
                    }
                }
                i -= 1;

                while (player2[i][j] == -1) {
//                    System.out.println("3333" + player2[i][j]);
                    j -= 1;
                    if (j == player2[1].length || j == -1 || player2[i][j] == 8) {
                        flag_down = 1;
                        break;
                    }
                }
                j += 1;
                //System.out.println("3333" + player1[i][j]);

                while (player2[i][j] == -1) {
//                    System.out.println("4444" + player2[i][j]);
                    j += 1;
                    if (j == player2[1].length || j == -1 || player2[i][j] == 8) {
                        flag_top = 1;
                        break;
                    }
                }
                j -= 1;
                //System.out.println("3333" + player1[i][j]);

//                System.out.println("left " + flag_left);
//                System.out.println("right " + flag_right);
//                System.out.println("top " + flag_top);
//                System.out.println("down " + flag_down);

                if (flag_down == 1 && flag_left == 1 && flag_top == 1 && flag_right == 1) {
                    PrintArray(player2);
                    Rectangle rectangle = null;
                    Scene scene = null;
                    while (player2[i][j] == -1 || player2[i][j] == -2) {
                        String rect_id = "#shot2_" + i + j;
//                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player2[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = "#shot2_" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player2[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = "#shot2_" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player2[0].length - 1 && player2[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = "#shot2_" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player2[1].length - 1 && player2[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = "#shot2_" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }

                        if (player2[i][j] == -1){
                            player2[i][j] = -2;
                        }
                        i -= 1;
                        if (i == player2[0].length || i == -1 || player2[i][j] == 8) {
                            break;
                        }
                    }
                    if (i != -1 && j != player2[1].length - 1 && player2[i][j+1] == 8) {
                        int j8 = j+1;
                        String rect_id2 = "#shot2_" + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (i != -1 && j != 0 && player2[i][j-1] == 8) {
                        int j8 = j-1;
                        String rect_id2 = "#shot2_" + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }

                    i += 1;
                    //System.out.println("wefwe" + player1[i][j]);
                    while (player2[i][j] == -1 || player2[i][j] == -2) {
                        String rect_id = "#shot2_" + i + j;
//                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player2[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = "#shot2_" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player2[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = "#shot2_" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player2[0].length - 1 && player2[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = "#shot2_" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player2[1].length - 1 && player2[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = "#shot2_" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player2[i][j] == -1){
                            player2[i][j] = -2;
                        }
                        i += 1;
                        if (i == player2[0].length || i == -1 || player2[i][j] == 8) {
                            break;
                        }
                    }
                    if (i != player2[0].length && j != player2[1].length - 1 && player2[i][j+1] == 8) {
                        int j8 = j+1;
                        String rect_id2 = "#shot2_" + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (i != player2[0].length && j != 0 && player2[i][j-1] == 8) {
                        int j8 = j-1;
                        String rect_id2 = "#shot2_" + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    i -= 1;
                    //System.out.println("wefwe" + player1[i][j]);
                    while (player2[i][j] == -1 || player2[i][j] == -2) {
                        String rect_id = "#shot2_" + i + j;
//                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player2[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = "#shot2_" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player2[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = "#shot2_" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player2[0].length - 1 && player2[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = "#shot2_" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player2[1].length - 1 && player2[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = "#shot2_" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player2[i][j] == -1){
                            player2[i][j] = -2;
                        }
                        j += 1;
                        if (j == player2[1].length || j == -1 || player2[i][j] == 8) {
                            break;
                        }
                    }
                    if (j != player2[1].length && i != player2[0].length - 1 && player2[i+1][j] == 8) {
                        int i8 = i+1;
                        String rect_id2 = "#shot2_" + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (j != player2[1].length && i != 0 && player2[i-1][j] == 8) {
                        int i8 = i-1;
                        String rect_id2 = "#shot2_" + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    j -= 1;
//                    System.out.println("wefwe" + player2[i][j]);
                    while (player2[i][j] == -1 || player2[i][j] == -2) {
                        String rect_id = "#shot2_" + i + j;
//                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player2[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = "#shot2_" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player2[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = "#shot2_" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player2[0].length - 1 && player2[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = "#shot2_" + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player2[1].length - 1 && player2[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = "#shot2_" + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player2[i][j] == -1) {
                            player2[i][j] = -2;
                        }
                        j -= 1;
//                        System.out.println("wefwe" + rect_id);
                        if (j == player2[1].length || j == -1 || player2[i][j] == 8) {
                            break;
                        }
                    }
                    if (j != -1 && i != player2[0].length - 1 && player2[i+1][j] == 8) {
                        int i8 = i+1;
                        String rect_id2 = "#shot2_" + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (j != -1 && i != 0 && player2[i-1][j] == 8) {
                        int i8 = i-1;
                        String rect_id2 = "#shot2_" + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    j += 1;

                    PrintArray(player2);


//                    while (i != player2[0].length && i != -1 && (player2[i][j] == 8 || player2[i][j] == -2)){
//                        i -= 1;
//                        System.out.println("чему Ш  1" + i);
//                    }
//                    i += 1;
//                    while (j != player2[1].length && j != -1 && (player2[i][j] == 8 || player2[i][j] == -2)){
//                        j -= 1;
//                    }
//                    j += 1;
//
//
////                    System.out.println("чему Ш  2  " + i);
////                    System.out.println("чему Ш  4  " + j);
//                    while (j != player2[1].length && j != -1 && player2[i][j] == 8 && ((i + 1 != 10 && j + 1 != 10 && player2[i+1][j+1] == -2) || (i + 1 != 10 && j - 1 != -1 && player2[i+1][j-1] == -2) || (i + 1 != 10 && player2[i+1][j] == -2))) {
//                        //System.out.println("чему Ш  3" + i);
//                        String rect_id = "#shot2_" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j += 1;
//                    }
//
//    //                    System.out.println("i: " + i);
//    //                    System.out.println("j: " + j);
//                    while (j != player2[1].length && j != -1 && (player2[i][j] == 8 || player2[i][j] == -2)){
//                        j += 1;
//                    }
//                    j -= 1;
//
//
//                    while (i != player2[0].length && i != -1 && player2[i][j] == 8 && ((i - 1 != -1 && j - 1 != -1 && player2[i-1][j-1] == -2) || (i + 1 != 10 && j - 1 != -1 && player2[i+1][j-1] == -2) || (j - 1 != -1 && player2[i][j-1] == -2))) {
//                        String rect_id = "#shot2_" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i += 1;
//                    }
//                    while (i != player2[0].length && i != -1 && (player2[i][j] == 8 || player2[i][j] == -2)){
//                        i += 1;
//                    }
//                    i -= 1;
//
//                    while (j != player2[1].length && j != -1 && player2[i][j] == 8 && ((i - 1 != -1 && j - 1 != -1 && player2[i-1][j-1] == -2) || (i - 1 != -1 && j + 1 != 10 && player2[i-1][j+1] == -2) ||(i - 1 != -1 && player2[i-1][j] == -2))) {
//                        String rect_id = "#shot2_" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j -= 1;
//                    }
//                    while (j != player2[1].length && j != -1 && (player2[i][j] == 8 || player2[i][j] == -2)){
//                        j -= 1;
//                    }
//                    j += 1;
//
//                    while (i != player2[0].length && i != -1 && player2[i][j] == 8 && ((i + 1 != 10 && j + 1 != 10 && player2[i+1][j+1] == -2) || (j + 1 != 10 && i - 1 != -1 && player2[i-1][j+1] == -2)  || (j + 1 != 10 && player2[i][j+1] == -2))) {
//                        //System.out.println("12345678:   ");
//                        String rect_id = "#shot2_" + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i -= 1;
//                        //System.out.println("24g43g34g34:   " + rect_id);
//                    }
//                    while (i != player2[1].length && i != -1 && (player2[i][j] == 8 || player2[i][j] == -2)){
//                        i -= 1;
//                    }
//                    i += 1;
//                    System.out.println("---------------------");
                    PrintArray(player2);

                }
                if (score_player2 == 20) {
                    Stage window = new Stage();

//        Pane pane = new Pane();

                    Group root = new Group();
                    Scene scene = new Scene(root, 600,100);

                    Label label = new Label("Игрок " + name_player1 + " выиграл");
                    label.setAlignment(Pos.CENTER);
                    label.setStyle("-fx-font-size:40;-fx-font-size:40");

                    Button btn_ok = new Button("Да");
                    btn_ok.setLayoutX(300);
                    btn_ok.setLayoutY(60);


                    btn_ok.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            mediaPlayer.dispose();
                            mediaPlayer = new MediaPlayer(new Media(new File(songs[0]).toURI().toString()));
                            mediaPlayer.setVolume(sliderValue / 100);
                            time_sleep = 210000;
                            flag = true;
                            player = 1;
                            clear_matrix(player1);
//                            System.out.println("player 1-------------------------");
                            PrintArray(player1);

                            clear_matrix(player2);
//                            System.out.println("player 2-------------------------");
                            PrintArray(player2);

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
                            Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                            Stage window2 = (Stage)btn1.getScene().getWindow();
                            Scene scene = new Scene(pane);
                            window.close();
                            window2.close();
                            window1.setMaximized(true);
                            window1.setScene(scene);
                            window1.show();
                        }
                    });
                    root.getChildren().add(label);
                    root.getChildren().add(btn_ok);

                    window.setScene(scene);
                    window.initModality(Modality.WINDOW_MODAL);
//        window.initOwner(parentStage);
                    window.showAndWait();
                }

            }
            //polyg.getPoints().removeAll();

        }
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
        Scene scene = new Scene(root, 600,100);

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
                time_sleep = 210000;
                flag = true;
                player = 1;
                clear_matrix(player1);
//                System.out.println("player 1-------------------------");
                PrintArray(player1);

                clear_matrix(player2);
//                System.out.println("player 2-------------------------");
                PrintArray(player2);

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
                Stage window1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                Stage window2 = (Stage)btn1.getScene().getWindow();
                Scene scene = new Scene(pane);
                window.close();
                window2.close();
                window1.setScene(scene);
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
        rect2.setStyle(rect2Color);

        mediaPlayer.dispose();
        mediaPlayer = new MediaPlayer(new Media(new File(songs[1]).toURI().toString()));
        mediaPlayer.setVolume(sliderValue / 100);
        time_sleep = 480000;
        flag = true;
//        System.out.println(name_player1);
        text1.setText(name_player1);
        text2.setText(name_player2);
//        System.out.println(text1.getText());
    }
}
