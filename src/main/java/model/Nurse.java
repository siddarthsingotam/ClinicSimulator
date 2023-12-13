package model;

import framework.Clock;
import framework.Event;
import framework.EventList;
import javafx.scene.control.Label;
import model.EventType;
import model.ServicePoint;
import distributions.ContinuousGenerator;
import distributions.Normal;
import controller.ClinicSimController;

public class Nurse extends ServicePoint {

        private String name;
        private EventType eventTypeScheduled;

        public Nurse(String name, ContinuousGenerator g, EventList tl, EventType type,ClinicSimController controller) {
            super(g, tl,controller);
            this.name = name;
            this.eventTypeScheduled = type;
        }

    @Override
    protected Label getQueueLabel() {
        switch (name) {
            case "Simu.model.Nurse 1":
                return controller.getNurse1QLabel();
            case "Simu.model.Nurse 2":
                return controller.getNurse2QLabel();
            case "Simu.model.Nurse 3":
                return controller.getNurse3QLabel();
            case "Simu.model.Nurse 4":
                return controller.getNurse4QLabel();
            default:
                return null; // Or throw an exception
        }
    } @Override
    protected Label getServicedLabel() {
        switch (name) {
            case "Simu.model.Nurse 1":
                return controller.getNurse1Srvd1();
            case "Simu.model.Nurse 2":
                return controller.getNurse1Srvd2();
            case "Simu.model.Nurse 3":
                return controller.getNurse1Srvd3();
            case "Simu.model.Nurse 4":
                return controller.getNurse1Srvd4();
            default:
                return null; // Or throw an exception
        }
    }

        @Override
        public void beginService() {
            super.beginService();
            System.out.printf("%sStarting service %s for the patient #%d%s", ServicePoint.GREEN, name, queue.peek().getPatientCount(), ServicePoint.WHITE);

            reserved = true;
            double serviceTime = new Normal(30, 5).sample();
            eventList.addToEventList(new Event(Clock.getInstance().getClock() + serviceTime,eventTypeScheduled));
        }
}
