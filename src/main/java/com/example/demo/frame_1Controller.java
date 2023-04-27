package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import  javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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
    private AnchorPane rect2;
    @FXML
    private AnchorPane rect1;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;

    @FXML
    private Group btn3;

    private String lastButtonPressed;

    @FXML
    private void handleButtonClickBot(ActionEvent event) throws IOException {
//        frame_2Controller controller = new frame_2Controller();
//        controller.setLastButtonPressed("bot");
//        controller.check();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_2.fxml"));
        Parent pane = loader.load();
        frame_2Controller controller = loader.getController();
        controller.setLastButtonPressed("bot");
//        scene.setCursor(Cursor.HAND);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane);
        window.centerOnScreen();
        window.setMaximized(true);
        window.setScene(scene);
        window.show();
    }
    @FXML
    private void handleButtonClickTogether(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_2.fxml"));
        Parent pane = loader.load();
        frame_2Controller controller = loader.getController();
        controller.setLastButtonPressed("together_1");
//        scene.setCursor(Cursor.HAND);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane);
        window.centerOnScreen();
        window.setMaximized(true);
        window.setScene(scene);
        window.show();
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
        btn_no.setLayoutX(100);
        btn_no.setLayoutY(60);


        Button btn_yes = new Button("Да");
        btn_yes.setLayoutX(400);
        btn_yes.setLayoutY(60);
        btn_yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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


        System.out.println(groupWidth2);
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