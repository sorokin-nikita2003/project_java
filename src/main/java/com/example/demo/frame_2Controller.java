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
import javafx.scene.transform.Rotate;
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
    int count_click = 0;
    int rotate_ship = 0;
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

        Object obj = event.getSource();
        ImageView img = (ImageView)obj;
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        int rotate = (int)img.getRotate();
        System.out.print("y: ");
        System.out.println(logic.index(mas_y,y)+"  "+ y);
        System.out.print("x: ");
        System.out.println(logic.index(mas_x,x)+"  "+ x);
        int index_y = logic.index(mas_y,y);
        int index_x = logic.index(mas_x,x);
        try{
            if(rotate == 90){
                index_x += 2;
                index_y -= 1;
                //y -= 73;
                //x += 73;
            }
            logic.clear_ship(index_y, index_x,rotate);
            //player1[logic.index(mas_y,y)][ logic.index(mas_x,x)] = 0;
        }
        catch (Exception e){

        }

    }
    @FXML
    public void onImageClicked(MouseEvent event) {
        count_click  += 1;
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
        Object obj = event.getSource();
        ImageView img = (ImageView)obj;
        /**if (logic.turn_ship(mas_x,mas_y,img, count_click)){
            count_click = 0;
        }**/
        int rotate = (int)img.getRotate();
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        int index_y = logic.index(mas_y,y);
        int index_x = logic.index(mas_x,x);

        if(count_click == 2){
            try{
                if(rotate == 90){
                    index_x += 2;
                    index_y -= 1;

                }
                logic.clear_ship(index_y, index_x,rotate);
                //player1[logic.index(mas_y,y)][ logic.index(mas_x,x)] = 0;
            }
            catch (Exception e){

            }
            System.out.println("Rotate");
            if (img.getRotate() == 0){
                img.setRotate(90);
                //img.setLayoutX(img.getLayoutX()-73);
                //img.setLayoutY(img.getLayoutY()+73);
            }
            else  {
                //img.setLayoutX(img.getLayoutX()+73);
                //img.setLayoutY(img.getLayoutY()-73);
                img.setRotate(0);
                //img.setLayoutX(img.getLayoutX()+73);
                //img.setLayoutY(img.getLayoutY()-73);
            }
            logic.set_ship(mas_x,mas_y, img);
        }
        System.out.println("--------------------------------------------");
        logic.PrintArray(player1);
    }

    @FXML
    public void onImageDragged(MouseEvent event) {
        count_click = 0;
        ImageView clickedImageView = (ImageView) event.getSource();
        clickedImageView.setLayoutX(event.getSceneX() + x);
        clickedImageView.setLayoutY(event.getSceneY() + y);
    }

    public void onImageReleased(MouseEvent mouseEvent) {
        Object obj = mouseEvent.getSource();
        ImageView img = (ImageView)obj;
        logic.set_ship(mas_x, mas_y, img);
    }

    @FXML
    private void handleButtonClickBack(ActionEvent event) throws IOException {
        count_click = 0;
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
        count_click = 0;
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
