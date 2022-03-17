package com.example.gardenproject;

public class Tree extends Plant{
    private final Class waterClass = Irrigation.class;

    Tree(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    @Override
    public String getDescription() {
        return "Trees need to be watered by an irrigation system. Place trees within a one tile range of an irrigation system to ensure that the trees do not dehydrate.";
    }

    public String getItemName() {
        return "Tree";
    }

    public void checkWatering(){
        super.checkWatering();
    }
    public Class getWaterClass(){return this.waterClass;}

    @Override
    public int getWaterDeathDays(){
        return 21;}

}

class OrangeTree extends Tree{

    OrangeTree(Garden garden, int x, int y) {
        super(garden, x, y);
    }
    @Override
    public String getItemName() {
        return "Orange Tree";
    }
}

class PlumTree extends Tree{

    PlumTree(Garden garden, int x, int y) {
        super(garden, x, y);
    }
    @Override
    public String getItemName() {
        return "Plum Tree";
    }
}
