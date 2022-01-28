module com.unah.memorymanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.unah.memorymanagement to javafx.fxml;
    exports com.unah.memorymanagement;
    opens com.unah.memorymanagement.controller to javafx.fxml;
    exports com.unah.memorymanagement.controller;
}