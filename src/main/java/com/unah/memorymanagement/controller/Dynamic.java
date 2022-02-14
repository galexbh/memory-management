package com.unah.memorymanagement.controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Dynamic implements Initializable {
    private int lastBar = 0;

    @FXML
    private Pane PaneParentDyamic;

    @FXML
    private TextField dynamicText;
    @FXML
    private ComboBox comboBoxAlgorithms;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<String>  listAlgorithms= FXCollections.observableArrayList("Primer ajuste","Mejor ajuste","Siguiente ajuste");
        comboBoxAlgorithms.setItems(listAlgorithms);

    }

    @FXML
    private void enterDynamicMemory(){
        try {
            ObservableList <Node> allBars = PaneParentDyamic.getChildren();
           String selection = (String) comboBoxAlgorithms.getSelectionModel().getSelectedItem();
          double  tamMemory = Integer.parseInt(dynamicText.getText());
          tamMemory = tamMemory*15;
          switch (selection){
              case "Primer ajuste":
                  FirstFit(tamMemory, allBars);
                  break;
              case "Mejor ajuste":
                  BestFit(tamMemory, allBars);
                  break;
              case "Siguiente ajuste":
                  NextFit(tamMemory, allBars);

              default:
                  System.out.println("no selected");
                  break;
            }


        } catch (Exception e) {

        }

    }
    private void FirstFit(double tamMemory, ObservableList <Node> allBars ){


        for (int i = 0;i < allBars.size();i++){
            ProgressBar tempBar = (ProgressBar) allBars.get(i);
            if (tempBar.getProgress() == 0){
                if (tempBar.getPrefWidth() > tamMemory){
                    splitProgressBar(tempBar, tamMemory);
                    lastBar = i;
                    break;
                } else if (tempBar.getPrefWidth() == tamMemory){
                    tempBar.setProgress(1);
                    tempBar.setOnMouseClicked((event) -> clearMemory(tempBar));
                    lastBar = i;
                    break;
                }
            }

        }

    }
    private void BestFit(double tamMemory, ObservableList <Node> allBars){

        double temp = -1;
        ProgressBar auxBar = new ProgressBar();
        for (Node bar: allBars){
            ProgressBar tempBar = (ProgressBar) bar;
            double widthTemp = tempBar.getPrefWidth();
            if ((tamMemory > widthTemp) || (tempBar.getProgress() != 0)){continue;}

            if ((tamMemory <= widthTemp  )){
                if (temp == -1){
                    auxBar = tempBar;
                    temp = 1;
                } else if (widthTemp < auxBar.getWidth()){
                    auxBar = tempBar;
                }
            }
        }
        if (temp != - 1) {
            splitProgressBar(auxBar, tamMemory);
            lastBar = PaneParentDyamic.getChildren().indexOf(auxBar);
        }



    }
    private void NextFit(double tamMemory, ObservableList <Node> allBars){
        int count = 0;
        for (int i = lastBar;i < allBars.size();i++){
            ProgressBar tempBar = (ProgressBar) allBars.get(i);
            if (tempBar.getProgress() == 0){
                if (tempBar.getPrefWidth() > tamMemory){
                    splitProgressBar(tempBar, tamMemory);
                    lastBar = PaneParentDyamic.getChildren().indexOf(tempBar);
                    break;
                } else if (tempBar.getPrefWidth() == tamMemory){
                    tempBar.setProgress(1);
                    tempBar.setOnMouseClicked((event) -> clearMemory(tempBar));
                    lastBar = PaneParentDyamic.getChildren().indexOf(tempBar);
                    break;
                }
            }
            if (count == allBars.size()){
                break;
            }
            if (i == allBars.size() - 1){
                i = 0;
            }
            count++;
        }
    }
    private void splitProgressBar(ProgressBar bar, double tamMemory) {
        double widthNewBar = bar.getPrefWidth() - tamMemory;
        bar.setPrefWidth(tamMemory);
        bar.setProgress(1);
        bar.setOnMouseClicked((event) -> clearMemory(bar));
        ProgressBar newBar = new ProgressBar();
        PaneParentDyamic.getChildren().add(newBar);
        newBar.setProgress(0);
        newBar.setLayoutY(0);
        newBar.setLayoutX(bar.getLayoutX() + tamMemory);
        newBar.setPrefWidth(widthNewBar);
        newBar.setPrefHeight(PaneParentDyamic.getPrefHeight());
        newBar.setOnMouseClicked((event) -> clearMemory(newBar));

    }

    private void clearMemory(ProgressBar bar){
        bar.setProgress(0);
    }
    @FXML
    private void compactMemory(){
        this.lastBar = 0;
        ObservableList<Node> allBars = PaneParentDyamic.getChildren();
        int widthFreSpace = 0;

        for (int i = 0; i < allBars.size(); i++){
            ProgressBar tempBar = (ProgressBar) allBars.get(i);
            if (tempBar.getProgress() == 0){
                widthFreSpace += tempBar.getPrefWidth();
                PaneParentDyamic.getChildren().removeAll(tempBar);
            }
        }
        allBars = PaneParentDyamic.getChildren();

        for (int i = 0; i < allBars.size(); i++){
            ProgressBar tempBar = (ProgressBar) allBars.get(i);
            if (i == 0)  {
                if (tempBar.getLayoutX() != 0 ){
                    tempBar.setLayoutX(0);
                }
                continue;

            }
            ProgressBar passBar = (ProgressBar) allBars.get(i - 1);

            tempBar.setLayoutX(passBar.getLayoutX() + passBar.getPrefWidth());
        }


        ProgressBar newFreeSpace = new ProgressBar();
        PaneParentDyamic.getChildren().add(newFreeSpace);
        newFreeSpace.setLayoutX(PaneParentDyamic.getPrefWidth() - widthFreSpace);
        newFreeSpace.setLayoutY(0);
        newFreeSpace.setProgress(0);
        newFreeSpace.setPrefWidth(widthFreSpace);
        newFreeSpace.setPrefHeight(PaneParentDyamic.getPrefHeight());


    }


}
