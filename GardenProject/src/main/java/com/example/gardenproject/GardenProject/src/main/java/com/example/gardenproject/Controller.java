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
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Controller {

    public Text description;
    public Button beginSimulation;
    Garden garden = new Garden();

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

            if (GridPane.getRowIndex(node) == null){
                GridPane.setRowIndex(node, 0);
            }
            if (GridPane.getColumnIndex(node) == null){
                GridPane.setColumnIndex(node, 0);
            }

            if (Objects.equals(GridPane.getRowIndex(node), row) && Objects.equals(GridPane.getColumnIndex(node), column)) {
                return node;
            }

        }
            return null;
    }

    public void addImage(StackPane sp, FileInputStream input){
        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);

        sp.getChildren().add(imgView);
        imgView.toBack();

        MenuButton mb = (MenuButton) sp.getChildren().get(1);
        for (MenuItem child:mb.getItems()){
            String item = child.getUserData().toString();
            if (Objects.equals(item, "treeItem") | Objects.equals(item, "flowerItem")
                    | Objects.equals(item, "pestControlItem") | Objects.equals(item, "sprinklerItem")
                    | Objects.equals(item, "irrigationItem") ) {
                child.setVisible(false);
            }
            else if(Objects.equals(item, "details") | Objects.equals(item, "remove")){
                child.setVisible(true);
            }
        }

    }

    @FXML
    public void addTree(final ActionEvent event) throws NullPointerException, FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new Tree(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/tree.png");

        addImage(sp, input);

        description.setText(Garden.getDescription(x, y));
    }


    @FXML
    public void addFlower(ActionEvent event) throws FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, new Flower(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/flower.png");

        addImage(sp, input);

        description.setText(Garden.getDescription(x, y));

    }

    @FXML
    public void remove(final ActionEvent event){
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addItem(x, y, null);


        StackPane sp = (StackPane) node;
        sp.getChildren().remove(0);

        MenuButton mb = (MenuButton) sp.getChildren().get(0);

        for (MenuItem child:mb.getItems()) {
            String item = child.getUserData().toString();
            if (Objects.equals(item, "treeItem") | Objects.equals(item, "flowerItem")
                    | Objects.equals(item, "pestControlItem") | Objects.equals(item, "sprinklerItem")
                    | Objects.equals(item, "irrigationItem") ) {
                child.setVisible(true);
            } else if (Objects.equals(item, "details") | Objects.equals(item, "remove")) {
                child.setVisible(false);
            }
        }
        plantType.setText("");
        healthStatus.setText("");
        daySinceWater.setText("");
        infested.setText("");

        for(Item[] item:Garden.grid) {
                System.out.println(Arrays.toString(item));
            }
        description.setText("Item removed");
    }


    @FXML
    public void details(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

      //  System.out.println("node:"  +node);
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
        if (dayNumber!= null){
        int dayN = Integer.parseInt(dayNumber.getText());
        dayNumber.setText(String.valueOf(dayN + 1));
    }
    }

    public void beginSimulation() {
        beginSimulation.setVisible(false);
        simulate();
    }

    public void simulate() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<>() {
            int i =  0;
            @Override
            public void handle(ActionEvent event) {
                updateDay();

                for (Item[] line:Garden.grid){
                    for (Item item:line){
                        Item.checkWatering();

                    }
                }

                i++;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        }
}



