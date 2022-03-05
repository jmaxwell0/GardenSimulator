package com.example.gardenproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Controller {

    Garden garden = new Garden();

    @FXML
    public javafx.scene.layout.GridPane grid;

    @FXML
    public Text dayNumber;
    public Text plantType;
    public Text healthStatus;
    public Text daySinceWater;
    public Text infested;

    Node getNodeByCoordinate(Integer column, Integer row) throws NullPointerException {
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

    @FXML
    protected void addTree(final ActionEvent event) throws NullPointerException, FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addPlant(x, y, new Tree(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/tree.png");

        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);

        sp.getChildren().add(imgView);
        imgView.toBack();

        MenuButton mb = (MenuButton) sp.getChildren().get(1);
        for (MenuItem child:mb.getItems()){
            String item = child.getUserData().toString();
            if(Objects.equals(item, "treeItem") | Objects.equals(item, "flowerItem")){
                child.setVisible(false);
            }
            else if(Objects.equals(item, "details") | Objects.equals(item, "remove")){
                child.setVisible(true);
            }
        }
    }


    @FXML
    public void addFlower(ActionEvent event) throws FileNotFoundException {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addPlant(x, y, new Flower(garden, x, y));

        StackPane sp = (StackPane) node;
        FileInputStream input = new FileInputStream("files/flower.png");

        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);

        sp.getChildren().add(imgView);
        imgView.toBack();

        MenuButton mb = (MenuButton) sp.getChildren().get(1);
        for (MenuItem child:mb.getItems()){
            String item = child.getUserData().toString();
            if(Objects.equals(item, "treeItem") | Objects.equals(item, "flowerItem")){
                child.setVisible(false);
            }
            else if(Objects.equals(item, "details") | Objects.equals(item, "remove")){
                child.setVisible(true);
            }
        }
    }

    @FXML
    public void remove(final ActionEvent event){
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        garden.addPlant(x, y, null);


        StackPane sp = (StackPane) node;
        sp.getChildren().remove(0);

        MenuButton mb = (MenuButton) sp.getChildren().get(0);

        for (MenuItem child:mb.getItems()) {
            String item = child.getUserData().toString();
            if (Objects.equals(item, "treeItem") | Objects.equals(item, "flowerItem")) {
                child.setVisible(true);
            } else if (Objects.equals(item, "details") | Objects.equals(item, "remove")) {
                child.setVisible(false);
            }
        }
        plantType.setText("");
        healthStatus.setText("");
        daySinceWater.setText("");
        infested.setText("");


    }



    @FXML
    public void details(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';

        Node node = getNodeByCoordinate(x, y);

        plantType.setText(Garden.plantType(x, y));
        healthStatus.setText(Garden.healthStatus(x, y));
        daySinceWater.setText(String.valueOf(Garden.daySinceWater(x, y)));
        infested.setText(Boolean.toString(Garden.infested(x, y)));

    }



    protected void updateDay() {
        int dayN = Integer.parseInt(dayNumber.toString());
        dayNumber.setText(String.valueOf(dayN + 1));
    }
}

