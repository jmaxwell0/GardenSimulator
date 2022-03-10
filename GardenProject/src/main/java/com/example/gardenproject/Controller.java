package com.example.gardenproject;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class Controller {

    public Text description;
    public Button beginSimulation;
    Garden garden = new Garden();
    private final int dayLength = 2; //in seconds

    @FXML
    public javafx.scene.layout.GridPane grid;

    @FXML
    public Text dayNumber;
    public Text plantType;
    public Text healthStatus;
    public Text daySinceWater;
    public Text infested;

    Node getNodeByCoordinate(Integer row, Integer column) throws NullPointerException {
        for (Node node : grid.getChildren()) {

            if (GridPane.getRowIndex(node) == null) {
                GridPane.setRowIndex(node, 0);
            }
            if (GridPane.getColumnIndex(node) == null) {
                GridPane.setColumnIndex(node, 0);
            }

            if (Objects.equals(GridPane.getRowIndex(node), row) && Objects.equals(GridPane.getColumnIndex(node), column)) {
                return node;
            }

        }
        return null;
    }

    public void addImage(StackPane sp, FileInputStream input) {
        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);

        sp.getChildren().add(imgView);
        imgView.toBack();

        MenuButton mb = (MenuButton) sp.getChildren().get(1);
        for (MenuItem child : mb.getItems()) {
            String item = child.getUserData().toString();
            if (Objects.equals(item, "treeItem") | Objects.equals(item, "flowerItem")
                    | Objects.equals(item, "pestControlItem") | Objects.equals(item, "sprinklerItem")
                    | Objects.equals(item, "irrigationItem")) {
                child.setVisible(false);
            } else if (Objects.equals(item, "details") | Objects.equals(item, "remove")) {
                child.setVisible(true);
            }
        }
    }

    @FXML
    public void addOrangeTree(final ActionEvent event) throws NullPointerException, FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new OrangeTree(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/orangetree.png");

        addImage(sp, input);

        description.setText(Garden.getDescription(x, y));
    }

    @FXML
    public void addPlumTree(final ActionEvent event) throws NullPointerException, FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new PlumTree(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/plumtree.png");

        addImage(sp, input);

        description.setText(Garden.getDescription(x, y));
    }


    @FXML
    public void addHydrangea(ActionEvent event) throws FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new Hydrangea(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/hydrangea.png");

        addImage(sp, input);

        description.setText(Garden.getDescription(x, y));

    }
    @FXML
    public void addRose(ActionEvent event) throws FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new Rose(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/rose.png");

        addImage(sp, input);

        description.setText(Garden.getDescription(x, y));

    }
    @FXML
    public void addDaisy(ActionEvent event) throws FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new Daisy(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/daisy.png");

        addImage(sp, input);

        description.setText(Garden.getDescription(x, y));

    }

    @FXML
    public void remove(final ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, null);


        StackPane sp = (StackPane) node;
        sp.getChildren().remove(0);

        MenuButton mb = (MenuButton) sp.getChildren().get(0);

        for (MenuItem child : mb.getItems()) {
            String item = child.getUserData().toString();
            if (Objects.equals(item, "treeItem") | Objects.equals(item, "flowerItem")
                    | Objects.equals(item, "pestControlItem") | Objects.equals(item, "sprinklerItem")
                    | Objects.equals(item, "irrigationItem")) {
                child.setVisible(true);
            } else if (Objects.equals(item, "details") | Objects.equals(item, "remove")) {
                child.setVisible(false);
            }
        }
        plantType.setText("");
        healthStatus.setText("");
        daySinceWater.setText("");
        infested.setText("");

        node.setStyle("-fx-background-color:#FFFFFF");

        description.setText("Item removed");

        for (Item[] line:Garden.grid){
            System.out.println(Arrays.toString(line));
        }
    }


    @FXML
    public void details(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        plantType.setText(Garden.ItemType(x, y));
        healthStatus.setText(Garden.healthStatus(x, y));
        daySinceWater.setText(String.valueOf(Garden.daySinceWater(x, y)));
        infested.setText(Boolean.toString(Garden.infested(x, y)));

        description.setText(Garden.getDescription(x, y));
    }

    public void addSprinkler(ActionEvent event) throws FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new Sprinkler(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/sprinkler.png");

        addImage(sp, input);
        description.setText(Garden.getDescription(x, y));
    }

    public void addIrrigation(ActionEvent event) throws FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new Irrigation(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/pipes.png");

        addImage(sp, input);
        description.setText(Garden.getDescription(x, y));
    }

    public void addPestControl(ActionEvent event) throws FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new PestControl(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/pestcontrol.png");

        addImage(sp, input);

        description.setText(Garden.getDescription(x, y));
    }


    protected void updateDay() {
        if (dayNumber != null) {
            int dayN = Integer.parseInt(dayNumber.getText());
            dayNumber.setText(String.valueOf(dayN + 1));
        }
    }

    public void beginSimulation() throws FileNotFoundException, InterruptedException {
        beginSimulation.setVisible(false);
        simulate();
        pests();
    }

    public void water(Item item) {
        item.checkWatering();
        int x = item.x;
        int y = item.y;
        Node node = getNodeByCoordinate(x, y);
        if (item.getDaysSinceWater() == 0) {
            node.setStyle("-fx-background-color:#FFFFFF");
        }

        if (item.getDaysSinceWater() >= item.getWaterDeathDays()) {
            garden.addItem(x, y, null);
            StackPane sp = (StackPane) node;
            sp.getChildren().remove(0);
            MenuButton mb = (MenuButton) sp.getChildren().get(0);

            for (MenuItem child : mb.getItems()) {
                String mitem = child.getUserData().toString();
                if (Objects.equals(mitem, "treeItem") | Objects.equals(mitem, "flowerItem")
                        | Objects.equals(mitem, "pestControlItem") | Objects.equals(mitem, "sprinklerItem")
                        | Objects.equals(mitem, "irrigationItem")) {
                    child.setVisible(true);
                } else if (Objects.equals(mitem, "details") | Objects.equals(mitem, "remove")) {
                    child.setVisible(false);
                }
            }
            node.setStyle("-fx-background-color:#FFFFFF");
            item.setHealthStatus("Healthy");
            plantType.setText("");
            healthStatus.setText("");
            daySinceWater.setText("");
            infested.setText("");

            description.setText(item.getItemName() + " died due to lack of water");
        } else if (item.getDaysSinceWater() >= item.getWaterDeathDays() * 2 / 3) {
            node.setStyle("-fx-background-color:#FF0000");
            item.setHealthStatus("Very Thirsty");
            item.getHealthStatus();
        } else if (item.getDaysSinceWater() >= item.getWaterDeathDays() / 3) {
            node.setStyle("-fx-background-color:#FFA500");
            item.setHealthStatus("Unwatered");
        }
    }

    public void pestControl(Item item) {

        int x = item.x;
        int y = item.y;
        Node node = getNodeByCoordinate(x, y);
        StackPane sp = (StackPane) node;

        // if item is infested for 10 days without pest control, it dies
        if(item.getDaysInfested() > 10){
            garden.addItem(x, y, null);

            MenuButton mb = (MenuButton) sp.getChildren().get(1);

            sp.getChildren().remove(2);
            sp.getChildren().remove(0);


            for (MenuItem child : mb.getItems()) {
                String mitem = child.getUserData().toString();
                if (Objects.equals(mitem, "treeItem") | Objects.equals(mitem, "flowerItem")
                        | Objects.equals(mitem, "pestControlItem") | Objects.equals(mitem, "sprinklerItem")
                        | Objects.equals(mitem, "irrigationItem")) {
                    child.setVisible(true);
                } else if (Objects.equals(mitem, "details") | Objects.equals(mitem, "remove")) {
                    child.setVisible(false);
                }
            }
            node.setStyle("-fx-background-color:#FFFFFF");
            item.setHealthStatus("Healthy");
            plantType.setText("");
            healthStatus.setText("");
            daySinceWater.setText("");
            infested.setText("");

            description.setText(item.getItemName() + " died due to infestation");



        }else{
            if (garden.checkPestControl() && item.getInfested() && !item.getGassed()) {
                description.setFill(Color.GREEN);
                description.setText("Pesticide deployed!");
                description.setFill(Color.BLACK);
                Garden.grid[x][y].setGassed(true);

                // add gas for several days until bugs are removed

                FileInputStream input = null;
                try {
                    input = new FileInputStream("files/gas.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                assert input != null;

                Image img = new Image(input);
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(100);
                imgView.setFitWidth(100);

                sp.getChildren().add(imgView);
                imgView.toFront();
            }
        }
        if(Garden.grid[x][y] != null && Garden.grid[x][y].getGassed()){
            item.addGassedDays();
            // if gassed for 3 days, stop gassing
            if(item.getGassedDays()>= 3){
                item.setGassed(false);
                for(int i=2; i<=sp.getChildren().size(); i++){
                    sp.getChildren().remove(2);
                }
                description.setFill(Color.GREEN);
                description.setText("Pests successfully removed!");
                description.setFill(Color.BLACK);
                item.setInfested(false);
                item.resetDaysInfested();
                item.resetGassedDays();
            }
        }
    }


    public void simulate() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(dayLength), new EventHandler<>() {
            int i = 0;

            @Override
            public void handle(ActionEvent event) {
                updateDay();

                for (Item[] line : Garden.grid) {
                    for (Item item : line) {
                        if (item != null) {
                            if (item.getClass().getSuperclass() == Tree.class | item.getClass().getSuperclass() == Flower.class) {
                                water(item);
                                if(item.getInfested()){
                                    item.addDaysInfested();
                                }
                                pestControl(item);


                                }

                            }
                        }
                    }
                i++;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // find a way to have pests run in background on separate thread while able to update the ui,
    // while loop doesnt work, Timeline can't have time in between runs change...

  /*  public void pests() {
        Thread thread = new Thread() {
            public void run() {
                Random randomNum = new Random();

                try {
                    Thread.sleep(randomNum.nextInt(5 - 1) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ArrayList<int[]> arrayOfPlants = new ArrayList<>();

                for (Item[] line : Garden.grid) {
                    for (Item item : line) {
                        if (item != null) {
                            if (item.getClass() == Tree.class | item.getClass() == Flower.class) {
                                int[] plantXY = {item.x, item.y};
                                arrayOfPlants.add(plantXY);
                            }
                        }
                    }
                }

                int randNumPests = randomNum.nextInt(5 - 1) + 1;
                Integer[] numPestRounds = {randNumPests, arrayOfPlants.size()};
                int minRounds = Collections.min(Arrays.asList(numPestRounds));

                Collections.shuffle(arrayOfPlants);
                for (int i = 0; i < minRounds; i++) {

                    int x = arrayOfPlants.get(i)[0];
                    int y = arrayOfPlants.get(i)[1];

                    Garden.grid[x][y].setInfested();
                    Garden.grid[x][y].setHealthStatus("Infested for " + Garden.grid[x][y].getDaysInfested() + " days");
                    // insert pests here
                }
            }
        };
        thread.start();
    }*/


    public void pests() throws FileNotFoundException, InterruptedException {

        Random randomNum = new Random();
        int randTime = randomNum.nextInt(30 - 15) + 15;

        ArrayList<int[]> arrayOfPlants = new ArrayList<>();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(randTime), new EventHandler<>(){
            int i =  0;

            @Override
            public void handle(ActionEvent event) {
                for (Item[] line:Garden.grid){
                    for (Item item:line){
                        if (item != null){
                            if (item.getClass().getSuperclass() == Tree.class | item.getClass().getSuperclass() == Flower.class) {
                                int[] plantXY = {item.x, item.y};
                                arrayOfPlants.add(plantXY);
                            }
                        }
                    }
                }

                int randNumPests = randomNum.nextInt(5 - 2) + 2;
                Integer[] numPestRounds = {randNumPests, arrayOfPlants.size()};
                int minRounds = Collections.min(Arrays.asList(numPestRounds));

                description.setFill(Color.RED);
                description.setText(minRounds + " pests are attacking your garden!!!");
                description.setFill(Color.BLACK);

                Collections.shuffle(arrayOfPlants);
                for(int i=0; i<minRounds; i++){

                    int x = arrayOfPlants.get(i)[0];
                    int y = arrayOfPlants.get(i)[1];

                    Node node = getNodeByCoordinate(x, y);


                    Garden.grid[x][y].setInfested(true);
                    Garden.grid[x][y].setHealthStatus("Infested for " + Garden.grid[x][y].getDaysInfested() + " days");

                    StackPane sp = (StackPane) node;

                    FileInputStream input = null;
                    try {
                        input = new FileInputStream("files/pests.png");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    assert input != null;

                    Image img = new Image(input);
                    ImageView imgView = new ImageView(img);
                    imgView.setFitHeight(100);
                    imgView.setFitWidth(100);

                    sp.getChildren().add(imgView);
                    imgView.toFront();
                }
                arrayOfPlants.clear();

                i++;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


}



