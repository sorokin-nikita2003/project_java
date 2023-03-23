package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class frame_3Controller {
    @FXML
    Button btn1;

    @FXML
    private void handleButtonClickExit(ActionEvent event) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Pane pane = new Pane();

        Label label = new Label("Вы уверены, что хотите выйти?");
        label.setStyle("-fx-font-size:40;-fx-font-size:40");

        Button btn = new Button("Вы уверены, что хотите выйти?");

        Scene scene = new Scene(label, 600,100);

        window.setScene(scene);
        window.showAndWait();

    }
}
