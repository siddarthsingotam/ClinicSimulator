package controller;

import framework.Engine;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import model.MyEngine;
import view.ClinicSimGUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Locale;
import javafx.scene.control.Label;
import java.util.Map;
import java.util.HashMap;
import model.ServicePoint;
import javafx.scene.paint.Color;
import framework.Clock;

public class ClinicSimController {
    private Map<Rectangle, ServicePoint> rectangleToServicePointMap;
    @FXML
    private Label Reception, Nurses, GeneralPractitioners, Laboratory;

    @FXML
    private Rectangle reception, nurse1, GP1, Xray, nurse3, nurse2, nurse4, GP2, GP3, EKG, MRI, Blood;
    @FXML
    private Label receptionsrvd,nurse1srvd1, nurse1srvd2, nurse1srvd3, nurse1srvd4, gpsrvd, gpsrvd1, gpsrvd2,xraysrvd,ekgsrvd,mrisrvd,bloodsrvd;

    @FXML
    private Ellipse nurse1Q, nurse2Q, nurse3Q, nurse4Q, GP1Q, GP2Q, GP3Q, XrayQ, EKGQ, MRIQ, BloodQ;
    @FXML
    private Label  receptionQLabel ,nurse1QLabel, nurse2QLabel, nurse3QLabel, nurse4QLabel, GP1QLabel, GP2QLabel, GP3QLabel, XrayQLabel, EKGQLabel, MRIQLabel, BloodQLabel,clocktime;

    @FXML
    private Button start;
    @FXML
    private TextField delaytime, simtime;


    @FXML
    public void initialize() {
        rectangleToServicePointMap = new HashMap<>();

        ClinicSimController controller = this;
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Thread(()->{
                Engine engine = new MyEngine(controller);
                rectangleToServicePointMap.put(reception, engine.getServicePoint()[0]);
                rectangleToServicePointMap.put(nurse1, engine.getServicePoint()[1]);
                rectangleToServicePointMap.put(nurse2, engine.getServicePoint()[2]);
                rectangleToServicePointMap.put(nurse3, engine.getServicePoint()[3]);
                rectangleToServicePointMap.put(nurse4, engine.getServicePoint()[4]);
                rectangleToServicePointMap.put(GP1, engine.getServicePoint()[5]);
                rectangleToServicePointMap.put(GP2, engine.getServicePoint()[6]);
                rectangleToServicePointMap.put(GP3, engine.getServicePoint()[7]);
                rectangleToServicePointMap.put(Xray, engine.getServicePoint()[8]);
                rectangleToServicePointMap.put(Blood, engine.getServicePoint()[9]);
                rectangleToServicePointMap.put(EKG, engine.getServicePoint()[10]);
                rectangleToServicePointMap.put(MRI, engine.getServicePoint()[11]);
                engine.setSimulationTime(Double.parseDouble(simtime.getText()));
                engine.run();
        }).start();
    }});}
    @FXML
    public void updateRectangleColor(ServicePoint servicePoint, boolean isServicing) {
        Platform.runLater(() -> {
            for (Map.Entry<Rectangle, ServicePoint> entry : rectangleToServicePointMap.entrySet()) {
                if (entry.getValue().equals(servicePoint)) {
                    if (isServicing) {
                        entry.getKey().setFill(Color.GREEN);
                    } else {
                        entry.getKey().setFill(Color.WHITE);
                    }
                    break;
                }
            }
        });
    }
    @FXML
    public long getDelayTime(){
        if(delaytime.getText().isEmpty()){
            return (long) 0.0;
        }
        else{
            return Long.parseLong(delaytime.getText());
        }
    }
    @FXML
    public void updateClock(){
        Platform.runLater(()->{
            clocktime.setText(String.format(Locale.US, "%.2f", Clock.getInstance().getClock()));
        });
    }
    @FXML
    public void updateQueueLabel(ServicePoint servicePoint, Label label) {
        Platform.runLater(() -> {
            int queueSize = servicePoint.getQueueSize();
            label.setText(String.valueOf(queueSize));
        });
    }
    @FXML
    public void updateserviced(ServicePoint servicePoint, Label label) {
        Platform.runLater(() -> {
            int serviced = servicePoint.getPatientServiced();
            label.setText(String.valueOf(serviced));
        });
    }
    @FXML
    public Label getReceptionSrvd() {
        return receptionsrvd;
    }

    @FXML
    public Label getNurse1Srvd1() {
        return nurse1srvd1;
    }

    @FXML
    public Label getNurse1Srvd2() {
        return nurse1srvd2;
    }

    @FXML
    public Label getNurse1Srvd3() {
        return nurse1srvd3;
    }

    @FXML
    public Label getNurse1Srvd4() {
        return nurse1srvd4;
    }

    @FXML
    public Label getGpsrvd() {
        return gpsrvd;
    }

    @FXML
    public Label getGpsrvd1() {
        return gpsrvd1;
    }

    @FXML
    public Label getGpsrvd2() {
        return gpsrvd2;
    }


    @FXML
    public Label getXraysrvd() {
        return xraysrvd;
    }

    @FXML
    public Label getEkgsrvd() {
        return ekgsrvd;
    }

    @FXML
    public Label getMrisrvd() {
        return mrisrvd;
    }

    @FXML
    public Label getBloodsrvd() {
        return bloodsrvd;
    }
    @FXML
    public Label getBloodQLabel() {
        return BloodQLabel;
    }
    @FXML
    public Label getEKGQLabel() {
        return EKGQLabel;
    }
    @FXML
    public Label getMRIQLabel() {
        return MRIQLabel;
    }
    @FXML
    public Label getXrayQLabel() {
        return XrayQLabel;
    }
    @FXML
    public Label getGP1QLabel() {
        return GP1QLabel;
    }
    @FXML
    public Label getGP2QLabel() {
        return GP2QLabel;
    }
    @FXML
    public Label getGP3QLabel() {
        return GP3QLabel;
    }
    @FXML
    public Label getReceptionQLabel() {
        return receptionQLabel;
    }
    @FXML

    public Label getNurse1QLabel() {
        return nurse1QLabel;
    }
    @FXML
    public Label getNurse2QLabel() {
        return nurse2QLabel;
    }
    @FXML

    public Label getNurse3QLabel() {
        return nurse3QLabel;
    }
    @FXML

    public Label getNurse4QLabel() {
        return nurse4QLabel;
    }


    public static void main(String[] args) {
        ClinicSimGUI.launch(ClinicSimGUI.class);
    }
}
