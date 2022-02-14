package com.unah.memorymanagement.controller;

import com.unah.memorymanagement.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public Button btnColleague;
    @FXML
    public Button btnDynamic;
    @FXML
    public Button Fixed;
    @FXML
    public Button btnFixedSize;

    private final String[] listForm = {
            "FixedPartition-view.fxml",
            "test.fxml",
            "Dynamic-view.fxml",
            "colleagues-view.fxml"
    };

    public void onOpenForm(ActionEvent actionEvent) throws IOException {
        Button button = (Button)actionEvent.getSource();
        int value = Integer.parseInt(button.getText());

        FXMLLoader fxmlLoader = null;

        switch (value){
            case 1 -> fxmlLoader = new FXMLLoader(MainApplication.class.getResource(listForm[0]));
            case 2 -> fxmlLoader = new FXMLLoader(MainApplication.class.getResource(listForm[1]));
            case 3 -> fxmlLoader = new FXMLLoader(MainApplication.class.getResource(listForm[2]));
            case 4 -> fxmlLoader = new FXMLLoader(MainApplication.class.getResource(listForm[3]));
        }

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Memory Management");
        stage.setScene(new Scene(root));
        stage.show();
    }
}

