package com.example.gardenproject;

public class Flower extends Plant {

    String itemName = "Flower";
    String description = "The flower needs to be watered by a sprinkler. Place a flower within a one tile range of a sprinkler to ensure that the flower does not dehydrate.";

    public Flower(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getItemName() {
        return this.itemName;
    }



}
