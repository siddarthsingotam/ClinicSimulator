package model;

import framework.Clock;
import framework.Event;
import framework.EventList;
import distributions.ContinuousGenerator;
import distributions.Normal;
import controller.ClinicSimController;
import javafx.scene.control.Label;

public class Doctor extends ServicePoint {

        private String name;
        private EventType eventTypeScheduled;

        public Doctor(String name, ContinuousGenerator g, EventList tl, EventType type,ClinicSimController controller) {
            super(g, tl,controller);
            this.name = name;
            this.eventTypeScheduled = type;
        }

    @Override
    protected Label getQueueLabel() {
        switch (name) {
            case "Simu.model.Doctor 1":
                return controller.getGP1QLabel();
            case "Simu.model.Doctor 2":
                return controller.getGP2QLabel();
            case "Simu.model.Doctor 3":
                return controller.getGP3QLabel();
            default:
                return null; // Or throw an exception
        }
    }
    @Override
    protected Label getServicedLabel() {
        switch (name) {
            case "Simu.model.Doctor 1":
                return controller.getGpsrvd();
            case "Simu.model.Doctor 2":
                return controller.getGpsrvd1();
            case "Simu.model.Doctor 3":
                return controller.getGpsrvd2();
            default:
                return null; // Or throw an exception
        }
    }
        @Override
        public void beginService() {
            super.beginService();
            System.out.printf("%sStarting service %s for the patient #%d%s", ServicePoint.GREEN, name, queue.peek().getPatientCount(), ServicePoint.WHITE);

            reserved = true;
            double serviceTime = new Normal(30, 10).sample();
            eventList.addToEventList(new Event(Clock.getInstance().getClock() + serviceTime,eventTypeScheduled));
        }
}
