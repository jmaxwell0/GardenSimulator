package com.example.gardenproject;

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
        // pulls image from files, sets fit to size of cell

        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);

        sp.getChildren().add(imgView);
        imgView.toBack();


        // changes menu options to "details" and "remove" only after adding an item

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
        node.setStyle("-fx-background-color:#FFFFFF");
        Logger.add(x, y, "was planted");
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
        node.setStyle("-fx-background-color:#FFFFFF");
        Logger.add(x, y, "was planted");
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
        node.setStyle("-fx-background-color:#FFFFFF");
        Logger.add(x, y, "was planted");

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
        node.setStyle("-fx-background-color:#FFFFFF");
        Logger.add(x, y, "was planted");

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
        node.setStyle("-fx-background-color:#FFFFFF");
        Logger.add(x, y, "was planted");
    }

    @FXML
    public void remove(final ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        Logger.add(x, y, "has been removed");

        garden.addItem(x, y, null);

        StackPane sp = (StackPane) node;
        if (sp.getChildren().size() >= 2) {
            sp.getChildren().subList(2, sp.getChildren().size()).clear();
        }
        sp.getChildren().remove(0);

        MenuButton mb = (MenuButton) sp.getChildren().get(0);

        // switches menu options back to adding options (plants, watering, pest control) and removes "details" and "remove" choices

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

    }


    @FXML
    public void details(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        // sets text of details fields according to status of selected item

        plantType.setText(Garden.ItemType(x, y));
        healthStatus.setText(Garden.healthStatus(x, y));
        daySinceWater.setText(String.valueOf(Garden.daySinceWater(x, y)));
        infested.setText(Boolean.toString(Garden.infested(x, y)));

        description.setText(Garden.getDescription(x, y));
        Logger.add(x, y, "details viewed by user");

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
        node.setStyle("-fx-background-color:#FFFFFF");
        Logger.add(x, y, "was added");
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
        node.setStyle("-fx-background-color:#FFFFFF");
        Logger.add(x, y, "was added");
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
        node.setStyle("-fx-background-color:#FFFFFF");
        Logger.add(x, y, "was added");
    }


    protected void updateDay() {
        if (dayNumber != null) {
            int dayN = Integer.parseInt(dayNumber.getText());
            dayNumber.setText(String.valueOf(dayN + 1));
        }
    }

    public void beginSimulation() {
        beginSimulation.setVisible(false);
        simulate();

        Logger.simpleOutput("Simulation began\n");
    }

    public void water(Item item) {
        item.checkWatering();
        int x = item.x;
        int y = item.y;
        Node node = getNodeByCoordinate(x, y);
        if (item.getDaysSinceWater() == 0) {
            node.setStyle("-fx-background-color:#FFFFFF");
        }

        // setting health status to healthy if watered and not infested
        if (item.getDaysSinceWater() == 0 && !item.getInfested()) {
            item.setHealthStatus("Healthy");
        }

        // if days since water is greater than water deaths days of the plant, it dies and is removed
        if (item.getDaysSinceWater() >= item.getWaterDeathDays()) {
            Logger.add(x, y, "died due to lack of water");

            garden.addItem(x, y, null);
            StackPane sp = (StackPane) node;
            if (sp.getChildren().size() >= 2) {
                sp.getChildren().subList(2, sp.getChildren().size()).clear();
            }
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
            plantType.setText("");
            healthStatus.setText("");
            daySinceWater.setText("");
            infested.setText("");

            description.setText(item.getItemName() + " died due to lack of water");

         // if it has been unwatered for 2/3 of death days time, then turn background red
        } else if (item.getDaysSinceWater() >= item.getWaterDeathDays() * 2 / 3) {
            Logger.dailyUpdate(x, y, "has not been watered for " + item.getDaysSinceWater() + " days. It is very thirsty");
            node.setStyle("-fx-background-color:#FF0000");
            item.setHealthStatus("Very Thirsty");
            item.getHealthStatus();

            // if it has been unwatered for 1/3 of death days time, then turn background orange
        } else if (item.getDaysSinceWater() >= item.getWaterDeathDays() / 3) {
            Logger.dailyUpdate(x, y, "has not been watered for " + item.getDaysSinceWater() + " days. It is unwatered");
            node.setStyle("-fx-background-color:#FFA500");
            item.setHealthStatus("Unwatered");
        }
    }

    public void pestControl(Item item) {
        int x = item.x;
        int y = item.y;
        Node node = getNodeByCoordinate(x, y);
        StackPane sp = (StackPane) node;
        if(Garden.grid[x][y] != null && Garden.grid[x][y].getInfested()){
            Garden.grid[x][y].setHealthStatus("Infested for " + item.getDaysInfested() + " days");
        }
        // if item is infested for 7 days without pest control, it dies
        if (item.getDaysInfested() >= 7) {
            Logger.add(x, y, "died due to infestation");
            garden.addItem(x, y, null);

            MenuButton mb = (MenuButton) sp.getChildren().get(1);

            if (sp.getChildren().size() >= 2) {
                sp.getChildren().subList(2, sp.getChildren().size()).clear();
            }
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

        } else {
            if (garden.checkPestControl() && item.getInfested() && !item.getGassed() && item.getDaysInfested() >= 2) {
                description.setFill(Color.GREEN);
                description.setText("Pesticide deployed!");
                Logger.add(x, y, "had pesticide deployed on it");
                description.setFill(Color.BLACK);
                Garden.grid[x][y].setGassed(true);

                // add gas to infested plants

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
        if (Garden.grid[x][y] != null && Garden.grid[x][y].getGassed()) {
            item.addGassedDays();

            // if gassed for 3 days, stop gassing
            if (item.getGassedDays() >= 3) {
                item.setGassed(false);

                if (sp.getChildren().size() >= 2) {
                    sp.getChildren().subList(2, sp.getChildren().size()).clear();
                }
                description.setFill(Color.GREEN);
                description.setText("Pests successfully removed!");
                Logger.add(x, y, "had its pests successfully killed by pesticide");
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
                Logger.simpleOutput("----------------------------------- Day " + dayNumber.getText());
                updateDay();
                Random randomNum = new Random();

                // every day has a 1/15 chance of infestation
                int r = randomNum.nextInt(15);
                if (r == 0) {
                    try {
                        pests();
                    } catch (FileNotFoundException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // water plants, updates status of plants, and runs pest control check to see if there are pests
                for (Item[] line : Garden.grid) {
                    for (Item item : line) {
                        if (item != null) {
                            if (item.getClass().getSuperclass() == Tree.class | item.getClass().getSuperclass() == Flower.class) {
                                water(item);
                                if (item.getInfested()) {
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

    // pests() is called if 1/15th chance is hit on a particular day
    public void pests() throws FileNotFoundException, InterruptedException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<>() {
            int i = 0;
            @Override
            public void handle(ActionEvent event) {
                Random randomNum = new Random();
                ArrayList<int[]> arrayOfPlants = new ArrayList<>();
                for (Item[] line : Garden.grid) {
                    for (Item item : line) {
                        if (item != null) {
                            if (item.getClass().getSuperclass() == Tree.class | item.getClass().getSuperclass() == Flower.class) {
                                int[] plantXY = {item.x, item.y};
                                arrayOfPlants.add(plantXY);
                            }
                        }
                    }
                }

                // between 2-4 pests are spawned
                int randNumPests = randomNum.nextInt(5 - 2) + 2;
                Integer[] numPestRounds = {randNumPests, arrayOfPlants.size()};
                int minRounds = Collections.min(Arrays.asList(numPestRounds));

                description.setFill(Color.RED);
                description.setText(minRounds + " pests are attacking your garden!!!");
                description.setFill(Color.BLACK);

                Collections.shuffle(arrayOfPlants);
                for (int i = 0; i < minRounds; i++) {

                    int x = arrayOfPlants.get(i)[0];
                    int y = arrayOfPlants.get(i)[1];

                    Node node = getNodeByCoordinate(x, y);

                    Garden.grid[x][y].setInfested(true);

                    if(Garden.grid[x][y].getDaysInfested()==0) {
                        Logger.add(x, y, "has been infested!");
                    }

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
        timeline.setCycleCount(1);
        timeline.play();
    }
}


