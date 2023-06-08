package com.example.demo;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static com.example.demo.HelloApplication.*;


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
    static int score = 0;
    static int first_x = 0;
    static int first_y = 0;

//    static int[] ship_images = new int[20];
    static ArrayDeque<Integer> ship_images = new ArrayDeque<Integer>();
    static Random random = new Random();
    static Scanner in = new Scanner(System.in);

    protected static int count_ships(int[][] matrix) {
        int res = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (matrix[i][j]){
                    case(1), (2), (3), (4) ->{
                        res += 1;
                    }
                }
            }
        }
        return res;
    }
    protected static void PrintArray(int[][] Array) {
        for (int i = 0; i < 10; i++) {  //идём по строкам
            for (int j = 0; j < 10; j++) { //идём по столбцам
                System.out.print(" " + Array[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }

    protected static int index(int[] Array, int search_el){
        int in = 0;
        for(int el: Array){
            if(el == search_el){
                break;
            }
            in += 1;
        }
        return in;
    }
    protected static int index_turn_x(int[] Array, int search_el, int id){
        int in = 0;
        for(int el: Array){
            if(el == search_el){
                break;
            }
            in += 1;
        }
        in = in / 2;
        if (id == 2){in -= 1;}
        return in ;
    }
    protected static int index_turn_y(int[] Array, int search_el, int id){
        int in = 0;
        for(int el: Array){
            if(el == search_el){
                break;
            }
            in += 1;
        }
        in = in / 2;
        if (id == 4){in -= 1;}
        return in ;
    }

    protected static int index_turn_x_random(int search_index, int id){
        if (id == 2){
            search_index += 1;
        }

        search_index = search_index * 2;

        if (id == 3){
            search_index += 1;
        }

        return search_index ;
    }
    protected static int index_turn_y_random(int search_index, int id){
        if (id == 4){
            search_index += 1;
        }
        search_index = search_index * 2;

        if (id == 3){
            search_index += 1;
        }
        return search_index ;
    }

    public static boolean set_ship_on_matrix(int y, int x, int power_ship, int rotate, int[][] matrix){
        boolean res;
        try {
            switch (rotate){
                case (0) ->{
                    for(int i = 0; i < power_ship; i++){
                        matrix[y][x + i] = power_ship;
                        around(y, x + i, matrix, 0, 8);
                    }
                }
                case (90) ->{
                    for(int i = 0; i < power_ship; i++){
                        matrix[y + i][x] = power_ship;
                        around(y + i, x, matrix, 0, 8);
                    }
                }
            }
            res = true;
        }catch (Exception e){
            res = false;
        }
        return res;
    }
    public static void clear_ship(int y, int x, int rotate, int[][] matrix){
        int power_ship = matrix[y][x];
        try {
            switch (rotate){
                case (0) ->{
                    for(int i = 0; i < power_ship; i++){
                        matrix[y][x + i] = 0;
                        around(y, x + i, matrix, 8, 0);
                    }
                }
                case (90) ->{
                    for(int i = 0; i < power_ship; i++){
                        matrix[y + i][x] = 0;
                        around(y + i, x, matrix, 8, 0);
                    }
                }
            }
        }catch (Exception e){

        }
    }
    public static void clear(int[] mas_x, int[] mas_y, int rotate, ImageView img, int[][] matrix){
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        int id = id_ship(img.getId().substring(0, 5));
        try{
            switch (rotate) {
                case (0) -> {
                    clear_ship(index(mas_y, y), index(mas_x, x), rotate,matrix);
                }
                case (90) -> {
                    clear_ship(index_turn_y(mas_y, y, id), index_turn_x(mas_x, x, id), rotate, matrix);
                }
            }
            update_matrix(matrix);
        }
        catch (Exception e){
            System.out.println("ERROR");
        }
    }
    protected static void update_matrix(int[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (matrix[i][j]){
                    case(1), (2), (3), (4) -> {
                        around(i, j, matrix, 0, 8);
                    }
                }
            }
        }
    }
    public static boolean chek_ship_and_around(int y, int x, int power_ship, int rotate, int[][] matrix){
        boolean res = true;
        switch (rotate) {
            case (0) ->{
                for (int i = 0; i < power_ship; i++) {
                    switch (matrix[y][x + i]) {
                        case (1), (4), (3), (2), (8) -> {
                            res = false;
                        }
                    }
                }
            }
            case (90) ->{
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
    private static int rotation_for_random(int y, int x, int[][] matrix){
        int res;
        try {
            if(matrix[y + 1][x] == matrix[y][x]){
                res = 90;
            }
            else {
                res = 0;
            }
        }
        catch (Exception e){
            res = 0;
        }
        return res;
    }
    protected static void random_set_ship_image(ImageView img, int[][] matrix){
        int y = ship_images.pop();
        int x = ship_images.pop();
        switch (rotation_for_random(y, x, matrix)){
            case(0) ->{
//                System.out.print("rotation: ");
//                System.out.println(rotation_for_random(y, x, matrix));
//                System.out.print("x: ");
//                System.out.println(x);
//                System.out.print("y: ");
//                System.out.println(y);

                img.setRotate(0);
                img.setLayoutX(mas_x[x]);
                img.setLayoutY(mas_y[y]);
            }
            case(90) ->{
//                System.out.print("id: ");
//                System.out.println(img.getId());
//                System.out.print("rotation: ");
//                System.out.println(rotation_for_random(y, x, matrix));
//                System.out.print("x: ");
//                System.out.println(index_turn_x_random(x,id_ship(img.getId().substring(0, 5))));
//                System.out.print("y: ");
//                System.out.println(index_turn_y_random(y,id_ship(img.getId().substring(0, 5))));
//                System.out.print("x_new: ");
//                System.out.println(mas_x_turn[index_turn_x_random(x,id_ship(img.getId().substring(0, 5)))]);
//                System.out.print("y_new: ");
//                System.out.println(mas_y_turn[index_turn_y_random(y,id_ship(img.getId().substring(0, 5)))]);
                img.setRotate(90);
                img.setLayoutX(mas_x_turn[index_turn_x_random(x,id_ship(img.getId().substring(0, 5)))]);
                img.setLayoutY(mas_y_turn[index_turn_y_random(y,id_ship(img.getId().substring(0, 5)))]);
            }
        }

    }

    public static boolean chek_ship_and_around_for_turn(int y, int x, int power_ship, int[][] matrix){
        boolean res = true;
        for(int i = 0; i < power_ship; i++){
            switch (matrix[y + i][x]){
                case (1), (4), (3), (2), (8)->{
                    res = false;
                }
            }
        }
        return res;
    }
    private static void random_set_ship(int ship, int[][] matrix) throws Exception {
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
        while (true) {
            x1 = random.nextInt(10);
            y1 = random.nextInt(10);
            if (matrix[x1][y1] == 0 && check_root(x1, y1, ship, matrix)) {
                break;
            }
        }
        //System.out.println(x1);
        //System.out.println(y1);
        matrix[x1][y1] = ship;
        index_ship.add(x1);
        index_ship.add(y1);
        int i = 0;
        while (true) {
            move = random.nextInt(4);
            //System.out.println(move);
            if (check_for_move(x1, y1, ship, move, matrix)) {
                break;
            }
            if(i == 1000){
                System.out.println("Error");
                throw new Exception("Error");
            }
            i += 1;
        }
        //System.out.println("x " + x1);
        //System.out.println("y " + y1);
        //System.out.println("move " + move);
        int new_power_ship = power_ship;
        while (power_ship > 0) {
            //System.out.println(ship);
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
            //System.out.println(index_ship.pop());
            index_x = index_ship.pop();
            index_y = index_ship.pop();
            if ((index_x + index_y) < min_index){
                min_index = (index_x + index_y);
                 min_x = index_x;
                 min_y = index_y;
//                ship_images[index_ship_images] = index_x;
//                ship_images[index_ship_images + 1] = index_y;
//                ship_images.a
            }
            around(index_x, index_y, matrix, 0, 8);
        }
        //PrintArray(matrix);
        //System.out.println("_____________________________________________________________");
        ship_images.add(min_x);
        ship_images.add(min_y);
        score += 1;
        System.out.println(score);
    }

    public static int default_ship_x(String id_ship) {
        switch (id_ship){
            case("Ship1_1"), ("Ship2_1"), ("Ship3_1"), ("Ship4_1") ->{
                return 769;
            }
            case("Ship1_2")->{
                return 879;
            }
            case("Ship1_3")->{
                return 994;
            }
            case("Ship1_4")->{
                return 1104;
            }
            case("Ship2_2")->{
                return 925;
            }
            case("Ship2_3")->{
                return 1095;
            }
            case("Ship3_2")->{
                return 980;
            }
        }
        return 0;
    }
    public static int default_ship_y(String id_ship) {
        switch (id_ship){
            case("Ship1_1"), ("Ship1_2"), ("Ship1_3"), ("Ship1_4") ->{
                return  546;
            }
            case("Ship2_1"), ("Ship2_2"), ("Ship2_3") ->{
                return 449;
            }
            case("Ship3_1"), ("Ship3_2") ->{
                return  353;
            }
            case("Ship4_1")->{
                return  257;
            }
        }
        return 0;
    }
    public static int id_ship(String id_ship) {
        int res = 0;
        switch (id_ship){
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

    protected static void clear_matrix(int[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    protected static void generate_ships(int[][] matrix) {
        score = 0;
        int[] nymbers_ships = {4, 3, 2, 1};
        for (int type_ship = 3; type_ship >= 0; type_ship--) {
            while (nymbers_ships[type_ship] != 0) {
                try {
                    random_set_ship(type_ship + 1, matrix);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    nymbers_ships[type_ship] -= 1;
                }
            }
        }
    }

    protected static void set_turn_ship(int[] mas_x, int[] mas_y, ImageView img, int[][] matrix){
        int last_x = 0;
        int last_y = 0;
        int x = (int)img.getLayoutX();
        int y = (int)img.getLayoutY();
        int id = id_ship(img.getId().substring(0, 5));
        int t = 2;
        int index_x = 100;
        int index_y = 100;
        int lenght_mas_y = 100;
        switch (id){
            case(2) ->{
                index_x = 2;
                index_y = 0;
                lenght_mas_y = 8;
            }
            case(3) ->{
                index_x = 1;
                index_y = 1;
                lenght_mas_y = 7;
            }
            case(4) ->{
                index_x = 0;
                index_y = 2;
                lenght_mas_y = 6;
            }
        }
        for (int i = 0; i < 9; i++){
            if(Math.abs(x - mas_x[index_x]) <= Math.abs(x - mas_x[index_x+t]) ){
                last_x = mas_x[index_x];
            }
            else {
                last_x = mas_x[index_x+t];
            }
            if (last_x == mas_x[index_x]){
                break;
            }
            index_x += t;
        }
        for (int j = 0; j < lenght_mas_y; j++){
            if(Math.abs(y - mas_y[index_y]) <= Math.abs(y - mas_y[index_y+t]) ){
                last_y = mas_y[index_y];
            }
            else {
                last_y = mas_y[index_y+t];
            }
            if (last_y == mas_y[index_y]){
                break;
            }
            index_y += t;
        }
        index_y = index_turn_y(mas_y,last_y, id);
        index_x = index_turn_x(mas_x,last_x, id);
        try {
            if (chek_ship_and_around(index_y, index_x, id, 90, matrix)  && set_ship_on_matrix(index_y, index_x, id, 90, matrix)){
                img.setLayoutX(last_x);
                img.setLayoutY(last_y);
            }
            else {
                int default_x = default_ship_x(img.getId());
                int default_y = default_ship_y(img.getId());
                img.setLayoutX(default_x);
                img.setLayoutY(default_y);
                img.setRotate(0);
            }
        }catch (Exception e){
            int default_x = default_ship_x(img.getId());
            int default_y = default_ship_y(img.getId());
            img.setLayoutX(default_x);
            img.setLayoutY(default_y);
            img.setRotate(0);
        }
        //PrintArray(matrix);
    }

    protected static void set_ship(int[] mas_x, int[] mas_y, ImageView img,int[][] matrix){
        int last_x = 0;
        int last_y = 0;
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
        int id = id_ship(img.getId().substring(0, 5));
        int index_y = index(mas_y,last_y);
        int index_x = index(mas_x,last_x);
        try {
            if (chek_ship_and_around(index_y, index_x, id, 0, matrix) && set_ship_on_matrix(index_y, index_x, id, 0, matrix)){
                img.setLayoutX(last_x);
                img.setLayoutY(last_y);
            }
            else {
                int default_x = default_ship_x(img.getId());
                int default_y = default_ship_y(img.getId());
                img.setLayoutX(default_x);
                img.setLayoutY(default_y);
                img.setRotate(0);
            }
        }catch (Exception e){
            int default_x = default_ship_x(img.getId());
            int default_y = default_ship_y(img.getId());
            img.setLayoutX(default_x);
            img.setLayoutY(default_y);
            img.setRotate(0);
        }
        //PrintArray(matrix);
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
                if (matrix[x][y + i] != 0) {
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
            result = false;
        } catch (Exception e) {
            return false;
        } finally {
            return result;
        }
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
            //System.out.println("len " + len);
            //System.out.println("power " + power);
            if (check_len(len, power)) {
                result = true;
            }
        } catch (Exception e) {
            return false;
        } finally {
            return result;
        }
    }

    private static boolean check_len(int len, int power) {
        if (len == power) {
            return true;
        }
        return false;
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
        //PrintArray(player2);
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
       //ArrayDeque<Integer> parts_of_ship = new ArrayDeque<Integer>();
        System.out.println(matrix[x][y]);
        if (matrix[x][y] == 1) {
            matrix[x][y] = -2;
            around(x, y, matrix, 8, 9);
            //index_ship.add(x);
            //index_ship.add(y);
            System.out.println(search_score_ship(x, y, matrix, 1));
            return "Kill";
        } else {
            int score_ship = matrix[x][y];
            matrix[x][y] = -1;
            //index_ship.add(x);
            //index_ship.add(y);
            //search_ship(x, y, matrix, score_ship);
            //search_ship(first_x, first_y, matrix, score_ship);
            //System.out.println(-1);
            search_ship_new(x, y, matrix, score_ship);
            /**while (parts_of_ship.peek() != null) {
                matrix[parts_of_ship.pop()][parts_of_ship.pop()] = score_ship - 1;
            }**/
            return "Hit";
        }
    }

    protected static void shot(int[][] matrix) {
        String res;
        int i = 0;
        int x = in.nextInt();
        int y = in.nextInt();
        int value_shot;
            while ((ships_around(x, y, matrix) || matrix[x][y] == 1 || shot_target(x, y, matrix) != "not") && shot_target(x, y, matrix) != "Empty")  {
                if (shot_target(x, y, matrix) == "Hit"){
                    res = shot_result(x, y, matrix);
                    System.out.println(res);
                }
                else{
                    System.out.println("not");
                }
                PrintArray(matrix);
                x = in.nextInt();
                y = in.nextInt();
            }

        if (shot_target(x, y, matrix) == "Empty"){
            System.out.println("miss");
            matrix[x][y] = 9;
        }
        //score_player2 = i;
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

    private static void search_ship(int x, int y, int[][] matrix, int ship_power) {
        if (chek_ship(x + 1, y ,matrix, ship_power)) {
            matrix[x + 1][y] = ship_power -1;
            search_ship(x + 1, y ,matrix, ship_power);
        }
        if (chek_ship(x - 1, y ,matrix, ship_power)) {
            matrix[x - 1][y] = ship_power -1;
            search_ship(x - 1, y ,matrix, ship_power);
        }
        if (chek_ship(x , y + 1,matrix, ship_power)) {
            matrix[x][y + 1] = ship_power -1;
            search_ship(x, y + 1 ,matrix, ship_power);
        }
        if (chek_ship(x, y - 1 ,matrix, ship_power)) {
            matrix[x][y - 1] = ship_power -1;
            search_ship(x, y - 1 ,matrix, ship_power);
        }
        //return "end";
    }
    private static boolean chek_ship(int x, int y, int[][] matrix, int ship_power) {
        boolean res = false;
        if ((x >= 0 && x < 10) && (y >= 0 && y < 10) ) {
            if (ship_power == matrix[x][y]){res = true;}
        }
        return res;
    }
    private static int search_score_ship(int x, int y, int[][] matrix, int res) {
        if (chek_ship(x + 1, y, matrix, -1)) {
            matrix[x + 1][y] = -2;
            around(x + 1, y, matrix, 8, 9);
            res += search_score_ship(x + 1, y, matrix, res );
        }
        if (chek_ship(x - 1, y, matrix, -1)) {
            matrix[x - 1][y] = -2;
            around(x - 1, y, matrix, 8, 9);
            res += search_score_ship(x - 1, y, matrix, res);
        }
        if (chek_ship(x, y + 1, matrix, -1)) {
            matrix[x][y + 1] = -2;
            around(x, y + 1, matrix, 8, 9);
            res += search_score_ship(x, y + 1, matrix, res );
        }
        if (chek_ship(x, y - 1, matrix, -1)) {
            matrix[x][y - 1] = -2;
            around(x, y - 1, matrix, 8, 9);
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
            if (chek_ship_for_refresh(x + 1, y, matrix, ship_power) || chek_ship_for_refresh(x - 1, y, matrix, ship_power)) {
                if (chek_ship_for_refresh(x + 1, y, matrix, ship_power) && !(parts_of_ship.contains(x+1 + y))) {
                    if (matrix[x + 1][y] == ship_power ) {
                        matrix[x + 1][y] = ship_power - 1;
                        res += 1;
                    }
                    x = x + 1;
                }
                else {

                        if (matrix[x - 1][y] == ship_power && !(parts_of_ship.contains(x - 1 + y))) {
                            matrix[x - 1][y] = ship_power - 1;
                            res += 1;
                        }

                    x = x - 1;
                }
            } else {
                if (chek_ship_for_refresh(x, y + 1, matrix, ship_power) && !(parts_of_ship.contains(x + y+1))) {
                    if (matrix[x][y + 1] == ship_power ) {
                        matrix[x][y + 1] = ship_power - 1;
                        res += 1;
                    }
                    y = y + 1;

                }
                else {
                        if (matrix[x][y - 1] == ship_power && !(parts_of_ship.contains(x + y - 1))) {
                            matrix[x][y - 1] = ship_power - 1;
                            res += 1;
                        }
                        y = y - 1;
                }
            }
            if (i == 10){break;}
        }

            //return res;
    }
    private static boolean chek_ship_for_refresh(int x, int y, int[][] matrix, int ship_power) {
        boolean res = false;
        if ((x >= 0 && x < 10) && (y >= 0 && y < 10) ) {
            if (ship_power == matrix[x][y] || matrix[x][y] == -1 || matrix[x][y] == ship_power - 1){
                res = true;
            }
        }
        return res;
    }
}