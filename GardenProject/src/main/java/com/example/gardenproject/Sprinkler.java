package com.example.gardenproject;

public class Sprinkler extends Watering{

    static String description = "The sprinkler waters all flowers within a one tile range. It does not water trees, use an irrigation system to water trees.";
    private String itemName = "Sprinkler";

    Sprinkler(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    @Override
    public void checkWatering() {}

    @Override
    public void addDaysSinceWater() {}

    @Override
    public int getWaterDeathDays() {
        return 0;
    }

    @Override
    public String getItemName() {
        return this.itemName;
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
    public String getDescription(){
        return description;
    }
}
