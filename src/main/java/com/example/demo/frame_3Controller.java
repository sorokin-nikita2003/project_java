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
import static com.example.demo.Logic.PrintArray;
import static com.example.demo.Logic.in;

public class frame_3Controller {
    private AnchorPane anchorPane;
    public void getAnchorPane (AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }
    @FXML
    Button btn1;

    @FXML
    Label text1, text2;
    @FXML
    Polygon polyg;
    @FXML
    Rectangle shot00;
    @FXML
    Rectangle shot01;
@FXML
    Rectangle shot02;
@FXML
    Rectangle shot03;
@FXML
    Rectangle shot04;
@FXML
    Rectangle shot05;
@FXML
    Rectangle shot06;
@FXML
    Rectangle shot07;
@FXML
    Rectangle shot08;
@FXML
    Rectangle shot09;
@FXML
    Rectangle shot10;
@FXML
    Rectangle shot11;
@FXML
    Rectangle shot12;
@FXML
    Rectangle shot13;
@FXML
    Rectangle shot14;
@FXML
    Rectangle shot15;
@FXML
    Rectangle shot16;
@FXML
    Rectangle shot17;
@FXML
    Rectangle shot18;
@FXML
    Rectangle shot19;
@FXML
    Rectangle shot20;
@FXML
    Rectangle shot21;
@FXML
    Rectangle shot22;
@FXML
    Rectangle shot23;
@FXML
    Rectangle shot24;
@FXML
    Rectangle shot25;
@FXML
    Rectangle shot26;
@FXML
    Rectangle shot27;
@FXML
    Rectangle shot28;
@FXML
    Rectangle shot29;
@FXML
    Rectangle shot30;
@FXML
    Rectangle shot31;
@FXML
    Rectangle shot32;
@FXML
    Rectangle shot33;
@FXML
    Rectangle shot34;
@FXML
    Rectangle shot35;
@FXML
    Rectangle shot36;
@FXML
    Rectangle shot37;
@FXML
    Rectangle shot38;
@FXML
    Rectangle shot39;
@FXML
    Rectangle shot40;
@FXML
    Rectangle shot41;
@FXML
    Rectangle shot42;
@FXML
    Rectangle shot43;
@FXML
    Rectangle shot44;
@FXML
    Rectangle shot45;
@FXML
    Rectangle shot46;
@FXML
    Rectangle shot47;
@FXML
    Rectangle shot48;
@FXML
    Rectangle shot49;
@FXML
    Rectangle shot50;
@FXML
    Rectangle shot51;
@FXML
    Rectangle shot52;
@FXML
    Rectangle shot53;
@FXML
    Rectangle shot54;
@FXML
    Rectangle shot55;
@FXML
    Rectangle shot56;
@FXML
    Rectangle shot57;
@FXML
    Rectangle shot58;
@FXML
    Rectangle shot59;
@FXML
    Rectangle shot60;
@FXML
    Rectangle shot61;
@FXML
    Rectangle shot62;
@FXML
    Rectangle shot63;
@FXML
    Rectangle shot64;
@FXML
    Rectangle shot65;
@FXML
    Rectangle shot66;
@FXML
    Rectangle shot67;
@FXML
    Rectangle shot68;
@FXML
    Rectangle shot69;
@FXML
    Rectangle shot70;
@FXML
    Rectangle shot71;
@FXML
    Rectangle shot72;
@FXML
    Rectangle shot73;
@FXML
    Rectangle shot74;
@FXML
    Rectangle shot75;
@FXML
    Rectangle shot76;
@FXML
    Rectangle shot77;
@FXML
    Rectangle shot78;
@FXML
    Rectangle shot79;
@FXML
    Rectangle shot80;
@FXML
    Rectangle shot81;
@FXML
    Rectangle shot82;
@FXML
    Rectangle shot83;
@FXML
    Rectangle shot84;
@FXML
    Rectangle shot85;
@FXML
    Rectangle shot86;
@FXML
    Rectangle shot87;
@FXML
    Rectangle shot88;
@FXML
    Rectangle shot89;
@FXML
    Rectangle shot90;
@FXML
    Rectangle shot91;
@FXML
    Rectangle shot92;
@FXML
    Rectangle shot93;
@FXML
    Rectangle shot94;
@FXML
    Rectangle shot95;
@FXML
    Rectangle shot96;
@FXML
    Rectangle shot97;
@FXML
    Rectangle shot98;
@FXML
    Rectangle shot99;

@FXML Rectangle shot2_00;
@FXML Rectangle shot2_01;
@FXML
    Rectangle shot2_02;
@FXML
    Rectangle shot2_03;
@FXML
    Rectangle shot2_04;
@FXML
    Rectangle shot2_05;
@FXML
    Rectangle shot2_06;
@FXML
    Rectangle shot2_07;
@FXML
    Rectangle shot2_08;
@FXML
    Rectangle shot2_09;
@FXML
    Rectangle shot2_10;
@FXML
    Rectangle shot2_11;
@FXML
    Rectangle shot2_12;
@FXML
    Rectangle shot2_13;
@FXML
    Rectangle shot2_14;
@FXML
    Rectangle shot2_15;
@FXML
    Rectangle shot2_16;
@FXML
    Rectangle shot2_17;
@FXML
    Rectangle shot2_18;
@FXML
    Rectangle shot2_19;
@FXML
    Rectangle shot2_20;
@FXML
    Rectangle shot2_21;
@FXML
    Rectangle shot2_22;
@FXML
    Rectangle shot2_23;
@FXML
    Rectangle shot2_24;
@FXML
    Rectangle shot2_25;
@FXML
    Rectangle shot2_26;
@FXML
    Rectangle shot2_27;
@FXML
    Rectangle shot2_28;
@FXML
    Rectangle shot2_29;
@FXML
    Rectangle shot2_30;
@FXML
    Rectangle shot2_31;
@FXML
    Rectangle shot2_32;
@FXML
    Rectangle shot2_33;
@FXML
    Rectangle shot2_34;
@FXML
    Rectangle shot2_35;
@FXML
    Rectangle shot2_36;
@FXML
    Rectangle shot2_37;
@FXML
    Rectangle shot2_38;
@FXML
    Rectangle shot2_39;
@FXML
    Rectangle shot2_40;
@FXML
    Rectangle shot2_41;
@FXML
    Rectangle shot2_42;
@FXML
    Rectangle shot2_43;
@FXML
    Rectangle shot2_44;
@FXML
    Rectangle shot2_45;
@FXML
    Rectangle shot2_46;
@FXML
    Rectangle shot2_47;
@FXML
    Rectangle shot2_48;
@FXML
    Rectangle shot2_49;
@FXML
    Rectangle shot2_50;
@FXML
    Rectangle shot2_51;
@FXML
    Rectangle shot2_52;
@FXML
    Rectangle shot2_53;
@FXML
    Rectangle shot2_54;
@FXML
    Rectangle shot2_55;
@FXML
    Rectangle shot2_56;
@FXML
    Rectangle shot2_57;
@FXML
    Rectangle shot2_58;
@FXML
    Rectangle shot2_59;
@FXML
    Rectangle shot2_60;
@FXML
    Rectangle shot2_61;
@FXML
    Rectangle shot2_62;
@FXML
    Rectangle shot2_63;
@FXML
    Rectangle shot2_64;
@FXML
    Rectangle shot2_65;
@FXML
    Rectangle shot2_66;
@FXML
    Rectangle shot2_67;
@FXML
    Rectangle shot2_68;
@FXML
    Rectangle shot2_69;
@FXML
    Rectangle shot2_70;
@FXML
    Rectangle shot2_71;
@FXML
    Rectangle shot2_72;
@FXML
    Rectangle shot2_73;
@FXML
    Rectangle shot2_74;
@FXML
    Rectangle shot2_75;
@FXML
    Rectangle shot2_76;
@FXML
    Rectangle shot2_77;
@FXML
    Rectangle shot2_78;
@FXML
    Rectangle shot2_79;
@FXML
    Rectangle shot2_80;
@FXML
    Rectangle shot2_81;
@FXML
    Rectangle shot2_82;
@FXML
    Rectangle shot2_83;
@FXML
    Rectangle shot2_84;
@FXML
    Rectangle shot2_85;
@FXML
    Rectangle shot2_86;
@FXML
    Rectangle shot2_87;
@FXML
    Rectangle shot2_88;
@FXML
    Rectangle shot2_89;
@FXML
    Rectangle shot2_90;
@FXML
    Rectangle shot2_91;
@FXML
    Rectangle shot2_92;
@FXML
    Rectangle shot2_93;
@FXML
    Rectangle shot2_94;
@FXML
    Rectangle shot2_95;
@FXML
    Rectangle shot2_96;
@FXML
    Rectangle shot2_97;
@FXML
    Rectangle shot2_98;
@FXML
    Rectangle shot2_99;


    @FXML
    private void shot(MouseEvent event) {
        Rectangle clickedImageView = (Rectangle) event.getSource();
        int i = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(4)));
        System.out.println(i);
        int j = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(5)));
        System.out.println(j);
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
                clickedImageView.setFill(Color.GRAY);
                player1[i][j] = -1;

                int flag_left = 0;
                int flag_right = 0;
                int flag_top = 0;
                int flag_down = 0;
                System.out.println("length" + player1[0].length);

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
                System.out.println("ewfewfewf " + we);

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
                    System.out.println("3333" + player1[i][j]);
                    j -= 1;
                    if (j == player1[1].length || j == -1 || player1[i][j] == 8) {
                        flag_down = 1;
                        break;
                    }
                }
                j += 1;
                //System.out.println("3333" + player1[i][j]);

                while (player1[i][j] == -1) {
                    System.out.println("4444" + player1[i][j]);
                    j += 1;
                    if (j == player1[1].length || j == -1 || player1[i][j] == 8) {
                        flag_top = 1;
                        break;
                    }
                }
                j -= 1;
                //System.out.println("3333" + player1[i][j]);

                System.out.println("left " + flag_left);
                System.out.println("right " + flag_right);
                System.out.println("top " + flag_top);
                System.out.println("down " + flag_down);

                if (flag_down == 1 && flag_left == 1 && flag_top == 1 && flag_right == 1) {
                    PrintArray(player1);
                    Rectangle rectangle = null;
                    Scene scene = null;
                    while (player1[i][j] == -1) {
                        String rect_id = "#shot" + i + j;
                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        i -= 1;
                        if (i == player1[0].length || i == -1 || player1[i][j] == 8) {
                            break;
                        }
                    }
                    i += 1;
                    System.out.println("wefwe" + player1[i][j]);
                    while (player1[i][j] == -1) {
                        String rect_id = "#shot" + i + j;
                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        i += 1;
                        if (i == player1[0].length || i == -1 || player1[i][j] == 8) {
                            break;
                        }
                    }
                    i -= 1;
                    System.out.println("wefwe" + player1[i][j]);
                    while (player1[i][j] == -1) {
                        String rect_id = "#shot" + i + j;
                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        j += 1;
                        if (j == player1[1].length || j == -1 || player1[i][j] == 8) {
                            break;
                        }
                    }
                    j -= 1;
                    System.out.println("wefwe" + player1[i][j]);
                    while (player1[i][j] == -1) {
                        String rect_id = "#shot" + i + j;
                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        j -= 1;
                        if (j == player1[1].length || j == -1 || player1[i][j] == 8) {
                            break;
                        }
                    }
                    j += 1;
                    System.out.println("wefwe" + player1[i][j]);
                    PrintArray(player1);
                }
                PrintArray(player1);
            }
            //polyg.getPoints().removeAll();
        }
    }

    @FXML
    private void shot2(MouseEvent event) {
        Rectangle clickedImageView = (Rectangle) event.getSource();
        int i = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(6)));
        System.out.println(i);
        int j = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(7)));
        System.out.println(j);
        Paint polygColor = polyg.getFill();
        System.out.println(polygColor);
        if (polygColor == Color.GREEN && clickedImageView.getFill() != Color.BLACK && clickedImageView.getFill() != Color.GRAY) {
            //System.out.println(111111);
            if (player2[i][j] == 0 || player2[i][j] == 8) {
                clickedImageView.setFill(Color.BLUE);
                polyg.getPoints().setAll(50.0, 0.0,
                        50.0, 140.0,
                        0.0, 70.0);
                polyg.setFill(Color.RED);
            } else {
                clickedImageView.setFill(Color.GRAY);
                player2[i][j] = -1;

                int flag_left = 0;
                int flag_right = 0;
                int flag_top = 0;
                int flag_down = 0;
                System.out.println("length" + player2[0].length);

                while (player2[i][j] == -1) {
                    System.out.println("1111" + player2[i][j]);
                    i -= 1;
                    if (i == player2[0].length || i == -1 || player2[i][j] == 8) {
                        flag_left = 1;
                        //System.out.println("efwegfewg");
                        break;
                    }
                }
                i += 1;
                int we = player2[1].length - 1;
                System.out.println("ewfewfewf " + we);

                while (player2[i][j] == -1) {
                    System.out.println("2222" + player2[i][j]);
                    i += 1;
                    if (i == player2[0].length || i == -1 || player2[i][j] == 8) {
                        flag_right = 1;
                        break;
                    }
                }
                i -= 1;

                while (player2[i][j] == -1) {
                    System.out.println("3333" + player2[i][j]);
                    j -= 1;
                    if (j == player2[1].length || j == -1 || player2[i][j] == 8) {
                        flag_down = 1;
                        break;
                    }
                }
                j += 1;
                //System.out.println("3333" + player1[i][j]);

                while (player2[i][j] == -1) {
                    System.out.println("4444" + player2[i][j]);
                    j += 1;
                    if (j == player2[1].length || j == -1 || player2[i][j] == 8) {
                        flag_top = 1;
                        break;
                    }
                }
                j -= 1;
                //System.out.println("3333" + player1[i][j]);

                System.out.println("left " + flag_left);
                System.out.println("right " + flag_right);
                System.out.println("top " + flag_top);
                System.out.println("down " + flag_down);

                if (flag_down == 1 && flag_left == 1 && flag_top == 1 && flag_right == 1) {
                    PrintArray(player2);
                    Rectangle rectangle = null;
                    Scene scene = null;
                    while (player2[i][j] == -1) {
                        String rect_id = "#shot2_" + i + j;
                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        i -= 1;
                        if (i == player2[0].length || i == -1 || player2[i][j] == 8) {
                            break;
                        }
                    }
                    i += 1;
                    System.out.println("wefwe" + player2[i][j]);
                    while (player2[i][j] == -1) {
                        String rect_id = "#shot2_" + i + j;
                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        i += 1;
                        if (i == player2[0].length || i == -1 || player2[i][j] == 8) {
                            break;
                        }
                    }
                    i -= 1;
                    System.out.println("wefwe" + player2[i][j]);
                    while (player2[i][j] == -1) {
                        String rect_id = "#shot2_" + i + j;
                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        j += 1;
                        if (j == player2[1].length || j == -1 || player2[i][j] == 8) {
                            break;
                        }
                    }
                    j -= 1;
                    System.out.println("wefwe" + player2[i][j]);
                    while (player2[i][j] == -1) {
                        String rect_id = "#shot2_" + i + j;
                        System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        j -= 1;
                        if (j == player2[1].length || j == -1 || player2[i][j] == 8) {
                            break;
                        }
                    }
                    j += 1;
                    System.out.println("wefwe" + player2[i][j]);
                    PrintArray(player2);
                }
                PrintArray(player2);
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

                for (int i=0;i < player1[0].length;i++){
                    for (int j=0;j < player1[1].length ;j++){
                        player1[i][j]=0;
                    }
                }
                System.out.println("player 1-------------------------");
                PrintArray(player1);
                for (int i=0;i < player2[0].length;i++){
                    for (int j=0;j < player2[1].length ;j++){
                        player2[i][j]=0;
                    }
                }
                System.out.println("player 2-------------------------");
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
