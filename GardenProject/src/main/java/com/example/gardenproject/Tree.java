package com.example.gardenproject;

public class Tree extends Plant{
    String plantType = "Tree";
    String healthStatus = "Healthy";
    int daySinceWater = 0;
    boolean infested = false;


    Tree(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    public String getPlantType() {
        return this.plantType;
    }

    @Override
    public String getWateringType() {
        return null;
    }


}
