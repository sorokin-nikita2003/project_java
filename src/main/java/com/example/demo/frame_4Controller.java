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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import static com.example.demo.HelloApplication.*;

public class frame_4Controller {
    @FXML
    private Group group1;
    @FXML
    private Button btn6;
    @FXML
    private RadioButton btn_full, btn_window, btn_light, btn_dark;
    ToggleGroup group_btn_screan = new ToggleGroup();
    ToggleGroup group_btn_theme = new ToggleGroup();
    @FXML
    private Slider slider;
    @FXML
    public AnchorPane rect2;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane root;


    @FXML
    private void RadioButtonClick(ActionEvent event) throws IOException {
        Object obj = event.getSource();
        RadioButton btn = (RadioButton)obj;
        String value_btn = btn.getId().substring(4);
        switch (value_btn){
            case ("light")->{
                theme_color = value_btn;
                rect1Color = "-fx-background-color: linear-gradient(#1E6BFF, #2871FFAB, #6197FF5C)";;
                rect2Color = "-fx-background-color: linear-gradient(#001AFF5E, #001AFF00)";;
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_4.fxml"));
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //Logic.changeColor(loader, stage);
                rect2.setStyle(rect2Color);
            }
            case ("dark")->{
                theme_color = value_btn;;
                rect1Color = "-fx-background-color: linear-gradient(#66007A, #5C5C5C)";
                rect2Color = "-fx-background-color: linear-gradient(#6A300A, #001AFF00)";
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_4.fxml"));
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //Logic.changeColor(loader, stage);
                rect2.setStyle(rect2Color);


            }
            case ("full")->{
                full_screan = true;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_4.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Logic.changeScene(loader, stage);
            }
            case ("window")->{
                full_screan = false;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_4.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Logic.changeScene(loader, stage);
            }
        }

    }
    @FXML
    private void onPress(MouseEvent event) {
        mediaPlayer2.play();
        mediaPlayer2.seek(Duration.ZERO);
    }

    @FXML
    private void handleButtonClickOk(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setFullScreen(full_screan);
        stage.show();
    }
    public void onSliderReleased(MouseEvent mouseEvent) {
        mediaPlayer.setVolume((slider.getValue() / 100));
        sliderValue = slider.getValue();
    }
    public void updateVolume(MouseEvent event) {
//        MediaPlayer mediaPlayer = HelloApplication.getMediaPlayer();
//        double volume = slider.getValue() / 100.0;
//        mediaPlayer.setVolume(volume);
//        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
//            mediaPlayer.setVolume((Double) newValue);
//        });
//        sliderValue = slider.getValue();
//        System.out.println(slider.getValue());
//        System.out.println(slider.getValue() % 1);
    }
    public void initialize() {
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
//        double parentWidth1 = group1.getParent().getLayoutBounds().getWidth();
//        double parentHeight = group1.getParent().getLayoutBounds().getHeight();
        double groupWidth1 = group1.getBoundsInParent().getWidth();
//        double groupHeight = group1.getBoundsInParent().getHeight();
        group1.setLayoutX(screenWidth / 2 - groupWidth1 / 0.8);

        double screenHeight = Screen.getPrimary().getBounds().getHeight();
//        double parentWidth1 = group1.getParent().getLayoutBounds().getWidth();
//        double parentHeight = group1.getParent().getLayoutBounds().getHeight();
        double groupHeight1 = group1.getBoundsInParent().getHeight();
//        double groupHeight = group1.getBoundsInParent().getHeight();
        group1.setLayoutY(screenHeight / 2 - groupWidth1 / 1.5);
        rect2.setStyle(rect2Color);

        slider.setValue(sliderValue);

        btn_full.setToggleGroup(group_btn_screan);
        btn_window.setToggleGroup(group_btn_screan);

        btn_light.setToggleGroup(group_btn_theme);
        btn_dark.setToggleGroup(group_btn_theme);

        switch (theme_color){
            case ("light")->{
                btn_light.setSelected(true);
                btn_dark.setSelected(false);
            }
            case ("dark")->{
                btn_light.setSelected(false);
                btn_dark.setSelected(true);
            }
        }
        if (full_screan){
            btn_window.setSelected(false);
            btn_full.setSelected(true);
        }
        else {
            btn_window.setSelected(true);
            btn_full.setSelected(false);
        }
    }
}
