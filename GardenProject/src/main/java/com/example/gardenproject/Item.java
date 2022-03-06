package com.example.gardenproject;

public abstract class Item {
    Garden garden;
    int x;
    int y;
    private String itemName;
    private String healthStatus;
    private int daysSinceWater;
    private boolean infested;

    Item(Garden garden, int x, int y) {
        this.garden = garden;
        this.x = x;
        this.y = y;
    }

    public abstract String getItemName();
    public abstract String getHealthStatus();
    public abstract int getDaysSinceWater();
    public abstract boolean getInfested();

    public static void checkWatering() {
    }

    public abstract void checkWatering(Item item);
}


