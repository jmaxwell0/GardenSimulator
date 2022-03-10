package com.example.gardenproject;

public class Tree extends Plant{
    private final Class waterClass = Irrigation.class;

    Tree(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    @Override
    public String getDescription() {
        String description = "Trees need to be watered by an irrigation system. Place trees within a one tile range of an irrigation system to ensure that the trees do not dehydrate.";
        return description;
    }

    public String getItemName() {
        String itemName = "Tree";
        return itemName;
    }

    public void checkWatering(){
        super.checkWatering();
    }
    public Class getWaterClass(){return this.waterClass;}

    @Override
    public int getWaterDeathDays(){
        int waterDeathDays = 21;
        return waterDeathDays;}

}

class OrangeTree extends Tree{

    OrangeTree(Garden garden, int x, int y) {
        super(garden, x, y);
    }
}

class PlumTree extends Tree{

    PlumTree(Garden garden, int x, int y) {
        super(garden, x, y);
    }
}
