package com.example.gardenproject;

public abstract class Plant extends Item {
    String itemName;
    String healthStatus = "Healthy";
    private int daysSinceWater = 0;
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

    public void setHealthStatus(String s){
        this.healthStatus = s;
    }

    public void resetDaysSinceWater() {
        this.daysSinceWater = 0;
    }

    public void addDaysSinceWater() {
        this.daysSinceWater++;
    }

    @Override
    public void checkWatering(Item item){
        if (garden.checkSurroundings(item, "Tree", Irrigation.class)){
            this.resetDaysSinceWater();
        } else if (garden.checkSurroundings(item, "Flower", Sprinkler.class)){
            this.resetDaysSinceWater();
        }else{this.addDaysSinceWater();}
    }


    // check days since water to change color
    // check for pests
}
