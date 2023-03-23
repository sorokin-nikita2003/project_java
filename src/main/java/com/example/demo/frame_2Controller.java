package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class frame_2Controller {
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private void handleButtonClickBack(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("frame_1.fxml"));
        Scene scene = new Scene(pane);
//        scene.setCursor(Cursor.HAND);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void handleButtonClickForward(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("frame_3.fxml"));
        Scene scene = new Scene(pane);
//        scene.setCursor(Cursor.HAND);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
