package com.example.gardenproject;

public class Garden {

    public static final int size = 6;
    public static Item[][] grid;

    public Garden(){grid = new Item[size][size];}

    // world setup = 6x6 null grid
    public static void worldCreation() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = null;
            }
        }
    }

    // method that checks the class of an x,y cord
    public Class checkClass(int x,  int y) {
        if (validCell(x, y) && grid[x][y] != null) {
            return (grid[x][y].getClass());
        }
        return null;
    }

    // method that checks validity of cell (if in grid)
    public boolean validCell(int x, int y) {
        return x >= 0 && x < 6 && y >= 0 && y < 6;
    }

    // method that checks if the correct watering system is in range of the item (e.g. irrigation next to tree)
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

    // method that checks if pest control is present anywhere on the grid
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


    // add item to the grid
    public void addItem(int x, int y, Item item) {grid[x][y] = item;}

    // get item name of grid item
    public static String ItemType(int x, int y){
        return grid[x][y].getItemName();
    }

    // get health status of grid item
    public static String healthStatus(int x, int y){
        return grid[x][y].getHealthStatus();
    }
    // get days since water status of grid item
    public static int daySinceWater(int x, int y){
        return grid[x][y].getDaysSinceWater();
    }

    // get if infested boolean of grid item
    public static boolean infested(int x, int y){
        return grid[x][y].getInfested();
    }

    // get description of grid item
    public static String getDescription(int x, int y) {
        return grid[x][y].getDescription();
    }
}



