package com.example.gardenproject;

public class Tree extends Plant{
    String itemName = "Tree";
    String description = "The tree needs to be watered by an irrigation system. Place a tree within a one tile range of an irrigation system to ensure that the tree does not dehydrate.";

    Tree(Garden garden, int x, int y) {
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
