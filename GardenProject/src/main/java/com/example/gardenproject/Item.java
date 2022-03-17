package com.example.gardenproject;

public abstract class Item {
    Garden garden;
    int x;
    int y;

    private boolean infested;
    private int daysInfested = 0;
    private boolean gassed;
    private int gassedDays;

    Item(Garden garden, int x, int y) {
        this.garden = garden;
        this.x = x;
        this.y = y;
    }

    public abstract String getDescription();
    public abstract String getItemName();
    public abstract String getHealthStatus();
    public abstract int getDaysSinceWater();
    public abstract void checkWatering();
    public abstract void addDaysSinceWater();
    public abstract int getWaterDeathDays();
    public int getDaysInfested(){
        return this.daysInfested;
    }
    public void addDaysInfested(){
        this.daysInfested++;
    }

    public void resetDaysInfested(){
        this.daysInfested = 0;
    }
    public void setInfested(boolean boo){
        this.infested = boo;
    }

    public void setGassed(boolean boo){
        this.gassed = boo;
    }

    public boolean getGassed(){
        return this.gassed;
    }

    public void addGassedDays(){
        this.gassedDays++;
    }

    public void resetGassedDays(){
        this.gassedDays = 0;
    }
    public int getGassedDays(){
        return this.gassedDays;
    }

    public boolean getInfested() {
        return infested;
    }

    public void setHealthStatus(String status){
    }
}


