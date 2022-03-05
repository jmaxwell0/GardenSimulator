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
            System.out.println("x index = " + GridPane.getColumnIndex(node) );
            System.out.println("y index = " + GridPane.getRowIndex(node) );

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
        List<MenuItem> menuList = new ArrayList<>();
        MenuItem mi = (MenuItem) event.getSource();
        String id = mi.getId();
        int x = id.charAt(0) - '0';
        int y = id.charAt(1) - '0';
        System.out.println(x);
        System.out.println(y);
        Node node = getNodeByCoordinate(x, y);

        System.out.println(node);
        garden.addPlant(x, y, new Tree(garden, x, y));

        List<StackPane> stackList = new ArrayList<>();
       // if (node instanceof StackPane) {
        stackList.add((StackPane) node);
        StackPane sp = stackList.get(0);
        FileInputStream input = new FileInputStream("files/tree.png");
        Image img = new Image(input);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);
        sp.getChildren().add(imgView);
        imgView.toBack();

        System.out.println(sp.getChildren().get(1));
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

    protected void updateDay() {
        int dayN = Integer.parseInt(dayNumber.toString());
        dayNumber.setText(String.valueOf(dayN + 1));
    }
}

