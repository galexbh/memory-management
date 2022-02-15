package com.unah.memorymanagement.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class FixedDifferentPartitionController implements Initializable {

    @FXML
    private Pane PaneParenFixedDifferent;

    @FXML
    private TextField fixedDifferentPartitionText;

    public static ObservableList<ObservableList> processQueue = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < PaneParenFixedDifferent.getChildren().size(); i++) {
            ObservableList<Integer> tempList = FXCollections.observableArrayList();
            processQueue.add(tempList);
        }
        System.out.println(processQueue.size());
        ObservableList<Node> allBars = PaneParenFixedDifferent.getChildren();
        for (Node bar : allBars) {
            ProgressBar tempBar = (ProgressBar) bar;
            tempBar.setOnMouseClicked((event) -> clearMemory(tempBar));
        }
    }

    private void clearMemory(ProgressBar bar) {

        bar.setProgress(0);
        int tempPosition = PaneParenFixedDifferent.getChildren().indexOf(bar);


        if (processQueue.get(tempPosition).size() != 0) {
            double tempWidth = (double) processQueue.get(tempPosition).get(0);
            bar.setProgress(tempWidth / bar.getPrefWidth());

            processQueue.get(tempPosition).remove(0);
        }


    }

    @FXML
    private void addFixedDifferentPartition() {

        try {
            double tamMemory = Integer.parseInt(fixedDifferentPartitionText.getText());
            tamMemory = tamMemory * 15;
            double temp = -1;
            ProgressBar auxBar = new ProgressBar();
            for (Node bar : PaneParenFixedDifferent.getChildren()) {

                ProgressBar tempBar = (ProgressBar) bar;
                double widthTemp = tempBar.getPrefWidth();
                if ((tamMemory > widthTemp)) {
                    continue;
                }

                if ((tamMemory <= widthTemp)) {
                    if (temp == -1) {
                        auxBar = tempBar;
                        temp = 1;
                    } else if (widthTemp < auxBar.getWidth()) {
                        auxBar = tempBar;
                    }
                }
            }
            if (temp != -1) {
                if (auxBar.getProgress() != 0) {
                    int tempPosition = PaneParenFixedDifferent.getChildren().indexOf(auxBar);
                    processQueue.get(tempPosition).add(tamMemory);

                } else {
                    auxBar.setProgress(tamMemory / auxBar.getPrefWidth());
                }

            }
        } catch (Exception e) {
            //Error
        }
    }


}
