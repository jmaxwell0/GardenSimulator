package com.example.gardenproject;

public class Flower extends Plant {

    String itemName = "Flower";

    public Flower(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    public String getItemName() {
        return this.itemName;
    }


}
