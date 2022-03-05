package com.example.gardenproject;

import java.util.Random;

public class Garden {

    Random randomNum = new Random();
    public int randX = randomNum.nextInt(6);
    public int randY = randomNum.nextInt(6);
    public static final int size = 6;
    public static Item[][] grid;

    public Garden(){grid = new Item[size][size];}

    public void worldCreation() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = null;
            }
        }
    }
    public void addPlant(int x, int y, Plant plant) {grid[x][y] = plant;}

    public static String plantType(int x, int y){
        System.out.println(grid[x][y].getPlantType());
        return grid[x][y].getPlantType();
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



