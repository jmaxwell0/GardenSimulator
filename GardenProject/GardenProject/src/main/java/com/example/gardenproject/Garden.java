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

    public Class checkClass(int x,  int y) {
        if (validCell(x, y) && grid[x][y] != null) {
            return (grid[x][y].getClass());
        }
        return null;
    }
    public boolean validCell(int x, int y) {
        return x >= 0 && x < 6 && y >= 0 && y < 6;
    }

    public boolean checkSurroundings(Item item, Class watering){
        int x = item.x;
        int y = item.y;


        return (checkClass(x + 1, y) == watering)
                | (checkClass(x + 1, y + 1) == watering)
                | (checkClass(x, y +1) == watering)
                | (checkClass(x + 1,y - 1) == watering)
                | (checkClass(x,y - 1) == watering)
                | (checkClass(x - 1, y - 1) == watering)
                | (checkClass(x - 1, y) == watering)
                | (checkClass(x - 1,y + 1) == watering);
    }
    public boolean checkPestControl(){
        for (Item[] line : Garden.grid) {
            for (Item item : line) {
                if(item != null) {
                    if (item.getClass() == PestControl.class)
                        return true;
                }
            }
    }
    return false;
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

    public static String getDescription(int x, int y) {
        return grid[x][y].getDescription();
    }
}



