package com.unah.memorymanagement.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class FixedDifferentPartition implements Initializable {

    @FXML
    private Pane PaneParenFixedDifferent;

    @FXML
    private TextField fixedDifferentPartitionText;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<Node> allBars = PaneParenFixedDifferent.getChildren();
        for (Node bar: allBars){
            ProgressBar tempBar = (ProgressBar) bar;
            tempBar.setOnMouseClicked((event) -> clearMemory(tempBar));
        }
    }
    private void clearMemory(ProgressBar bar){
        bar.setProgress(0);
    }




}
