package model;

import controller.ClinicSimController;
import framework.Clock;
import framework.Event;
import framework.EventList;
import javafx.scene.control.Label;
import model.ServicePoint;
import distributions.ContinuousGenerator;
import distributions.Normal;
import controller.ClinicSimController;
public class XRay extends ServicePoint {
        private String name;
        private EventType eventTypeScheduled;

        public XRay(String name, ContinuousGenerator g, EventList tl, EventType type, ClinicSimController controller) {
            super(g, tl,controller);
            this.name = name;
            this.eventTypeScheduled = type;
        }
    @Override
    protected Label getQueueLabel() {
        return controller.getXrayQLabel();
    }
    @Override
    protected Label getServicedLabel() {
        return controller.getXraysrvd();
    }

        @Override
        public void beginService() {
            super.beginService();
            System.out.printf("%sStarting service %s for the patient #%d%s", ServicePoint.GREEN, name, queue.peek().getPatientCount(), ServicePoint.WHITE);

            reserved = true;
            double serviceTime = new Normal(20, 10).sample();
            eventList.addToEventList(new Event(Clock.getInstance().getClock() + serviceTime,eventTypeScheduled));
        }
}
