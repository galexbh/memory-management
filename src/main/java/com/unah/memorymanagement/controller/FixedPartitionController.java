package com.unah.memorymanagement.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class FixedPartitionController implements Initializable {

    @FXML
    private ComboBox comboBoxFixedMemory;
    @FXML
    private TextField fixedMemoryInput;
    @FXML
    private Pane ParentPaneFixed;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> listAlgorithms = FXCollections.observableArrayList(3, 5, 10, 12, 15, 20);
        comboBoxFixedMemory.setItems(listAlgorithms);
    }


    @FXML
    private void getFixedMemory() {
        try {

            if (comboBoxFixedMemory.getSelectionModel().getSelectedItem() == null) {

                return;

            }
            int tamMemory = (int) comboBoxFixedMemory.getSelectionModel().getSelectedItem();
            tamMemory = tamMemory * 15;

            ParentPaneFixed.getChildren().clear();


            if ((tamMemory > 0) && (tamMemory < ParentPaneFixed.getPrefWidth())) {

                int countPartition = (int) (ParentPaneFixed.getPrefWidth() / tamMemory);

                double restMemory = ParentPaneFixed.getPrefWidth() - tamMemory * countPartition;
                int auxLayoutX = 0;
                for (int i = 0; i < countPartition; i++) {
                    ProgressBar tempProgressBar = new ProgressBar();

                    ParentPaneFixed.getChildren().add(tempProgressBar);
                    tempProgressBar.setProgress(0);
                    tempProgressBar.setLayoutY(0);
                    tempProgressBar.setLayoutX(auxLayoutX);
                    tempProgressBar.setPrefHeight(ParentPaneFixed.getPrefHeight());
                    tempProgressBar.setPrefWidth(tamMemory);
                    auxLayoutX += tamMemory;
                }

                if (restMemory != 0) {
                    ProgressBar tempProgressBar = new ProgressBar();
                    ParentPaneFixed.getChildren().add(tempProgressBar);
                    tempProgressBar.setProgress(0);
                    tempProgressBar.setLayoutY(0);
                    tempProgressBar.setLayoutX(tamMemory * countPartition);
                    tempProgressBar.setPrefHeight(ParentPaneFixed.getPrefHeight());
                    tempProgressBar.setPrefWidth(restMemory);

                }
            }


        } catch (Exception e) {
            System.out.println("error");//Sin solución actual para error
        }
    }

    @FXML
    private void enterFixedMemory() {
        try {
            double tamMemory = Integer.parseInt(fixedMemoryInput.getText());
            tamMemory = tamMemory * 15;
            ObservableList<Node> bars = ParentPaneFixed.getChildren();
            if (!bars.isEmpty()) {

                ProgressBar tempBar = (ProgressBar) bars.get(0);
                double maxMemory = tempBar.getPrefWidth();
                if (maxMemory >= tamMemory) {

                    if (checkAvailableEspace(bars, tamMemory)) {
                        Random random = new Random();
                        int randomPosition = random.nextInt(bars.size());


                        tempBar = (ProgressBar) bars.get(randomPosition);

                        if (tempBar.getProgress() == 0) {

                            tempBar.setProgress(tamMemory / tempBar.getPrefWidth());
                        } else {
                            enterFixedMemory();

                        }

                    }
                }

            }


        } catch (Exception e) {
            //Manejo de errores

        }
    }

    private boolean checkAvailableEspace(ObservableList<Node> bars, double tamMemory) {
        for (Node bar : bars) {
            ProgressBar tempBar = (ProgressBar) bar;

            if ((tempBar.getProgress() == 0) && (tempBar.getPrefWidth() >= tamMemory)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void clearBarFixed() {
        ObservableList<Node> bars = ParentPaneFixed.getChildren();

        for (Node bar : bars) {
            ProgressBar tempBar = (ProgressBar) bar;
            tempBar.setProgress(0);
        }
    }
}

