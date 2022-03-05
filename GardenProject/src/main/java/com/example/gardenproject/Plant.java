package com.example.gardenproject;

public abstract class Plant extends Item {
    String plantType;
    String healthStatus;
    int daySinceWater;
    boolean infested;

    Plant(Garden garden, int x, int y) {
        super(garden, x, y);
    }


}
