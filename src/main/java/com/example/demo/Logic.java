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
    private static void PrintArray(int[][] Array){
        for (int i = 0; i < 10; i++) {  //идём по строкам
            for (int j = 0; j < 10; j++) { //идём по столбцам
                System.out.print(" " + Array[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }
    }
    static Random random = new Random();
    static int[][] player1 = new int[10][10];
    static int[][] player2 = new int[10][10];
    protected static void set_ship(int ship) {
        ArrayDeque<Integer> index_ship = new ArrayDeque<Integer>();
        int power_ship = ship - 1;
        int x1;
        int y1;
        int move;
        while (true) {
            x1 = random.nextInt(10);
            y1 = random.nextInt(10);
            if (player2[x1][y1] == 0 && check_root(x1, y1, ship)) {
                break;
            }
        }
        //System.out.println(x1);
        //System.out.println(y1);
        player2[x1][y1] = ship;
        index_ship.add(x1);
        index_ship.add(y1);
        while (true) {
            move = random.nextInt(4);
            //System.out.println(move);
            if (check_for_move(x1 ,y1, ship, move)){
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
                            if (player2[x1 + 1][y1] == 0) {
                                player2[x1 + 1][y1] = ship;
                                generate_stuff(x1, y1);
                                x1 = x1 + 1;
                                new_power_ship -= 1;
                            }
                        }
                        case (1) -> {
                            if (player2[x1 - 1][y1] == 0) {
                                player2[x1 - 1][y1] = ship;
                                x1 = x1 - 1;
                                new_power_ship -= 1;
                            }
                        }
                        case (2) -> {
                            if (player2[x1][y1 + 1] == 0) {
                                player2[x1][y1 + 1] = ship;
                                y1 = y1 + 1;
                                new_power_ship -= 1;
                            }
                        }
                        case (3) -> {
                            if (player2[x1][y1 - 1] == 0) {
                                player2[x1][y1 - 1] = ship;
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
            generate_stuff(index_ship.pop(), index_ship.pop());
        }
        PrintArray(player2);
        System.out.println("_____________________________________________________________");
    }
    protected static void generate_ships() {
        int[] nymbers_ships = {4, 3, 2, 1};
        for(int type_ship = 3; type_ship >= 0; type_ship--){
            while (nymbers_ships[type_ship] != 0){
                try {
                    set_ship(type_ship + 1);
                }
                catch (Exception e){
                    System.out.println("Error");
                }
                finally {
                    nymbers_ships[type_ship] -= 1;
                }
            }
        }
    }
    private static boolean check_root(int x , int y, int power){
        boolean result = true;
        int len = 0;
        try{
            for (int i = 1; i <= power; i++){
                if(player2[x + i][y] != 0){
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
                if(player2[x - i][y] != 0){
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
                if(player2[x][y + i] != 0){
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
                if(player2[x][y - i] != 0){
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
    private static boolean check_for_move(int x , int y, int power, int move_ship){
        boolean result = false;
        int len = 0;
        try{
            switch (move_ship) {
                case(0)-> {
                    for (int i = 1; i <= power; i++) {
                        if (player2[x + i][y] != 0) {
                            break;
                        } else {
                            len += 1;
                        }
                    }
                }
                case(1)-> {
                    for (int i = 1; i <= power; i++) {
                        if (player2[x - i][y] != 0) {
                            break;
                        } else {
                            len += 1;
                        }
                    }
                }
                case(2)-> {
                    for (int i = 1; i <= power; i++) {
                        if (player2[x][y + i] != 0) {
                            break;
                        } else {
                            len += 1;
                        }
                    }
                }
                case(3)-> {
                    for (int i = 1; i <= power; i++) {
                        if (player2[x][y - i] != 0) {
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
    protected static void generate_stuff(int i , int j){ //0, 0
        int xi = i - 1;
        for (int cx = 1; cx <= 3; cx++){
            int yj = j - 1;
            for (int cy = 1; cy <= 3; cy++){
                if ((xi >= 0 && xi < 10) && (yj >= 0 && yj < 10) && (player2[xi][yj] == 0)){
                    player2[xi][yj] = 8;
                }
                yj += 1;
            }
            xi += 1;
        }
        //PrintArray(player2);
    }
}