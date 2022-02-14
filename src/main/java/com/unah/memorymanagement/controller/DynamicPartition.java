package com.unah.memorymanagement.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private TextField BestFitText;


    @FXML
    private void clickBestFit(){

        ProgressBar[] bars = {BestFitSpace1, BestFitSpace2, BestFitSpace3, BestFitSpace4, BestFitSpace5};

        double tamMemory = 0;
        try {
            tamMemory = Integer.parseInt(BestFitText.getText());

         double temp = -1;
            for (int i = 0; i < bars.length; i++){
                double widthTemp = bars[i].getPrefWidth();
                if ((tamMemory*15 > widthTemp) || (bars[i].getProgress() != 0)){continue;}


                if ((tamMemory*15 <= widthTemp  )){
                    if (temp == -1){
                        temp = widthTemp;
                    } else if (widthTemp < temp){
                        temp = widthTemp;
                    }
                    }
                }
            for (ProgressBar bar: bars){
                if (temp == bar.getPrefWidth()){

                    bar.setProgress(tamMemory/(bar.getWidth()/15));
                }
            }

            //  Block of code to try
        }
        catch(Exception e) {
           System.out.println("Error");
        }

    }
    @FXML
    private  void clearProgressBar(){
        ProgressBar[] bars = {BestFitSpace1, BestFitSpace2, BestFitSpace3, BestFitSpace4, BestFitSpace5};

        for (ProgressBar bar: bars){
            bar.setProgress(0);
        }
    }

    @FXML
    private ProgressBar FirstFit1;
    @FXML
    private ProgressBar FirstFit2;
    @FXML
    private ProgressBar FirstFit3;
    @FXML
    private ProgressBar FirstFit4;
    @FXML
    private ProgressBar FirstFit5;
    @FXML
    private TextField FirstFitText;

    @FXML
    private void clickFirstFit() {
        ProgressBar[] bars = {FirstFit1, FirstFit2, FirstFit3, FirstFit4, FirstFit5};
        double tamMemory = 0;
        try {
            for(ProgressBar bar : bars) {
                if (bar.getProgress() != 0) {
                    continue;
                }
                tamMemory = Integer.parseInt(FirstFitText.getText());
                if (tamMemory*15 <= bar.getWidth()){
                    bar.setProgress(tamMemory/(bar.getWidth()/15));
                    break;
                }

        }
    }catch(Exception e){
        //Crear alerta de error

    }
    }
}
