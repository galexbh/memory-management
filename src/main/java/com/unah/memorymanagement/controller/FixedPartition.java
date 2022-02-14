package com.unah.memorymanagement.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class FixedPartition {

    @FXML
    private TextField fixedText;
    @FXML
    private TextField fixedMemoryInput;
    @FXML
    private Pane ParentPaneFixed;
    @FXML
    private void getFixedMemory(){
        try {
            double tamMemory = Integer.parseInt(fixedText.getText());
            tamMemory = tamMemory*15;
          ObservableList <Node> bars = ParentPaneFixed.getChildren();

          if (!bars.isEmpty()){
              bars.removeAll();
          }


            if ((tamMemory < 0) || (tamMemory < ParentPaneFixed.getPrefWidth())) {

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
    private void enterFixedMemory(){
        try {

        } catch (Exception e) {

        }
    }
}
