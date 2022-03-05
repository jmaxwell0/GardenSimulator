package com.example.gardenproject;

public abstract class Item {
    Garden garden;
    int x;
    int y;
    String plantType;
    String healthStatus;
    int daysSinceWater;
    boolean infested;
    String wateringType;

    Item(Garden garden, int x, int y) {
        this.garden = garden;
        this.x = x;
        this.y = y;
    }

    public abstract String getPlantType();
    public abstract String getHealthStatus();
    public abstract int getDaysSinceWater();
    public abstract boolean getInfested();
    public abstract String getWateringType();
}


