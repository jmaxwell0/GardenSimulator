package com.example.gardenproject;

import java.util.Random;

public class Garden {

    Random randomNum = new Random();
    public int randX = randomNum.nextInt(6);
    public int randY = randomNum.nextInt(6);
    public static final int size = 6;
    public static Item[][] grid;

    public Garden() {
        grid = new Item[size][size];
    }

    public void worldCreation() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = null;
            }
        }
    }
    public void addPlant(int x, int y, Plant plant) {grid[x][y] = plant;}

}



