package com.unah.memorymanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class DynamicPartition {
    @FXML
    private ProgressBar BestFitSpace1;
    @FXML
    private ProgressBar BestFitSpace2;
    @FXML
    private ProgressBar BestFitSpace3;
    @FXML
    private ProgressBar BestFitSpace4;
    @FXML
    private ProgressBar BestFitSpace5;
    @FXML
    private TextField MemoryQuantity;


    @FXML
    private void mouseClick(){
        ProgressBar[] pro = {BestFitSpace1, BestFitSpace2, BestFitSpace3, BestFitSpace4, BestFitSpace5};
       String value = MemoryQuantity.getText();
       double val = Integer.parseInt(value);
       pro[2].setProgress(val/10);
    }


}
