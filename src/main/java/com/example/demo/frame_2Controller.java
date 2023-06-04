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
import java.util.Arrays;
import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static com.example.demo.HelloApplication.player1;
import static com.example.demo.HelloApplication.player2;

public class frame_2Controller {
    private Logic logic = new Logic();
    private int[] mas_x = {152, 201, 249, 297, 345, 392, 441, 488, 537, 584};
    private int[] mas_y = {258, 306, 354, 402, 450, 498, 546, 594, 642, 690};
    double x, y;
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

        try{
            Object obj = event.getSource();
            ImageView img = (ImageView)obj;
            int x = (int)img.getLayoutX();
            int y = (int)img.getLayoutY();
            logic.clear_ship(logic.index(mas_y,y), logic.index(mas_x,x));
            //player1[logic.index(mas_y,y)][ logic.index(mas_x,x)] = 0;
        }
        catch (Exception e){

        }

    }
    @FXML
    public void onImageClicked(MouseEvent event) {
        /**int id = 0;
        Object obj = event.getSource();
        ImageView img = (ImageView)obj;
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        switch (img.getId().substring(0, 5)){
            case ("Ship1") -> {
                id = 1;
            }
            case ("Ship2") -> {
                id = 2;
            }
            case ("Ship3") -> {
                id = 3;
            }
            case ("Ship4") -> {
                id = 4;
            }
        }
        try{
            logic.clear_ship(logic.index(mas_y,y), logic.index(mas_x,x));
            if(logic.chek_ship_and_around_for_turn(y, x, id)){
                logic.turn_ship(y, x, id);
            }
            else {
                logic.set_ship(logic.index(mas_y,y), logic.index(mas_x,x), id);
            }
        }
        catch (Exception e){
            System.out.println(logic.index(mas_x,x));
            System.out.println(logic.index(mas_y,y));
        }**/
    }

    @FXML
    public void onImageDragged(MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();
        clickedImageView.setLayoutX(event.getSceneX() + x);
        clickedImageView.setLayoutY(event.getSceneY() + y);
    }

    public void onImageReleased(MouseEvent mouseEvent) {
        int last_x = 0;
        int last_y = 0;
        Object obj = mouseEvent.getSource();
        ImageView img = (ImageView)obj;
        //System.out.println(ship_1.getLayoutX());
        //System.out.println();
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        for (int i = 0; i < 9; i++){
            if(Math.abs(x - mas_x[i]) <= Math.abs(x - mas_x[i+1]) ){
                last_x = mas_x[i];
            }
            else {
                last_x = mas_x[i+1];
            }
            if (last_x == mas_x[i]){
                break;
            }
        }
        for (int j = 0; j < 9; j++){
            if(Math.abs(y - mas_y[j]) <= Math.abs(y - mas_y[j+1]) ){
                last_y = mas_y[j];
            }
            else {
                last_y = mas_y[j+1];
            }
            if (last_y == mas_y[j]){
                break;
            }
        }
        int id = logic.id_ship(img.getId().substring(0, 5));

        //int index = Arrays.indexOf(mas_x, 42); // 4
        System.out.println(logic.index(mas_x,last_x));
        System.out.println(logic.index(mas_y,last_y));
        try {
            if (logic.chek_ship_and_around(logic.index(mas_y,last_y), logic.index(mas_x,last_x), id) && logic.set_ship(logic.index(mas_y,last_y), logic.index(mas_x,last_x), id)){
                img.setLayoutX(last_x);
                img.setLayoutY(last_y);
            }
            else {
                int default_x = logic.default_ship_x(img.getId());
                int default_y = logic.default_ship_y(img.getId());
                /**switch (img.getId()){
                 case("Ship1_1")->{
                 default_x = 769;
                 default_y = 546;
                 }
                 case("Ship1_2")->{
                 default_x = 879;
                 default_y = 546;
                 }
                 case("Ship1_3")->{
                 default_x = 994;
                 default_y = 546;
                 }
                 case("Ship1_4")->{
                 default_x = 1104;
                 default_y = 546;
                 }
                 case("Ship2_1")->{
                 default_x = 769;
                 default_y = 449;
                 }
                 case("Ship2_2")->{
                 default_x = 925;
                 default_y = 449;
                 }
                 case("Ship2_3")->{
                 default_x = 1095;
                 default_y = 449;
                 }
                 case("Ship3_1")->{
                 default_x = 769;
                 default_y = 353;
                 }
                 case("Ship3_2")->{
                 default_x = 980;
                 default_y = 353;
                 }
                 case("Ship4_1")->{
                 default_x = 769;
                 default_y = 257;
                 }
                 }**/
                img.setLayoutX(default_x);
                img.setLayoutY(default_y);
            }
        }catch (Exception e){
            int default_x = logic.default_ship_x(img.getId());
            int default_y = logic.default_ship_y(img.getId());
            img.setLayoutX(default_x);
            img.setLayoutY(default_y);
        }

        System.out.println(player1[logic.index(mas_y,last_y)][logic.index(mas_x,last_x)]);
        logic.PrintArray(player1);
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
