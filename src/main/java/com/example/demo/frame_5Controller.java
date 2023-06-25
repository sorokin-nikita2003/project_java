package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static com.example.demo.HelloApplication.*;

public class frame_5Controller {
    @FXML
    private Group achivement1, achivement2, achivement3, achivement4;
    private Group[] achivements_groups;

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
    public void initialize() {
        if (achivements_groups == null){
            achivements_groups = new Group[]{achivement1, achivement2, achivement3, achivement4};
        }
        for (int i = 0; i < 4; i++){
            if (achievement[i]){
                achivements_groups[i].setVisible(true);
            }
            else {
                achivements_groups[i].setVisible(false);
            }
        }
    }
}
