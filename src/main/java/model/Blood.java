package model;

import framework.Clock;
import framework.Event;
import framework.EventList;
import distributions.ContinuousGenerator;
import controller.ClinicSimController;
import javafx.scene.control.Label;

public class Blood extends ServicePoint {
        private String name;
        private EventType eventTypeScheduled;

        public Blood(String name, ContinuousGenerator g, EventList tl, EventType type, ClinicSimController controller) {
            super(g, tl,controller);
            this.name = name;
            this.eventTypeScheduled = type;
        }

    @Override
    protected Label getServicedLabel() {
        return controller.getBloodsrvd();
    }

    @Override
        protected Label getQueueLabel() {
            return controller.getBloodQLabel();
        }


        @Override
        public void beginService() {
            super.beginService();
            System.out.printf("%sStarting service %s for the patient #%d%s", ServicePoint.GREEN, name, queue.peek().getPatientCount(), ServicePoint.WHITE);

            reserved = true;
            double serviceTime = generator.sample();
            eventList.addToEventList(new Event(Clock.getInstance().getClock() + serviceTime,eventTypeScheduled));
        }


}
