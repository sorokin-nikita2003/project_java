package com.example.demo;

import javafx.event.ActionEvent;
import  javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
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

    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("frame_2.fxml"));
        Scene scene = new Scene(pane);
//        scene.setCursor(Cursor.HAND);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

//    @FXML
//    private void handleButtonHover() {
//        btn1.setCursor(Cursor.HAND);
//        btn2.setCursor(Cursor.HAND);
//        btn3.setCursor(Cursor.HAND);
//    }


}