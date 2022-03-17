package com.example.gardenproject;

import  java.io.FileWriter;
import java.io.IOException;

//NOTE by default, watering updates and pest clearing aren't shown in console.txt
//change values in GardenApplication.java main() startup()

public class Logger {
    private static final long MS = GardenApplication.MS;
    static boolean showDailyUpdates;

    private static FileWriter clearFile;
    private static FileWriter fw;

    //disables watering and pest updates if off
    public static void dailyUpdate(int x, int y, String action) { if (showDailyUpdates) add(x, y, action);
    }
    //prints output to file
    public static void simpleOutput(Object output, String suffix) {
        long time = (System.currentTimeMillis() - MS) / 1000;

        try {
            fw.write(suffix);
            fw.write(String.format("\n[%d:%d:%d] %s", time / 3600, time / 60, time, output.toString()));
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void simpleOutput(Object output) {
        simpleOutput(output, "");
    }

    //adds action to console.txt
    public static void add(int x, int y, String action) {
        long time = (System.currentTimeMillis() - MS) / 1000;

        try {
            fw.write(String.format("\n[%d:%d:%d] %s (%d, %d) %s", time / 3600, time / 60, time,
                Garden.grid[x][y].getClass().toString().replaceFirst(
                "class com.example.gardenproject.", ""), x, y, action));
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //clears console.txt
    public static void clear() {
        try {
            clearFile.write("\t\t\tFORMAT: logs are written as '[<hours>:<minutes>:<seconds>] " +
                "<Object> (<x>, <y>) <action>'");
            clearFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //automatically clears console.txt unless dontClear is true (default false)
    //defines filewriters and adds shutdownhook
    public static void startup(boolean showDailyUpdates, boolean dontClear) {
        Logger.showDailyUpdates = showDailyUpdates;
        try {
            fw = new FileWriter("files/console.txt",
                    true);
            if (!dontClear) clearFile = new FileWriter(
                    "files/console.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        if (!dontClear) clear();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            simpleOutput("Simulation ended", "\n");
            try {
                fw.close();
                if (!dontClear) clearFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public static void startup(boolean showDailyUpdates) {
        startup(showDailyUpdates, false);
    }
    public static void startup() {
        startup(false, false);
    }
}
