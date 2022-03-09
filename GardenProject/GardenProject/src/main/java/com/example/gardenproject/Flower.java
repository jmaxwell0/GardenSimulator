package com.example.gardenproject;

import java.lang.reflect.GenericDeclaration;

public class Flower extends Plant {

    private final Class waterClass = Sprinkler.class;


    public Flower(Garden garden, int x, int y) {
        super(garden, x, y);
    }

    @Override
    public String getDescription() {
        return "The flower needs to be watered by a sprinkler. Place a flower within a one tile range of a sprinkler to ensure that the flower does not dehydrate.";
    }

    public String getItemName() {
        return "Flower";
    }

    public void checkWatering(){
        super.checkWatering();
    }
    public Class getWaterClass(){return this.waterClass;}
    @Override
    public int getWaterDeathDays(){
        return 7;}



}
