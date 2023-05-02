package com.example.demo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;


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
    protected static void PrintArray(int[][] Array){
        for (int i = 0; i < 10; i++) {  //идём по строкам
            for (int j = 0; j < 10; j++) { //идём по столбцам
                System.out.print(" " + Array[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }
    static Random random = new Random();
    private static void set_ship(int ship, int[][] matrix) {
        ArrayDeque<Integer> index_ship = new ArrayDeque<Integer>();
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
        while (true) {
            move = random.nextInt(4);
            //System.out.println(move);
            if (check_for_move(x1 ,y1, ship, move, matrix)){
                break;
            }
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
            if (new_power_ship != power_ship){
                    index_ship.add(x1);
                    index_ship.add(y1);
                }
            power_ship = new_power_ship;
        }
        while (index_ship.peek() != null) {
            //System.out.println(index_ship.pop());
            around(index_ship.pop(), index_ship.pop(), matrix, 0,8);
        }
        //PrintArray(matrix);
        //System.out.println("_____________________________________________________________");
    }
    protected static void generate_ships(int[][] player) {
        int[] nymbers_ships = {4, 3, 2, 1};
        for(int type_ship = 3; type_ship >= 0; type_ship--){
            while (nymbers_ships[type_ship] != 0){
                try {
                    set_ship(type_ship + 1, player);
                }
                finally {
                    nymbers_ships[type_ship] -= 1;
                }
            }
        }
    }
    private static boolean check_root(int x , int y, int power, int[][] matrix){
        boolean result = true;
        int len = 0;
        try{
            for (int i = 1; i <= power; i++){
                if(matrix[x + i][y] != 0){
                    break;
                }
                else{
                    len += 1;
                }
            }
            if(check_len(len, power)){
                return result;
            }
            len = 0;
            for (int i = 1; i <= power; i++){
                if(matrix[x - i][y] != 0){
                    break;
                }
                else{
                    len += 1;
                }
            }
            if(check_len(len, power)){
                return result;
            }
            len = 0;
            for (int i = 1; i <= power; i++){
                if(matrix[x][y + i] != 0){
                    break;
                }
                else{
                    len += 1;
                }
            }
            if(check_len(len, power)){
                return result;
            }
            len = 0;
            for (int i = 1; i <= power; i++){
                if(matrix[x][y - i] != 0){
                    break;
                }
                else{
                    len += 1;
                }
            }
            if(check_len(len, power)){
                return result;
            }
            result = false;
        }
        catch (Exception e){
            return false;
        }
        finally {
            return result;
        }
    }
    private static boolean check_for_move(int x , int y, int power, int move_ship, int[][] matrix){
        boolean result = false;
        int len = 0;
        try{
            switch (move_ship) {
                case(0)-> {
                    for (int i = 1; i <= power; i++) {
                        if (matrix[x + i][y] != 0) {
                            break;
                        } else {
                            len += 1;
                        }
                    }
                }
                case(1)-> {
                    for (int i = 1; i <= power; i++) {
                        if (matrix[x - i][y] != 0) {
                            break;
                        } else {
                            len += 1;
                        }
                    }
                }
                case(2)-> {
                    for (int i = 1; i <= power; i++) {
                        if (matrix[x][y + i] != 0) {
                            break;
                        } else {
                            len += 1;
                        }
                    }
                }
                case(3)-> {
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
        }
        catch (Exception e){
            return false;
        }
        finally {
            return result;
        }
    }
    private static boolean check_len(int len , int power){
        if(len == power){
            return true;
        }
        return false;
    }
    private static void around(int i , int j, int[][] matrix, int target, int value){ //0, 0
        int xi = i - 1;
        for (int cx = 1; cx <= 3; cx++){
            int yj = j - 1;
            for (int cy = 1; cy <= 3; cy++){
                if ((xi >= 0 && xi < 10) && (yj >= 0 && yj < 10) && (matrix[xi][yj] == target)){
                    matrix[xi][yj] = value;
                }
                yj += 1;
            }
            xi += 1;
        }
        //PrintArray(player2);
    }
    private static String shot_target(int x, int y, int[][] matrix){
        int result = matrix[x][y];
        if(result == 0 || result == 8){
            return "Empty";
        }
        else {
            if(result == 1 || result == 2 || result == 3 || result == 4){
                return "Hit";
            }
        }
        return "not";
    }
    private static boolean shot_result(int x, int y, int[][] matrix){
        boolean result_shot = true;
        switch (shot_target(x, y, matrix)){
            case("Empty") ->{
                matrix[x][y] = 9;
                result_shot = false;
            }
            case("Hit") ->{
                matrix[x][y] = -1;
            }
        }
        return result_shot;
    }
    protected static String shot(int x, int y, int[][] matrix) {
        ArrayDeque<Integer> index_ship = new ArrayDeque<Integer>();
        String res = "miss";
        int i = 0;
        int value_shot = matrix[x][y];
        while (shot_result(x, y, matrix)) {
            System.out.println("hit");
            i += 1;
            index_ship.add(x);
            index_ship.add(y);
            if (i == value_shot) {
                while (index_ship.peek() != null) {
                    around(index_ship.pop(), index_ship.pop(), matrix, 8, 9);
                }
                res = "Kill";
                i = 0;
            }
        }
        return res;
    }
}