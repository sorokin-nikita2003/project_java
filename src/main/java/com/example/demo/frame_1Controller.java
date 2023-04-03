package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import  javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class frame_1Controller {

    @FXML
    private Group ships;

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
                javafx.application.Platform.exit();
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
        window.showAndWait();
    }

//    @FXML
//    private void handleButtonHover() {
//        btn1.setCursor(Cursor.HAND);
//        btn2.setCursor(Cursor.HAND);
//        btn3.setCursor(Cursor.HAND);
//    }


}