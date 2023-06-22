package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo.HelloApplication.*;
import static com.example.demo.Logic.WorkFile.write_file;

public class frame_1Controller implements Initializable {
    @FXML
    private Group group1;
    @FXML
    private Group group2;
    @FXML
    private VBox frame1;
    @FXML
    private ResourceBundle rb;
    @FXML
    private Group ships;
    @FXML
    public AnchorPane rect2;
    @FXML
    public AnchorPane rect1;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    static public Button btn4;
    @FXML
    private Group btn3;
    private String lastButtonPressed;

    @FXML
    private void onPress(MouseEvent event) {
        //MediaPlayer mediaPlayer2 = HelloApplication.getMediaPlayer2();
        //mediaPlayer2.setVolume((double) volume);
        //MediaPlayer mediaPlayer2 = HelloApplication.mediaPlayer2();
        mediaPlayer2.play();
        mediaPlayer2.seek(Duration.ZERO);
    }
    @FXML
    private void handleButtonClickBot(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Logic.changeScene(loader, stage);
        frame_2Controller controller = loader.getController();
        controller.setLastButtonPressed("bot");
    }
    @FXML
    private void handleButtonClickTogether(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Logic.changeScene(loader, stage);
        frame_2Controller controller = loader.getController();
        controller.setLastButtonPressed("together_1");
    }

    @FXML
    private void handleButtonClick_3(ActionEvent event) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane pane = new Pane();

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
                write_file(file);
                Logic.stop_tread_music();
                Platform.exit();
                System.exit(0);
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
        window.show();
    }
    @FXML
    public void handleButtonClickSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_4.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.setFullScreen(full_screan);
//        stage.show();
        Logic.changeScene(loader, stage);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        scene2 = new Scene(FXMLLoader.load(getClass().getResource("frame_2.fxml")));
//        scene3 = new Scene(FXMLLoader.load(getClass().getResource("frame_3.fxml")));
//        scene4 = new Scene(FXMLLoader.load(getClass().getResource("frame_4.fxml")));
        //rect1.setStyle("-fx-background-color: BLACK;");
        //System.out.println(rect1Color);
        rect1.setStyle(rect1Color);
        rect2.setStyle(rect2Color);

        name_player1 = "Игрок 1";
        name_player2 = "Игрок 2";

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
//        double parentWidth1 = group1.getParent().getLayoutBounds().getWidth();
//        double parentHeight = group1.getParent().getLayoutBounds().getHeight();
        double groupWidth1 = group1.getBoundsInParent().getWidth();
//        double groupHeight = group1.getBoundsInParent().getHeight();
        group1.setLayoutX(screenWidth/2 - groupWidth1/1.3);
//        double screenWidth2 = Screen.getPrimary().getBounds().getWidth();
//        double parentWidth2 = group1.getParent().getLayoutBounds().getWidth();
//        double parentHeight = group1.getParent().getLayoutBounds().getHeight();
        double groupWidth2 = group2.getBoundsInParent().getWidth();
//        double groupHeight = group1.getBoundsInParent().getHeight();
        group2.setLayoutX(screenWidth/2 - groupWidth2/2);
//        group1.setLayoutY((parentHeight + groupHeight) / 2);
//        btn3.setLayoutX(100);


        //System.out.println(groupWidth2);
//        rect1.setAlignment(Pos.CENTER);

//        btn1.setLayoutX(screenWidth/2);
//        frame1.setAlignment(btn1 ,Pos.CENTER);
//        frame1.setPrefWidth(vboxWidth);
//        vBox.setPrefHeight(vboxHeight);
    }

//    @FXML
//    private void handleButtonHover() {
//        btn1.setCursor(Cursor.HAND);
//        btn2.setCursor(Cursor.HAND);
//        btn3.setCursor(Cursor.HAND);
//    }
//    public static void main(String[] args) {
//        System.out.println("2324");
//    }
}