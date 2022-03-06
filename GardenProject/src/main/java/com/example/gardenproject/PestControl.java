package com.example.gardenproject;

public class PestControl extends Item{
    String itemName = "Pest Control";
    String healthStatus = "N/A";

    PestControl(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }

    @Override
    public String getHealthStatus() {
        return this.healthStatus;
    }

    @Override
    public int getDaysSinceWater() {
        return 0;
    }

    @Override
    public boolean getInfested() {
        return false;
    }

}
