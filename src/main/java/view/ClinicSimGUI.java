package view;

import controller.ClinicSimController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class ClinicSimGUI extends Application {


    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Clinic Simulator");
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/version4OfGuiNoLines.fxml"));
       Parent root = fxmlLoader.load();
         stage.setScene(new Scene(root));
            stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
