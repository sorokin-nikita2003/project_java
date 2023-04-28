package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class frame_2Controller {
    double x, y;
    @FXML
    private ImageView ship_4;
    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    private String lastButtonPressed;
    public void setLastButtonPressed(String lastButtonPressed) {
        this.lastButtonPressed = lastButtonPressed;
    }

    @FXML
    public void onImagePressed(MouseEvent event) {
//        String imageViewId = ((ImageView) event.getSource()).getId();
        ImageView clickedImageView = (ImageView) event.getSource();
        x = event.getX() - clickedImageView.getLayoutBounds().getWidth();
//        System.out.println(x);
        y = event.getY() - clickedImageView.getLayoutBounds().getHeight();
        clickedImageView.setCursor(Cursor.MOVE);
    }

    @FXML
    public void onImageDragged(MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();
        clickedImageView.setLayoutX(event.getSceneX() + x);
        clickedImageView.setLayoutY(event.getSceneY() + y);
    }

    public void onImageReleased(MouseEvent mouseEvent) {
//        if()
    }

    @FXML
    private void handleButtonClickBack(ActionEvent event) throws IOException {
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

    @FXML
    private void handleButtonClickForward(ActionEvent event) throws IOException {
        if (Objects.equals(lastButtonPressed, "bot") || Objects.equals(lastButtonPressed, "together_2")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_3.fxml"));
            Parent pane = loader.load();
//            frame_2Controller controller = loader.getController();
//            controller.setLastButtonPressed("bot");
//        scene.setCursor(Cursor.HAND);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane);
            window.setMaximized(true);
            window.setScene(scene);
            window.show();
        }
        else if (Objects.equals(lastButtonPressed, "together_1")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_2.fxml"));
            Parent pane = loader.load();
            frame_2Controller controller = loader.getController();
            controller.setLastButtonPressed("together_2");
//        scene.setCursor(Cursor.HAND);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(pane);
            window.setMaximized(true);
            window.setScene(scene);
            window.show();
        }
    }
}
