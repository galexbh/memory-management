package com.unah.memorymanagement.controller;

import com.unah.memorymanagement.utils.ColleaguesUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ColleaguesController implements Initializable {

    @FXML
    private Label statusProcessMade;
    @FXML
    private Label statusMemorySize;
    @FXML
    private TextField txtProcessSize;
    @FXML
    private TextField txtSpaceMemory;

    ColleaguesUtils.Buddy buddy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusMemorySize.setText("Undefined: size");
    }

    public void setMemorySize() {
        int memorySize = Integer.parseInt(txtSpaceMemory.getText());
        buddy = new ColleaguesUtils.Buddy(memorySize);
        statusMemorySize.setText("Size: " + txtSpaceMemory.getText() + "MB");

    }

    public void addProcess() {
        int process = Integer.parseInt(txtProcessSize.getText());
        buddy.allocate(process);
        statusProcessMade.setText(buddy.getAssignedResult());

    }

    public void quitProcess() {
        int process = Integer.parseInt(txtProcessSize.getText());
        buddy.deallocate(process);
        statusProcessMade.setText(buddy.getDesignatedResult());

    }


}
