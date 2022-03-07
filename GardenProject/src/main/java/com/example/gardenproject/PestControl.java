package com.example.gardenproject;

public class PestControl extends Item{
    String itemName = "Pest Control";
    String healthStatus = "N/A";
    String description = "The pest control system detects any infestations across the entire garden. Only one is needed to provide protection.";

    PestControl(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    @Override
    public String getDescription() {return description;}

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

    @Override
    public void checkWatering(Item item) {
    }

}
