package com.example.demo;

import javafx.application.HostServices;
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
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

import static com.example.demo.HelloApplication.mediaPlayer2;
import static com.example.demo.HelloApplication.sliderValue;


public class frame_4Controller {
    @FXML
    private Button btn1;

    @FXML
    private Slider slider;

//    private MediaPlayer mediaPlayer;
//    public void settVolume(MediaPlayer mediaPlayer) {
//        this.mediaPlayer = mediaPlayer;
//    }

    @FXML
    private void onPress(MouseEvent event) {
        //MediaPlayer mediaPlayer2 = HelloApplication.getMediaPlayer2();
        //mediaPlayer2.setVolume((double) volume);
        //MediaPlayer mediaPlayer2 = HelloApplication.mediaPlayer2();
        mediaPlayer2.play();
        mediaPlayer2.seek(Duration.ZERO);
    }

    @FXML
    private void handleButtonClickOk(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_1.fxml"));
        Parent pane = loader.load();
//        scene.setCursor(Cursor.HAND);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(pane);
        window.centerOnScreen();
        window.setMaximized(true);
        window.setScene(scene);
        window.show();
    }

    public void updateVolume(MouseEvent event) {
        //mediaPlayer.setVolume(slider.getValue());
        MediaPlayer mediaPlayer = HelloApplication.getMediaPlayer();
        double volume = slider.getValue() / 100.0;
        mediaPlayer.setVolume(volume);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume((Double) newValue);
        });
        sliderValue = slider.getValue();
    }
    public void initialize() {
        slider.setValue(sliderValue);
    }
}
