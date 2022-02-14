package com.unah.memorymanagement.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

public class Dynamic {
    @FXML
    private Pane PaneParent;

    @FXML
    private void prueba() {
        ObservableList<Node> prueba = PaneParent.getChildren();
        double tempX;
        double tempY;
        for (Node prueb : prueba) {
            ProgressBar temp = (ProgressBar) prueb;
            if (temp.getProgress() == 0){

            }

        }

    }


}
