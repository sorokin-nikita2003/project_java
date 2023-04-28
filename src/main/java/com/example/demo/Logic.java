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
        int x1 = random.nextInt(10);
        int y1 = random.nextInt(10);
        int move = random.nextInt(4);
        player2[x1][y1] = ship;
        index_ship.add(x1);
        index_ship.add(y1);
        while (power_ship > 0) {
            try {
                try {
                    switch (move){
                        case (0):
                            if(player2[x1 + 1][y1] == 0) {
                                player2[x1 + 1][y1] = ship;
                                generate_stuff(x1,y1);
                                x1 = x1 + 1;
                                power_ship -= 1;
                            }
                            else {
                                if (player2[x1 + 1][y1] == ship) {
                                    x1 = x1 + 1;
                                }
                            }
                            break;
                        case(1):
                            if(player2[x1 - 1][y1] == 0) {
                                player2[x1 - 1][y1] = ship;
                                x1 = x1 - 1;
                                power_ship -= 1;
                            }
                            else {
                                if (player2[x1 - 1][y1] == ship) {
                                    x1 = x1 - 1;
                                }
                            }
                            break;
                        case(2):
                            if(player2[x1][y1 + 1] == 0) {
                                player2[x1][y1 + 1] = ship;
                                y1 = y1 + 1;
                                power_ship -= 1;
                            }
                            else {
                                if (player2[x1][y1 + 1] == ship) {
                                    y1 = y1 + 1;
                                }
                            }
                            break;
                        case(3):
                            if(player2[x1][y1 - 1] == 0) {
                                player2[x1][y1 - 1] = ship;
                                y1 = y1 - 1;
                                power_ship -= 1;
                            }
                            else {
                                if (player2[x1][y1 - 1] == ship) {
                                    y1 = y1 - 1;
                                }
                            }
                            break;
                    }
                }
                catch (Exception e) {
                    switch (move){
                        case (0):
                            if(player2[x1 - 1][y1] == 0) {
                                player2[x1 - 1][y1] = ship;
                                x1 = x1 - 1;
                                move = 1;
                                power_ship -= 1;
                            }
                            if(player2[x1 - 1][y1] == ship) {
                                x1 = x1 - 1;
                                move = 1;
                            }
                            break;
                        case(1):
                            if(player2[x1 + 1][y1] == 0) {
                                player2[x1 + 1][y1] = ship;
                                x1 = x1 + 1;
                                move = 0;
                                power_ship -= 1;
                            }
                            if(player2[x1 + 1][y1] == ship) {
                                x1 = x1 + 1;
                                move = 0;
                            }
                            break;
                        case(2):
                            if(player2[x1][y1 - 1] == 0) {
                                player2[x1][y1 - 1] = ship;
                                y1 = y1 - 1;
                                move = 3;
                                power_ship -= 1;
                            }
                            if(player2[x1][y1 - 1] == ship) {
                                y1 = y1 - 1;
                                move = 3;
                            }
                            break;
                        case(3):
                            if(player2[x1][y1 + 1] == 0) {
                                player2[x1][y1 + 1] = ship;
                                y1 = y1 + 1;
                                move = 2;
                                power_ship -= 1;
                            }
                            if(player2[x1][y1 + 1] == ship) {
                                y1 = y1 + 1;
                                move = 2;
                            }
                            break;
                    }
                }
            }
            catch (Exception e){
                System.out.print(e);
            }
            finally {
                index_ship.add(x1);
                index_ship.add(y1);
            }
            //System.out.print("pow" + power_ship + " ");
            //System.out.println("move" + move + "  " + x1 + " " + y1);
        }
        while(index_ship.peek()!=null){
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