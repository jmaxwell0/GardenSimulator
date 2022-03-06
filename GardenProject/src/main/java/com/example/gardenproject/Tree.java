package com.example.gardenproject;

public class Tree extends Plant{
    String itemName = "Tree";

    Tree(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    public String getItemName() {
        return this.itemName;
    }


}
