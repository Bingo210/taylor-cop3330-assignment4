module ucf.assignments {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens ucf.assignments to javafx.fxml;
    exports ucf.assignments;
}