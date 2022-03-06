package com.example.gardenproject;

public class Irrigation extends Watering{
    String itemName = "Irrigation";

    Irrigation(Garden garden, int x, int y) {
        super(garden, x, y);
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
    public void checkWatering(Item item) {

    }
}
