module com.example.klinicsim {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports view to javafx.graphics;
    exports controller to javafx.fxml;

    opens controller to javafx.fxml;
}