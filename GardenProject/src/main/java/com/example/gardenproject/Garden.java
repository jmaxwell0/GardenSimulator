package com.example.gardenproject;

import java.util.Objects;
import java.util.Random;

public class Garden {

    Random randomNum = new Random();
    public int randX = randomNum.nextInt(6);
    public int randY = randomNum.nextInt(6);
    public static final int size = 6;
    public static Item[][] grid;

    public Garden(){grid = new Item[size][size];}

    public static void worldCreation() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = null;
            }
        }
    }


    public boolean checkSurroundings(Item item, String plantType, Class watering){
        int x = item.x;
        int y = item.y;

        if((grid[x+1][y].getClass() == watering)
            | (grid[x+1][y+1].getClass() == watering)
            | (grid[x+1][y-1].getClass() == watering)
            | (grid[x][y+1].getClass() == watering)
            | (grid[x-1][y+1].getClass() == watering)
            | (grid[x-1][y].getClass() == watering)
            | (grid[x][y-1].getClass() == watering)
            | (grid[x-1][y-1].getClass() == watering)){
            return true;
    } else {
            return false;
        }
}



    public void addItem(int x, int y, Item item) {grid[x][y] = item;}

    public static String ItemType(int x, int y){
        return grid[x][y].getItemName();
    }

    public static String healthStatus(int x, int y){
        return grid[x][y].getHealthStatus();
    }

    public static int daySinceWater(int x, int y){
        return grid[x][y].getDaysSinceWater();
    }

    public static boolean infested(int x, int y){
        return grid[x][y].getInfested();
    }



}



