package com.example.gardenproject;

public abstract class Watering extends Item {

    String healthStatus = "N/A";

    Watering(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    public String getHealthStatus(){
        return this.healthStatus;
    }


}
