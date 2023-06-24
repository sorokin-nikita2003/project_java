package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.demo.HelloApplication.*;
import static com.example.demo.HelloApplication.player;
import static com.example.demo.Logic.Clear.clear_matrix;
import static com.example.demo.Logic.Support.PrintArray;


// 0, 8 - путо
// 9 - промах
// -1 - попадание
// -2 - уничтожен
// 1 - одиночный
// 2 - двойной
// 3 - тойной
// 4 - четверной
//

public class Logic {
    private static int score_player = 0;
    static int score = 0;
    static ArrayDeque<Integer> ship_images = new ArrayDeque<Integer>();
    static Random random = new Random();
    static Scanner in = new Scanner(System.in);
    static Label game_time;
    static boolean status_work_time_game;
    static boolean status_work_music;
    protected static class Support {
        protected static void PrintArray(int[][] Array) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(" " + Array[i][j] + " ");
                }
                System.out.println();
            }
        }

        protected static int count_ships(int[][] matrix) {
            int res = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    switch (matrix[i][j]) {
                        case (1), (2), (3), (4) -> {
                            res += 1;
                        }
                    }
                }
            }
            return res;
        }

        private static int index(int[] Array, int search_el) {
            int in = 0;
            for (int el : Array) {
                if (el == search_el) {
                    break;
                }
                in += 1;
            }
            return in;
        }

        private static int index_turn_x(int[] Array, int search_el, int id) {
            int in = 0;
            for (int el : Array) {
                if (el == search_el) {
                    break;
                }
                in += 1;
            }
            in = in / 2;
            if (id == 2) {
                in -= 1;
            }
            return in;
        }

        private static int index_turn_y(int[] Array, int search_el, int id) {
            int in = 0;
            for (int el : Array) {
                if (el == search_el) {
                    break;
                }
                in += 1;
            }
            in = in / 2;
            if (id == 4) {
                in -= 1;
            }
            return in;
        }

        private static int index_turn_x_random(int search_index, int id) {
            if (id == 2) {
                search_index += 1;
            }
            search_index = search_index * 2;
            if (id == 3) {
                search_index += 1;
            }
            return search_index;
        }

        private static int index_turn_y_random(int search_index, int id) {
            if (id == 4) {
                search_index += 1;
            }
            search_index = search_index * 2;

            if (id == 3) {
                search_index += 1;
            }
            return search_index;
        }

        private static void update_matrix(int[][] matrix) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    switch (matrix[i][j]) {
                        case (1), (2), (3), (4) -> {
                            around(i, j, matrix, 0, 8);
                        }
                    }
                }
            }
        }

        private static int id_ship(String id_ship) {
            int res = 0;
            switch (id_ship) {
                case ("Ship1") -> {
                    res = 1;
                }
                case ("Ship2") -> {
                    res = 2;
                }
                case ("Ship3") -> {
                    res = 3;
                }
                case ("Ship4") -> {
                    res = 4;
                }
            }
            return res;
        }

        private static void around(int i, int j, int[][] matrix, int target, int value) { //0, 0
            int xi = i - 1;
            for (int cx = 1; cx <= 3; cx++) {
                int yj = j - 1;
                for (int cy = 1; cy <= 3; cy++) {
                    if ((xi >= 0 && xi < 10) && (yj >= 0 && yj < 10) && (matrix[xi][yj] == target)) {
                        matrix[xi][yj] = value;
                    }
                    yj += 1;
                }
                xi += 1;
            }
        }
    }

    protected static class Clear {
        private static void clear_ship(int y, int x, int rotate, int[][] matrix) {
            int power_ship = matrix[y][x];
            try {
                switch (rotate) {
                    case (0) -> {
                        for (int i = 0; i < power_ship; i++) {
                            matrix[y][x + i] = 0;
                            Support.around(y, x + i, matrix, 8, 0);
                        }
                    }
                    case (90) -> {
                        for (int i = 0; i < power_ship; i++) {
                            matrix[y + i][x] = 0;
                            Support.around(y + i, x, matrix, 8, 0);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        protected static void clear(int[] mas_x, int[] mas_y, int rotate, ImageView img, int[][] matrix) {
            int x = (int) img.getLayoutX();
            int y = (int) img.getLayoutY();
            int id = Support.id_ship(img.getId().substring(0, 5));
            try {
                switch (rotate) {
                    case (0) -> {
                        clear_ship(Support.index(mas_y, y), Support.index(mas_x, x), rotate, matrix);
                    }
                    case (90) -> {
                        clear_ship(Support.index_turn_y(mas_y, y, id), Support.index_turn_x(mas_x, x, id), rotate, matrix);
                    }
                }
                Support.update_matrix(matrix);
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }

        protected static void clear_matrix(int[][] matrix) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private static class Checks {
        private static boolean chek_ship_and_around(int y, int x, int power_ship, int rotate, int[][] matrix) {
            boolean res = true;
            switch (rotate) {
                case (0) -> {
                    for (int i = 0; i < power_ship; i++) {
                        switch (matrix[y][x + i]) {
                            case (1), (4), (3), (2), (8) -> {
                                res = false;
                            }
                        }
                    }
                }
                case (90) -> {
                    for (int i = 0; i < power_ship; i++) {
                        switch (matrix[y + i][x]) {
                            case (1), (4), (3), (2), (8) -> {
                                res = false;
                            }
                        }
                    }
                }
            }
            return res;
        }

        private static boolean chek_ship_and_around_for_turn(int y, int x, int power_ship, int[][] matrix) {
            boolean res = true;
            for (int i = 0; i < power_ship; i++) {
                switch (matrix[y + i][x]) {
                    case (1), (4), (3), (2), (8) -> {
                        res = false;
                    }
                }
            }
            return res;
        }

        private static boolean check_for_move(int x, int y, int power, int move_ship, int[][] matrix) {
            boolean result = false;
            int len = 0;
            try {
                switch (move_ship) {
                    case (0) -> {
                        for (int i = 1; i <= power; i++) {
                            if (matrix[x + i][y] != 0) {
                                break;
                            } else {
                                len += 1;
                            }
                        }
                    }
                    case (1) -> {
                        for (int i = 1; i <= power; i++) {
                            if (matrix[x - i][y] != 0) {
                                break;
                            } else {
                                len += 1;
                            }
                        }
                    }
                    case (2) -> {
                        for (int i = 1; i <= power; i++) {
                            if (matrix[x][y + i] != 0) {
                                break;
                            } else {
                                len += 1;
                            }
                        }
                    }
                    case (3) -> {
                        for (int i = 1; i <= power; i++) {
                            if (matrix[x][y - i] != 0) {
                                break;
                            } else {
                                len += 1;
                            }
                        }
                    }
                }
                if (check_len(len, power)) {
                    result = true;
                }
            } catch (Exception e) {
                return result;
            }
            return result;
        }

        private static boolean check_root(int x, int y, int power, int[][] matrix) {
            boolean result = true;
            int len = 0;
            try {
                for (int i = 1; i <= power; i++) {
                    if (matrix[x + i][y] != 0) {
                        break;
                    } else {
                        len += 1;
                    }
                }
                if (check_len(len, power)) {
                    return result;
                }

                len = 0;
                for (int i = 1; i <= power; i++) {
                    if (matrix[x][y + i] != 0) {
                        break;
                    } else {
                        len += 1;
                    }
                }
                if (check_len(len, power)) {
                    return result;
                }
                result = false;
            } catch (Exception e) {
                len = 0;
                for (int i = 1; i <= power; i++) {
                    if (matrix[x - i][y] != 0) {
                        break;
                    } else {
                        len += 1;
                    }
                }
                if (check_len(len, power)) {
                    return result;
                }
                len = 0;
                for (int i = 1; i <= power; i++) {
                    if (matrix[x][y - i] != 0) {
                        break;
                    } else {
                        len += 1;
                    }
                }
                if (check_len(len, power)) {
                    return result;
                }
            }
            return result;
        }

        private static boolean check_len(int len, int power) {
            return len == power;
        }

        private static boolean chek_ship(int x, int y, int[][] matrix, int ship_power) {
            boolean res = false;
            if ((x >= 0 && x < 10) && (y >= 0 && y < 10)) {
                if (ship_power == matrix[x][y]) {
                    res = true;
                }
            }
            return res;
        }

        private static boolean chek_ship_for_refresh(int x, int y, int[][] matrix, int ship_power) {
            boolean res = false;
            if ((x >= 0 && x < 10) && (y >= 0 && y < 10)) {
                if (ship_power == matrix[x][y] || matrix[x][y] == -1 || matrix[x][y] == ship_power - 1) {
                    res = true;
                }
            }
            return res;
        }

        private static boolean ships_around(int i, int j, int[][] matrix) {
            int xi = i - 1;
            for (int cx = 1; cx <= 3; cx++) {
                int yj = j - 1;
                for (int cy = 1; cy <= 3; cy++) {
                    if ((xi >= 0 && xi < 10) && (yj >= 0 && yj < 10)) {
                        if (matrix[xi][yj] == 2 || matrix[xi][yj] == 3 || matrix[xi][yj] == 4) {
                            return true;
                        }
                    }
                    yj += 1;
                }
                xi += 1;
            }
            return false;
        }

        protected static boolean cheks_sets_random_ships(int[][] matrix) {
            boolean res;
            try {
                while (ship_images.peek() != null) {
                    ship_images.pop();
                    ship_images.pop();
                }
                clear_matrix(matrix);
                RandomShips.generate_ships(matrix);
                res = true;
            } catch (Exception m) {
                res = false;
            }
            return res;
        }
    }

    protected static class SetShips {
        private static boolean set_ship_on_matrix(int y, int x, int power_ship, int rotate, int[][] matrix) {
            boolean res;
            try {
                switch (rotate) {
                    case (0) -> {
                        for (int i = 0; i < power_ship; i++) {
                            matrix[y][x + i] = power_ship;
                            Support.around(y, x + i, matrix, 0, 8);
                        }
                    }
                    case (90) -> {
                        for (int i = 0; i < power_ship; i++) {
                            matrix[y + i][x] = power_ship;
                            Support.around(y + i, x, matrix, 0, 8);
                        }
                    }
                }
                res = true;
            } catch (Exception e) {
                res = false;
            }
            return res;
        }

        protected static void set_ship(ImageView img, int[][] matrix) {
            int last_x = 0;
            int last_y = 0;
            int x = (int) img.getLayoutX();
            int y = (int) img.getLayoutY();
            for (int i = 0; i < 9; i++) {
                if (Math.abs(x - HelloApplication.mas_x[i]) <= Math.abs(x - HelloApplication.mas_x[i + 1])) {
                    last_x = HelloApplication.mas_x[i];
                } else {
                    last_x = HelloApplication.mas_x[i + 1];
                }
                if (last_x == HelloApplication.mas_x[i]) {
                    break;
                }
            }
            for (int j = 0; j < 9; j++) {
                if (Math.abs(y - HelloApplication.mas_y[j]) <= Math.abs(y - HelloApplication.mas_y[j + 1])) {
                    last_y = HelloApplication.mas_y[j];
                } else {
                    last_y = HelloApplication.mas_y[j + 1];
                }
                if (last_y == HelloApplication.mas_y[j]) {
                    break;
                }
            }
            int id = Support.id_ship(img.getId().substring(0, 5));
            int index_y = Support.index(HelloApplication.mas_y, last_y);
            int index_x = Support.index(HelloApplication.mas_x, last_x);
            try {
                if (Checks.chek_ship_and_around(index_y, index_x, id, 0, matrix) && set_ship_on_matrix(index_y, index_x, id, 0, matrix)) {
                    img.setLayoutX(last_x);
                    img.setLayoutY(last_y);
                } else {
                    int default_x = Default.default_ship_x(img.getId());
                    int default_y = Default.default_ship_y(img.getId());
                    img.setLayoutX(default_x);
                    img.setLayoutY(default_y);
                    img.setRotate(0);
                }
            } catch (Exception e) {
                int default_x = Default.default_ship_x(img.getId());
                int default_y = Default.default_ship_y(img.getId());
                img.setLayoutX(default_x);
                img.setLayoutY(default_y);
                img.setRotate(0);
            }
            //PrintArray(matrix);
        }

        protected static void set_turn_ship(ImageView img, int[][] matrix) {
            int last_x = 0;
            int last_y = 0;
            int x = (int) img.getLayoutX();
            int y = (int) img.getLayoutY();
            int id = Support.id_ship(img.getId().substring(0, 5));
            int t = 2;
            int index_x = 100;
            int index_y = 100;
            int lenght_mas_y = 100;
            switch (id) {
                case (2) -> {
                    index_x = 2;
                    index_y = 0;
                    lenght_mas_y = 8;
                }
                case (3) -> {
                    index_x = 1;
                    index_y = 1;
                    lenght_mas_y = 7;
                }
                case (4) -> {
                    index_x = 0;
                    index_y = 2;
                    lenght_mas_y = 6;
                }
            }
            for (int i = 0; i < 9; i++) {
                if (Math.abs(x - HelloApplication.mas_x_turn[index_x]) <= Math.abs(x - HelloApplication.mas_x_turn[index_x + t])) {
                    last_x = HelloApplication.mas_x_turn[index_x];
                } else {
                    last_x = HelloApplication.mas_x_turn[index_x + t];
                }
                if (last_x == HelloApplication.mas_x_turn[index_x]) {
                    break;
                }
                index_x += t;
            }
            for (int j = 0; j < lenght_mas_y; j++) {
                if (Math.abs(y - HelloApplication.mas_y_turn[index_y]) <= Math.abs(y - HelloApplication.mas_y_turn[index_y + t])) {
                    last_y = HelloApplication.mas_y_turn[index_y];
                } else {
                    last_y = HelloApplication.mas_y_turn[index_y + t];
                }
                if (last_y == HelloApplication.mas_y_turn[index_y]) {
                    break;
                }
                index_y += t;
            }
            index_y = Support.index_turn_y(HelloApplication.mas_y_turn, last_y, id);
            index_x = Support.index_turn_x(HelloApplication.mas_x_turn, last_x, id);
            try {
                if (Checks.chek_ship_and_around(index_y, index_x, id, 90, matrix) && set_ship_on_matrix(index_y, index_x, id, 90, matrix)) {
                    img.setLayoutX(last_x);
                    img.setLayoutY(last_y);
                } else {
                    int default_x = Default.default_ship_x(img.getId());
                    int default_y = Default.default_ship_y(img.getId());
                    img.setLayoutX(default_x);
                    img.setLayoutY(default_y);
                    img.setRotate(0);
                }
            } catch (Exception e) {
                int default_x = Default.default_ship_x(img.getId());
                int default_y = Default.default_ship_y(img.getId());
                img.setLayoutX(default_x);
                img.setLayoutY(default_y);
                img.setRotate(0);
            }
        }
    }

    protected static class RandomShips {
        private static int rotation_for_random(int y, int x, int[][] matrix) {
            int res;
            try {
                if (matrix[y + 1][x] == matrix[y][x]) {
                    res = 90;
                } else {
                    res = 0;
                }
            } catch (Exception e) {
                res = 0;
            }
            return res;
        }

        protected static void random_set_ship_image(ImageView img, int[][] matrix) {
            int y = ship_images.pop();
            int x = ship_images.pop();
            switch (rotation_for_random(y, x, matrix)) {
                case (0) -> {
                    img.setRotate(0);
                    img.setLayoutX(mas_x[x]);
                    img.setLayoutY(mas_y[y]);
                }
                case (90) -> {
                    img.setRotate(90);
                    img.setLayoutX(mas_x_turn[Support.index_turn_x_random(x, Support.id_ship(img.getId().substring(0, 5)))]);
                    img.setLayoutY(mas_y_turn[Support.index_turn_y_random(y, Support.id_ship(img.getId().substring(0, 5)))]);
                }
            }
        }

        protected static void generate_ships(int[][] matrix) {
            score = 0;
            int[] nymbers_ships = {4, 3, 2, 1};
            for (int type_ship = 3; type_ship >= 0; type_ship--) {
                while (nymbers_ships[type_ship] != 0) {
                    try {
                        random_set_ship_matrix(type_ship + 1, matrix);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        nymbers_ships[type_ship] -= 1;
                    }
                }
            }
        }

        private static void random_set_ship_matrix(int ship, int[][] matrix) throws Exception {
            ArrayDeque<Integer> index_ship = new ArrayDeque<Integer>();
            int index_x;
            int index_y;
            int min_index = 20;
            int min_x = 0;
            int min_y = 0;
            int power_ship = ship - 1;
            int x1;
            int y1;
            int move;
            do {
                x1 = random.nextInt(10);
                y1 = random.nextInt(10);
            } while (matrix[x1][y1] != 0 || !Checks.check_root(x1, y1, ship, matrix));

            System.out.println("x1:" + x1);
            System.out.println("y1:" + y1);


            matrix[x1][y1] = ship;
            index_ship.add(x1);
            index_ship.add(y1);
            int i = 0;
            while (true) {
                move = random.nextInt(4);
                if (Checks.check_for_move(x1, y1, ship, move, matrix)) {
                    break;
                }
                if (i == 1000) {
                    System.out.println("Error");
                    throw new Exception("Error");
                }
                i += 1;
            }
            int new_power_ship = power_ship;
            while (power_ship > 0) {
                switch (move) {
                    case (0) -> {
                        if (matrix[x1 + 1][y1] == 0) {
                            matrix[x1 + 1][y1] = ship;
                            x1 = x1 + 1;
                            new_power_ship -= 1;
                        }
                    }
                    case (1) -> {
                        if (matrix[x1 - 1][y1] == 0) {
                            matrix[x1 - 1][y1] = ship;
                            x1 = x1 - 1;
                            new_power_ship -= 1;
                        }
                    }
                    case (2) -> {
                        if (matrix[x1][y1 + 1] == 0) {
                            matrix[x1][y1 + 1] = ship;
                            y1 = y1 + 1;
                            new_power_ship -= 1;
                        }
                    }
                    case (3) -> {
                        if (matrix[x1][y1 - 1] == 0) {
                            matrix[x1][y1 - 1] = ship;
                            y1 = y1 - 1;
                            new_power_ship -= 1;
                        }
                    }
                }
                if (new_power_ship != power_ship) {
                    index_ship.add(x1);
                    index_ship.add(y1);
                }
                power_ship = new_power_ship;
            }
            while (index_ship.peek() != null) {
                index_x = index_ship.pop();
                index_y = index_ship.pop();
                if ((index_x + index_y) < min_index) {
                    min_index = (index_x + index_y);
                    min_x = index_x;
                    min_y = index_y;
                }
                Support.around(index_x, index_y, matrix, 0, 8);
            }
            ship_images.add(min_x);
            ship_images.add(min_y);
            score += 1;
        }

        protected static void set_random_ships(ImageView[] ships, int[][] matrix) {
            while (!(Checks.cheks_sets_random_ships(matrix))) {
                System.out.println("Error");
//                PrintArray(matrix);
//                break;
            }
//            System.out.println("__________________________________");
//            Support.PrintArray(matrix);
            for (ImageView el : ships) {
                random_set_ship_image(el, matrix);
            }
        }
    }

    protected static class Default {
        protected static int default_ship_x(String id_ship) {
            switch (id_ship) {
                case ("Ship1_1"), ("Ship2_1"), ("Ship3_1"), ("Ship4_1") -> {
                    return 769;
                }
                case ("Ship1_2") -> {
                    return 879;
                }
                case ("Ship1_3") -> {
                    return 994;
                }
                case ("Ship1_4") -> {
                    return 1104;
                }
                case ("Ship2_2") -> {
                    return 925;
                }
                case ("Ship2_3") -> {
                    return 1095;
                }
                case ("Ship3_2") -> {
                    return 980;
                }
            }
            return 0;
        }

        protected static int default_ship_y(String id_ship) {
            switch (id_ship) {
                case ("Ship1_1"), ("Ship1_2"), ("Ship1_3"), ("Ship1_4") -> {
                    return 546;
                }
                case ("Ship2_1"), ("Ship2_2"), ("Ship2_3") -> {
                    return 449;
                }
                case ("Ship3_1"), ("Ship3_2") -> {
                    return 353;
                }
                case ("Ship4_1") -> {
                    return 257;
                }
            }
            return 0;
        }
    }

    protected static class ShotOnShip {
        protected static void shot(int[][] matrix) {
            String res;
            int i = 0;
            int x = in.nextInt();
            int y = in.nextInt();
            int value_shot;
            while ((Checks.ships_around(x, y, matrix) || matrix[x][y] == 1 || !shot_target(x, y, matrix).equals("not")) && !shot_target(x, y, matrix).equals("Empty")) {
                if (shot_target(x, y, matrix).equals("Hit")) {
                    res = shot_result(x, y, matrix);
                    System.out.println(res);
                } else {
                    System.out.println("not");
                }
                Support.PrintArray(matrix);
                x = in.nextInt();
                y = in.nextInt();
            }
            if (shot_target(x, y, matrix).equals("Empty")) {
                System.out.println("miss");
                matrix[x][y] = 9;
            }
            //score_player2 = i;
        }

        private static String shot_target(int x, int y, int[][] matrix) {
            int result = matrix[x][y];
            if (result == 0 || result == 8) {
                return "Empty";
            } else {
                if (result == 1 || result == 2 || result == 3 || result == 4) {
                    return "Hit";
                }
            }
            return "not";
        }

        private static String shot_result(int x, int y, int[][] matrix) {
            System.out.println(matrix[x][y]);
            if (matrix[x][y] == 1) {
                matrix[x][y] = -2;
                Support.around(x, y, matrix, 8, 9);
                System.out.println(search_score_ship(x, y, matrix, 1));
                return "Kill";
            } else {
                int score_ship = matrix[x][y];
                matrix[x][y] = -1;
                search_ship_new(x, y, matrix, score_ship);
                return "Hit";
            }
        }

        private static int search_score_ship(int x, int y, int[][] matrix, int res) {
            if (Checks.chek_ship(x + 1, y, matrix, -1)) {
                matrix[x + 1][y] = -2;
                Support.around(x + 1, y, matrix, 8, 9);
                res += search_score_ship(x + 1, y, matrix, res);
            }
            if (Checks.chek_ship(x - 1, y, matrix, -1)) {
                matrix[x - 1][y] = -2;
                Support.around(x - 1, y, matrix, 8, 9);
                res += search_score_ship(x - 1, y, matrix, res);
            }
            if (Checks.chek_ship(x, y + 1, matrix, -1)) {
                matrix[x][y + 1] = -2;
                Support.around(x, y + 1, matrix, 8, 9);
                res += search_score_ship(x, y + 1, matrix, res);
            }
            if (Checks.chek_ship(x, y - 1, matrix, -1)) {
                matrix[x][y - 1] = -2;
                Support.around(x, y - 1, matrix, 8, 9);
                res += search_score_ship(x, y - 1, matrix, res);
            }
            return res;
        }

        private static void search_ship_new(int x, int y, int[][] matrix, int ship_power) {
            ArrayDeque<Integer> parts_of_ship = new ArrayDeque<Integer>();
            int res = 1;
            int i = 0;
            while (res != ship_power) {
                parts_of_ship.add(x + y);
                System.out.print(res);
                i += 1;
                System.out.print(x);
                System.out.println(y);
                if (Checks.chek_ship_for_refresh(x + 1, y, matrix, ship_power) || Checks.chek_ship_for_refresh(x - 1, y, matrix, ship_power)) {
                    if (Checks.chek_ship_for_refresh(x + 1, y, matrix, ship_power) && !(parts_of_ship.contains(x + 1 + y))) {
                        if (matrix[x + 1][y] == ship_power) {
                            matrix[x + 1][y] = ship_power - 1;
                            res += 1;
                        }
                        x = x + 1;
                    } else {

                        if (matrix[x - 1][y] == ship_power && !(parts_of_ship.contains(x - 1 + y))) {
                            matrix[x - 1][y] = ship_power - 1;
                            res += 1;
                        }

                        x = x - 1;
                    }
                } else {
                    if (Checks.chek_ship_for_refresh(x, y + 1, matrix, ship_power) && !(parts_of_ship.contains(x + y + 1))) {
                        if (matrix[x][y + 1] == ship_power) {
                            matrix[x][y + 1] = ship_power - 1;
                            res += 1;
                        }
                        y = y + 1;

                    } else {
                        if (matrix[x][y - 1] == ship_power && !(parts_of_ship.contains(x + y - 1))) {
                            matrix[x][y - 1] = ship_power - 1;
                            res += 1;
                        }
                        y = y - 1;
                    }
                }
                if (i == 10) {
                    break;
                }
            }

            //return res;
        }

        private static void search_ship(int x, int y, int[][] matrix, int ship_power) {
            if (Checks.chek_ship(x + 1, y, matrix, ship_power)) {
                matrix[x + 1][y] = ship_power - 1;
                search_ship(x + 1, y, matrix, ship_power);
            }
            if (Checks.chek_ship(x - 1, y, matrix, ship_power)) {
                matrix[x - 1][y] = ship_power - 1;
                search_ship(x - 1, y, matrix, ship_power);
            }
            if (Checks.chek_ship(x, y + 1, matrix, ship_power)) {
                matrix[x][y + 1] = ship_power - 1;
                search_ship(x, y + 1, matrix, ship_power);
            }
            if (Checks.chek_ship(x, y - 1, matrix, ship_power)) {
                matrix[x][y - 1] = ship_power - 1;
                search_ship(x, y - 1, matrix, ship_power);
            }
            //return "end";
        }
    }

    protected static class WorkFile {
        protected static void create_file(File file) {
            try {
                if (file.createNewFile()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write("Volume: 100.0 \n");
                    writer.write("Theme: light \n");
                    writer.write("Volume: true ");
                    writer.close();
                } else {
                    System.out.println("Файл не создан. Что-то пошло не так.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        protected static void read_file(File file) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                Scanner scan = new Scanner(reader);
                sliderValue = Double.parseDouble(scan.nextLine().substring(8));
                theme_color = scan.nextLine().substring(7);
                if (Objects.equals(theme_color, "dark ")) {
                    theme_color = "dark";
                    rect1Color = "-fx-background-color: linear-gradient(#66007A, #5C5C5C)";
                    rect2Color = "-fx-background-color: linear-gradient(#6A300A, #001AFF00)";

                } else {
                    theme_color = "light";
                    rect1Color = "-fx-background-color: linear-gradient(#1E6BFF, #2871FFAB, #6197FF5C)";;
                    rect2Color = "-fx-background-color: linear-gradient(#001AFF5E, #001AFF00)";;
                }
                full_screan = Boolean.parseBoolean(scan.nextLine().substring(12));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        protected static void write_file(File file) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Volume: " + sliderValue + " \n");
                writer.write("Theme: " + theme_color + " \n");
                writer.write("FullScrean: " + full_screan);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected static class Threads {
        protected static class Music implements Runnable {
            public void run() {
                try {
                    while (status_work_music) {
                        mediaPlayer.play();
                        while (!(mediaPlayer.getCurrentTime().equals(mediaPlayer.getTotalDuration()))) {
                            Thread.sleep(1000);
                            if (flag) {
                                flag = false;
                                break;
                            }
                        }
                        mediaPlayer.seek(Duration.ZERO);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
//00:00
        protected static class Time_game implements Runnable {
            public void run() {
                int seconds = 0;
                int minutes = 0;
                int hours = 0;
                String result;
                try {
                    while (status_work_time_game) {
                        seconds += 1;
                        result = "";
                        if (seconds == 60) {
                            minutes += 1;
                            seconds = 0;
                        }
                        if (minutes == 60) {
                            hours += 1;
                            minutes = 0;
                        }
                        if (hours > 0 && hours < 10) {
                            result += ("0" + hours + ":");
                        }
                        if (hours > 9) {
                            result += (hours + ":");
                        }
                        if (minutes < 10) {
                            result += ("0" + minutes + ":");
                        } else {
                            result += (minutes + ":");
                        }
                        if (seconds < 10) {
                            result += ("0" + seconds);
                        } else {
                            result += (seconds);
                        }
                        String finalResult = result;
                        Platform.runLater(() -> game_time.setText(finalResult));
//                        System.out.println(result);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    protected static void changeScene(FXMLLoader loader, Stage stage) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(full_screan);
        stage.show();
    }
    protected static void set_label_time(Label label){
        game_time = label;
        status_work_time_game = true;
    }
    protected static void stop_tread_time(Label label){
        status_work_time_game = false;
    }
    protected static void start_tread_music(){
        status_work_music = true;
    }
    protected static void stop_tread_music(){
        status_work_music = false;
    }
    protected static void shoot_enemy(MouseEvent event, int[][] player, int number_player, String id, Polygon polyg, String name_player, Paint color1, Paint color2, AnchorPane anchorPane, Button btn1, int char_i, int char_j, List<Double> coordinates) {
        Rectangle clickedImageView = (Rectangle) event.getSource();
        int i = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(char_i)));
//        System.out.println(i);
        int j = Integer.parseInt(String.valueOf(clickedImageView.getId().charAt(char_j)));
//        System.out.println(j);
        Paint polygColor = polyg.getFill();
        if (polygColor == color1 && clickedImageView.getFill() != Color.BLACK && clickedImageView.getFill() != Color.GRAY && clickedImageView.getFill() != Color.BLUE) {
            //System.out.println(clickedImageView.getFill());
            //System.out.println(111111);
            if (player[i][j] == 0 || player[i][j] == 8) {
                clickedImageView.setFill(Color.BLUE);
                polyg.getPoints().setAll(coordinates);
                polyg.setFill(color2);
            } else {
                switch (number_player){
                    case (1)->{
                        score_player1 += 1;
                        score_player = score_player1;
                    }
                    case (2)->{
                        score_player2 += 1;
                        score_player = score_player2;
                    }
                }
//                score_player += 1;
                System.out.println("score_player: " + score_player);
                clickedImageView.setFill(Color.GRAY);
                player[i][j] = -1;

                int flag_left = 0;
                int flag_right = 0;
                int flag_top = 0;
                int flag_down = 0;
                //System.out.println("length" + player[0].length);

                while (player[i][j] == -1) {
                    System.out.println("1111" + player[i][j]);
                    i -= 1;
                    if (i == player[0].length || i == -1 || player[i][j] == 8) {
                        flag_left = 1;
                        //System.out.println("efwegfewg");
                        break;
                    }
                }
                i += 1;
                int we = player[1].length - 1;
                //System.out.println("ewfewfewf " + we);

                while (player[i][j] == -1) {
                    System.out.println("2222" + player[i][j]);
                    i += 1;
                    if (i == player[0].length || i == -1 || player[i][j] == 8) {
                        flag_right = 1;
                        break;
                    }
                }
                i -= 1;

                while (player[i][j] == -1) {
                    //System.out.println("3333" + player[i][j]);
                    j -= 1;
                    if (j == player[1].length || j == -1 || player[i][j] == 8) {
                        flag_down = 1;
                        break;
                    }
                }
                j += 1;
                //System.out.println("3333" + player[i][j]);

                while (player[i][j] == -1) {
                    //System.out.println("4444" + player[i][j]);
                    j += 1;
                    if (j == player[1].length || j == -1 || player[i][j] == 8) {
                        flag_top = 1;
                        break;
                    }
                }
                j -= 1;
                //System.out.println("3333" + player[i][j]);

//                System.out.println("left " + flag_left);
//                System.out.println("right " + flag_right);
//                System.out.println("top " + flag_top);
//                System.out.println("down " + flag_down);

                if (flag_down == 1 && flag_left == 1 && flag_top == 1 && flag_right == 1) {
                    PrintArray(player);
//                    Rectangle rectangle = null;
//                    Scene scene = null;
                    while (player[i][j] == -1 || player[i][j] == -2) {
                        String rect_id = id + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player[0].length - 1 && player[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player[1].length - 1 && player[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }

                        if (player[i][j] == -1){
                            player[i][j] = -2;
                        }
                        i -= 1;
                        if (i == player[0].length || i == -1 || player[i][j] == 8) {
                            break;
                        }
                    }
                    if (i != -1 && j != player[1].length - 1 && player[i][j+1] == 8) {
                        int j8 = j+1;
                        String rect_id2 = id + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (i != -1 && j != 0 && player[i][j-1] == 8) {
                        int j8 = j-1;
                        String rect_id2 = id + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }

                    i += 1;
                    //System.out.println("wefwe" + player[i][j]);
                    while (player[i][j] == -1 || player[i][j] == -2) {
                        String rect_id = id + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player[0].length - 1 && player[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player[1].length - 1 && player[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player[i][j] == -1){
                            player[i][j] = -2;
                        }
                        i += 1;
                        if (i == player[0].length || i == -1 || player[i][j] == 8) {
                            break;
                        }
                    }
                    if (i != player[0].length && j != player[1].length - 1 && player[i][j+1] == 8) {
                        int j8 = j+1;
                        String rect_id2 = id + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (i != player[0].length && j != 0 && player[i][j-1] == 8) {
                        int j8 = j-1;
                        String rect_id2 = id + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    i -= 1;
                    //System.out.println("wefwe" + player[i][j]);
                    while (player[i][j] == -1 || player[i][j] == -2) {
                        String rect_id = id + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player[0].length - 1 && player[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player[1].length - 1 && player[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player[i][j] == -1){
                            player[i][j] = -2;
                        }
                        j += 1;
                        if (j == player[1].length || j == -1 || player[i][j] == 8) {
                            break;
                        }
                    }
                    if (j != player[1].length && i != player[0].length - 1 && player[i+1][j] == 8) {
                        int i8 = i+1;
                        String rect_id2 = id + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (j != player[1].length && i != 0 && player[i-1][j] == 8) {
                        int i8 = i-1;
                        String rect_id2 = id + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    j -= 1;
                    //System.out.println("wefwe" + player[i][j]);
                    while (player[i][j] == -1 || player[i][j] == -2) {
                        String rect_id = id + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player[i-1][j] == 8) {
                            int i8 = i-1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player[i][j-1] == 8) {
                            int j8 = j-1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player[0].length - 1 && player[i+1][j] == 8) {
                            int i8 = i+1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player[1].length - 1 && player[i][j+1] == 8) {
                            int j8 = j+1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player[i][j] == -1) {
                            player[i][j] = -2;
                        }
                        j -= 1;
                        //System.out.println("wefwe" + rect_id);
                        if (j == player[1].length || j == -1 || player[i][j] == 8) {
                            break;
                        }
                    }
                    if (j != -1 && i != player[0].length - 1 && player[i+1][j] == 8) {
                        int i8 = i+1;
                        String rect_id2 = id + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (j != -1 && i != 0 && player[i-1][j] == 8) {
                        int i8 = i-1;
                        String rect_id2 = id + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    j += 1;
                    PrintArray(player);

//                    System.out.println("i: " + i);
//                    System.out.println("j: " + j);
//                    while (i != player[0].length && i != -1 && (player[i][j] == 8 || player[i][j] == -2) && player[i][j] == -2){
//                        i -= 1;
//                        //System.out.println("чему Ш  " + i);
//                    }
//                    i += 1;
//                    while (j != player[1].length && j != -1 && (player[i][j] == 8 || player[i][j] == -2) && (player[i-1][j] == -2 || player[i][j-1] == -2 || player[i+1][j] == -2 || player[j+1][j] == -2)){
//                        j -= 1;
//                    }
//                    j += 1;
//
//                    System.out.println("i = " + i);
//                    System.out.println("j = " + j);
//
//                    while (j != player[1].length && j != -1 && player[i][j] == 8 && ((i + 1 != 10 && j + 1 != 10 && player[i+1][j+1] == -2) || (i + 1 != 10 && player[i+1][j] == -2))) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j += 1;
////                        System.out.println("i = " + i);
////                        System.out.println("j = " + j);
//                    }
//
////                    System.out.println("i: " + i);
////                    System.out.println("j: " + j);
//                    while (j != player[1].length && j != -1 && (player[i][j] == 8 || player[i][j] == -2)){
//                        j += 1;
//                    }
//                    j -= 1;
//
//
//                    while (i != player[0].length && i != -1 && player[i][j] == 8 && ((i + 1 != 10 && j - 1 != -1 && player[i+1][j-1] == -2) || (j - 1 != -1 && player[i][j-1] == -2))) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i += 1;
//                    }
//                    while (i != player[0].length && i != -1 && (player[i][j] == 8 || player[i][j] == -2)){
//                        i += 1;
//                    }
//                    i -= 1;
//
//                    while (j != player[1].length && j != -1 && player[i][j] == 8 && ((i - 1 != -1 && j - 1 != -1 && player[i-1][j-1] == -2) || (i - 1 != -1 && player[i-1][j] == -2))) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j -= 1;
//                    }
//                    while (j != player[1].length && j != -1 && (player[i][j] == 8 || player[i][j] == -2)){
//                        j -= 1;
//                    }
//                    j += 1;
//
//                    while (i != player[0].length && i != -1 && player[i][j] == 8 && ((j + 1 != 10 && i - 1 != -1 && player[i-1][j+1] == -2)  || (j + 1 != 10 && player[i][j+1] == -2))) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i -= 1;
//                    }
//                    while (i != player[1].length && i != -1 && (player[i][j] == 8 || player[i][j] == -2)){
//                        i -= 1;
//                    }
//                    i += 1;

//                    while (player[i][j] == 8 && player[i - 1][j] == -1) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i -= 1;
//                    }
//                    while (player[i][j] == 8 && player[i][j + 1] == -1) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j += 1;
//                    }
//                    while (player[i][j] == 8 && player[i - 1][j] == -1) {
//                        i -= 1;
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                    }
//                    while (player[i][j] == 8 && player[i][j - 1] == -1) {
//                        j -= 1;
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                    }
                }
                if (score_player == 20) {
                    score_player1 = 0;
                    score_player2 = 0;
                    Logic.victory_window(name_player, btn1);
                }
                //PrintArray(player);
            }
            //polyg.getPoints().removeAll();
        }
    }
    protected static void victory_window(String name_player, Button btn1) {
        Logic.stop_tread_time(game_time);
        Stage modal_window = new Stage();
        Stage frame_3 = (Stage) btn1.getScene().getWindow();

//        Pane pane = new Pane();

        Group root = new Group();
        Scene scene = new Scene(root, 600,150);

        Label label = new Label(game_time.getText() +"\n" + "Игрок " + name_player + " выиграл");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size:40;-fx-font-size:40");

        Button btn_ok = new Button("Да");
        btn_ok.setLayoutX(300);
        btn_ok.setLayoutY(60);


        btn_ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.dispose();
                mediaPlayer = new MediaPlayer(new Media(new File(songs[0]).toURI().toString()));
                mediaPlayer.setVolume(sliderValue / 100);
                flag = true;
                player = 1;
                clear_matrix(player1);
                //.out.println("player 1-------------------------");
                PrintArray(player1);

                clear_matrix(player2);
                //System.out.println("player 2-------------------------");
                PrintArray(player2);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("frame_1.fxml"));
//            frame_2Controller controller = loader.getController();
//            controller.setLastButtonPressed("bot");
//        scene.setCursor(Cursor.HAND);
                Stage frame_1 = new Stage();
//                Scene scene = new Scene(pane);
                modal_window.close();
                frame_3.close();
                try {
                    Logic.changeScene(loader, frame_1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        root.getChildren().add(label);
        root.getChildren().add(btn_ok);

        modal_window.setScene(scene);
        modal_window.initModality(Modality.WINDOW_MODAL);
        modal_window.initOwner(frame_3);
//        window.initOwner(parentStage);
        modal_window.showAndWait();
    }

    protected static void shoot_bot(int[][] player, String id, Polygon polyg, String name_player, AnchorPane anchorPane, Button btn1, String lastButtonPressed) {
        Rectangle clickedImageView;
        int i;
//        System.out.println(i);
        int j;
//        System.out.println(j);
        Paint polygColor;
//        System.out.println(polygColor);

        polygColor = polyg.getFill();

        if (polygColor == Color.RED && Objects.equals(lastButtonPressed, "bot")) {
            //int k = 0;
//            while (k < 1000000) {
//                System.out.println(k);
//                k += 1;
//            }
            i = ThreadLocalRandom.current().nextInt(0, 10);
            j = ThreadLocalRandom.current().nextInt(0, 10);
            String rect_id3 = id + i + j;
            clickedImageView = (Rectangle) anchorPane.lookup(rect_id3);
            while (clickedImageView.getFill() == Color.BLACK || clickedImageView.getFill() == Color.GRAY || clickedImageView.getFill() == Color.BLUE) {
                i = ThreadLocalRandom.current().nextInt(0, 10);
                j = ThreadLocalRandom.current().nextInt(0, 10);
                rect_id3 = id + i + j;
                clickedImageView = (Rectangle) anchorPane.lookup(rect_id3);
            }

            while (player[i][j] != 0 && player[i][j] != 8 && score_player1 != 20) {
                score_player1 += 1;
                score_player = score_player1;
                //System.out.println("score_player " + score_player);
                clickedImageView.setFill(Color.GRAY);
                player[i][j] = -1;

                int flag_left = 0;
                int flag_right = 0;
                int flag_top = 0;
                int flag_down = 0;
                //System.out.println("length" + player[0].length);

                while (player[i][j] == -1) {
                    System.out.println("1111" + player[i][j]);
                    i -= 1;
                    if (i == player[0].length || i == -1 || player[i][j] == 8) {
                        flag_left = 1;
                        //System.out.println("efwegfewg");
                        break;
                    }
                }
                i += 1;
                int we = player[1].length - 1;
                //System.out.println("ewfewfewf " + we);

                while (player[i][j] == -1) {
//                    System.out.println("2222" + player[i][j]);
                    i += 1;
                    if (i == player[0].length || i == -1 || player[i][j] == 8) {
                        flag_right = 1;
                        break;
                    }
                }
                i -= 1;

                while (player[i][j] == -1) {
                    //System.out.println("3333" + player[i][j]);
                    j -= 1;
                    if (j == player[1].length || j == -1 || player[i][j] == 8) {
                        flag_down = 1;
                        break;
                    }
                }
                j += 1;
                //System.out.println("3333" + player[i][j]);

                while (player[i][j] == -1) {
                    //System.out.println("4444" + player[i][j]);
                    j += 1;
                    if (j == player[1].length || j == -1 || player[i][j] == 8) {
                        flag_top = 1;
                        break;
                    }
                }
                j -= 1;
                //System.out.println("3333" + player[i][j]);

//                System.out.println("left " + flag_left);
//                System.out.println("right " + flag_right);
//                System.out.println("top " + flag_top);
//                System.out.println("down " + flag_down);

                if (flag_down == 1 && flag_left == 1 && flag_top == 1 && flag_right == 1) {
                    PrintArray(player);
                    Rectangle rectangle = null;
                    Scene scene = null;
                    while (player[i][j] == -1 || player[i][j] == -2) {
                        String rect_id = id + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player[i - 1][j] == 8) {
                            int i8 = i - 1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player[i][j - 1] == 8) {
                            int j8 = j - 1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player[0].length - 1 && player[i + 1][j] == 8) {
                            int i8 = i + 1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player[1].length - 1 && player[i][j + 1] == 8) {
                            int j8 = j + 1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }

                        if (player[i][j] == -1) {
                            player[i][j] = -2;
                        }
                        i -= 1;
                        if (i == player[0].length || i == -1 || player[i][j] == 8) {
                            break;
                        }
                    }
                    if (i != -1 && j != player[1].length - 1 && player[i][j + 1] == 8) {
                        int j8 = j + 1;
                        String rect_id2 = id + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (i != -1 && j != 0 && player[i][j - 1] == 8) {
                        int j8 = j - 1;
                        String rect_id2 = id + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }

                    i += 1;
                    //System.out.println("wefwe" + player[i][j]);
                    while (player[i][j] == -1 || player[i][j] == -2) {
                        String rect_id = id + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player[i - 1][j] == 8) {
                            int i8 = i - 1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player[i][j - 1] == 8) {
                            int j8 = j - 1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player[0].length - 1 && player[i + 1][j] == 8) {
                            int i8 = i + 1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player[1].length - 1 && player[i][j + 1] == 8) {
                            int j8 = j + 1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player[i][j] == -1) {
                            player[i][j] = -2;
                        }
                        i += 1;
                        if (i == player[0].length || i == -1 || player[i][j] == 8) {
                            break;
                        }
                    }
                    if (i != player[0].length && j != player[1].length - 1 && player[i][j + 1] == 8) {
                        int j8 = j + 1;
                        String rect_id2 = id + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (i != player[0].length && j != 0 && player[i][j - 1] == 8) {
                        int j8 = j - 1;
                        String rect_id2 = id + i + j8;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    i -= 1;
                    //System.out.println("wefwe" + player[i][j]);
                    while (player[i][j] == -1 || player[i][j] == -2) {
                        String rect_id = id + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player[i - 1][j] == 8) {
                            int i8 = i - 1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player[i][j - 1] == 8) {
                            int j8 = j - 1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player[0].length - 1 && player[i + 1][j] == 8) {
                            int i8 = i + 1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player[1].length - 1 && player[i][j + 1] == 8) {
                            int j8 = j + 1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player[i][j] == -1) {
                            player[i][j] = -2;
                        }
                        j += 1;
                        if (j == player[1].length || j == -1 || player[i][j] == 8) {
                            break;
                        }
                    }
                    if (j != player[1].length && i != player[0].length - 1 && player[i + 1][j] == 8) {
                        int i8 = i + 1;
                        String rect_id2 = id + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (j != player[1].length && i != 0 && player[i - 1][j] == 8) {
                        int i8 = i - 1;
                        String rect_id2 = id + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    j -= 1;
                    //System.out.println("wefwe" + player[i][j]);
                    while (player[i][j] == -1 || player[i][j] == -2) {
                        String rect_id = id + i + j;
                        //System.out.println(rect_id);
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
                        clickedImageView.setFill(Color.BLACK);
                        if (i != 0 && player[i - 1][j] == 8) {
                            int i8 = i - 1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != 0 && player[i][j - 1] == 8) {
                            int j8 = j - 1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (i != player[0].length - 1 && player[i + 1][j] == 8) {
                            int i8 = i + 1;
                            String rect_id2 = id + i8 + j;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (j != player[1].length - 1 && player[i][j + 1] == 8) {
                            int j8 = j + 1;
                            String rect_id2 = id + i + j8;
                            clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                            clickedImageView.setFill(Color.BLUE);
                        }
                        if (player[i][j] == -1) {
                            player[i][j] = -2;
                        }
                        j -= 1;
                        //System.out.println("wefwe" + rect_id);
                        if (j == player[1].length || j == -1 || player[i][j] == 8) {
                            break;
                        }
                    }
                    if (j != -1 && i != player[0].length - 1 && player[i + 1][j] == 8) {
                        int i8 = i + 1;
                        String rect_id2 = id + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    if (j != -1 && i != 0 && player[i - 1][j] == 8) {
                        int i8 = i - 1;
                        String rect_id2 = id + i8 + j;
                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id2);
                        clickedImageView.setFill(Color.BLUE);
                    }
                    j += 1;
                    PrintArray(player);

//                    System.out.println("i: " + i);
//                    System.out.println("j: " + j);
//                    while (i != player[0].length && i != -1 && (player[i][j] == 8 || player[i][j] == -2) && player[i][j] == -2){
//                        i -= 1;
//                        //System.out.println("чему Ш  " + i);
//                    }
//                    i += 1;
//                    while (j != player[1].length && j != -1 && (player[i][j] == 8 || player[i][j] == -2) && (player[i-1][j] == -2 || player[i][j-1] == -2 || player[i+1][j] == -2 || player[j+1][j] == -2)){
//                        j -= 1;
//                    }
//                    j += 1;
//
//                    System.out.println("i = " + i);
//                    System.out.println("j = " + j);
//
//                    while (j != player[1].length && j != -1 && player[i][j] == 8 && ((i + 1 != 10 && j + 1 != 10 && player[i+1][j+1] == -2) || (i + 1 != 10 && player[i+1][j] == -2))) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j += 1;
////                        System.out.println("i = " + i);
////                        System.out.println("j = " + j);
//                    }
//
////                    System.out.println("i: " + i);
////                    System.out.println("j: " + j);
//                    while (j != player[1].length && j != -1 && (player[i][j] == 8 || player[i][j] == -2)){
//                        j += 1;
//                    }
//                    j -= 1;
//
//
//                    while (i != player[0].length && i != -1 && player[i][j] == 8 && ((i + 1 != 10 && j - 1 != -1 && player[i+1][j-1] == -2) || (j - 1 != -1 && player[i][j-1] == -2))) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i += 1;
//                    }
//                    while (i != player[0].length && i != -1 && (player[i][j] == 8 || player[i][j] == -2)){
//                        i += 1;
//                    }
//                    i -= 1;
//
//                    while (j != player[1].length && j != -1 && player[i][j] == 8 && ((i - 1 != -1 && j - 1 != -1 && player[i-1][j-1] == -2) || (i - 1 != -1 && player[i-1][j] == -2))) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j -= 1;
//                    }
//                    while (j != player[1].length && j != -1 && (player[i][j] == 8 || player[i][j] == -2)){
//                        j -= 1;
//                    }
//                    j += 1;
//
//                    while (i != player[0].length && i != -1 && player[i][j] == 8 && ((j + 1 != 10 && i - 1 != -1 && player[i-1][j+1] == -2)  || (j + 1 != 10 && player[i][j+1] == -2))) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i -= 1;
//                    }
//                    while (i != player[1].length && i != -1 && (player[i][j] == 8 || player[i][j] == -2)){
//                        i -= 1;
//                    }
//                    i += 1;

//                    while (player[i][j] == 8 && player[i - 1][j] == -1) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        i -= 1;
//                    }
//                    while (player[i][j] == 8 && player[i][j + 1] == -1) {
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                        j += 1;
//                    }
//                    while (player[i][j] == 8 && player[i - 1][j] == -1) {
//                        i -= 1;
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                    }
//                    while (player[i][j] == 8 && player[i][j - 1] == -1) {
//                        j -= 1;
//                        String rect_id = id + i + j;
//                        clickedImageView = (Rectangle) anchorPane.lookup(rect_id);
//                        clickedImageView.setFill(Color.BLUE);
//                    }
                    if (score_player == 20) {
                        score_player1 = 0;
                        score_player2 = 0;
                        Logic.victory_window(name_player, btn1);
                        break;
                    }
                }
                i = ThreadLocalRandom.current().nextInt(0, 10);
                j = ThreadLocalRandom.current().nextInt(0, 10);
                rect_id3 = id + i + j;
                clickedImageView = (Rectangle) anchorPane.lookup(rect_id3);
                while (clickedImageView.getFill() == Color.BLACK || clickedImageView.getFill() == Color.GRAY || clickedImageView.getFill() == Color.BLUE) {
                    i = ThreadLocalRandom.current().nextInt(0, 10);
                    j = ThreadLocalRandom.current().nextInt(0, 10);
                    rect_id3 = id + i + j;
                    clickedImageView = (Rectangle) anchorPane.lookup(rect_id3);
                }
            }
            if (player[i][j] == 0 || player[i][j] == 8) {
                clickedImageView.setFill(Color.BLUE);
                polyg.getPoints().setAll(0.0, 0.0,
                        0.0, 140.0,
                        50.0, 70.0);
                polyg.setFill(Color.GREEN);
            }
        }
    }
    protected static void frame_2_open(ActionEvent event, String btn) throws IOException {
        FXMLLoader loader = new FXMLLoader(Logic.class.getResource("frame_2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Logic.changeScene(loader, stage);
        frame_2Controller controller = loader.getController();
        controller.setLastButtonPressed(btn);
    }


//    protected static void changeColor(FXMLLoader loader, Stage stage) throws IOException {
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//    }
}