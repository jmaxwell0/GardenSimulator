package com.example.gardenproject;

public abstract class Plant extends Item {
    String itemName;
    String healthStatus = "Healthy";
    int daySinceWater = 0;
    boolean infested = false;

    Plant(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    public String getHealthStatus() {
        return this.healthStatus;
    }

    public int getDaysSinceWater() {
        return this.daysSinceWater;
    }

    public boolean getInfested() {
        return infested;
    }
}
