package com.example.gardenproject;

public class Flower extends Plant {

    private final Class waterClass = Sprinkler.class;


    public Flower(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    @Override
    public String getDescription() {
        return "Flowers needs to be watered by a sprinkler. Place flowers within a one tile range of a sprinkler to ensure that the flowers do not dehydrate.";
    }

    @Override
    public String getItemName(){return "Flower";}

    public void checkWatering(){
        super.checkWatering();
    }
    public Class getWaterClass(){return this.waterClass;}
    @Override
    public int getWaterDeathDays(){
        return 7;}

}

class Daisy extends Flower{

    public Daisy(Garden garden, int x, int y) {
        super(garden, x, y);
    }
    @Override
    public String getItemName(){return "Daisy";}
}

class Rose extends Flower{

    public Rose(Garden garden, int x, int y) {
        super(garden, x, y);
    }
    @Override
    public String getItemName(){return "Rose";}
}

class Hydrangea extends Flower{

    public Hydrangea(Garden garden, int x, int y) {
        super(garden, x, y);
    }
    @Override
    public String getItemName(){return "Hydrangea";}
}