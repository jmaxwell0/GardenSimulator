package com.example.gardenproject;

public abstract class Plant extends Item {
    String itemName;
    String healthStatus = "Healthy";
    private int daysSinceWater = 0;
    boolean infested;
    Class waterClass;


    Plant(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    public String getHealthStatus() {
        return this.healthStatus;
    }

    public int getDaysSinceWater() {
        return this.daysSinceWater;
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

    public abstract Class getWaterClass();

    public void checkWatering(){
        if (garden.checkSurroundings(this, Irrigation.class) && (this.getWaterClass() == Irrigation.class)){
            this.resetDaysSinceWater();
            Logger.add(x, y, "was watered by an irrigation system");
        } else if (garden.checkSurroundings(this, Sprinkler.class) && (this.getWaterClass() == Sprinkler.class)){
            this.resetDaysSinceWater();
            Logger.add(x, y, "was watered by a sprinkler");
        }else{this.addDaysSinceWater();}
    }

    // check days since water to change color


    // check for pests


}
