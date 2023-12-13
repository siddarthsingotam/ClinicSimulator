package model;

import framework.Clock;
import framework.Event;
import framework.EventList;
import distributions.ContinuousGenerator;
import distributions.Normal;
import controller.ClinicSimController;
import javafx.scene.control.Label;

public class EKG extends ServicePoint {
        private String name;
        private EventType eventTypeScheduled;

        public EKG(String name, ContinuousGenerator g, EventList tl, EventType type, ClinicSimController controller) {
            super(g, tl,controller);
            this.name = name;
            this.eventTypeScheduled = type;
        }

        @Override
        protected Label getQueueLabel() {
            return controller.getEKGQLabel();
        }
    @Override
    protected Label getServicedLabel() {
        return controller.getEkgsrvd();
    }
        @Override
        public void beginService() {
            super.beginService();
            System.out.printf("%sStarting service %s for the patient #%d%s", ServicePoint.GREEN, name, queue.peek().getPatientCount(), ServicePoint.WHITE);

            reserved = true;
            double serviceTime = new Normal(10, 5).sample();
            eventList.addToEventList(new Event(Clock.getInstance().getClock() + serviceTime,eventTypeScheduled));
        }
}
