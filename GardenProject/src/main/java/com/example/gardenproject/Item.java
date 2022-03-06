package com.example.gardenproject;

public abstract class Item {
    Garden garden;
    int x;
    int y;
    String itemName;
    String healthStatus;
    int daysSinceWater;
    boolean infested;

    Item(Garden garden, int x, int y) {
        this.garden = garden;
        this.x = x;
        this.y = y;
    }

    public abstract String getItemName();
    public abstract String getHealthStatus();
    public abstract int getDaysSinceWater();
    public abstract boolean getInfested();
}


