package model;

import controller.ClinicSimController;
import distributions.ContinuousGenerator;
import distributions.Negexp;
import framework.EventList;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServicePointTest {

    @Test
    void addToQueue_increasesQueueSize() {
        ContinuousGenerator generator = new Negexp(10);
        EventList eventList = new EventList();
        ClinicSimController controller = new ClinicSimController();
        ServicePoint servicePoint = new ServicePoint(generator, eventList, controller) {
            @Override
            protected Label getServicedLabel() {
                return null;
            }

            @Override
            protected Label getQueueLabel() {
                return null;
            }
        };

        int initialSize = servicePoint.getQueueSize();
        servicePoint.addToQueue(new Patient());
        int finalSize = servicePoint.getQueueSize();

        assertTrue(finalSize > initialSize);
    }

    @Test
    void removeFromQueue_decreasesQueueSize() {
        ContinuousGenerator generator = new Negexp(10);
        EventList eventList = new EventList();
        ClinicSimController controller = new ClinicSimController();
        ServicePoint servicePoint = new ServicePoint(generator, eventList, controller) {
            @Override
            protected Label getServicedLabel() {
                return null;
            }

            @Override
            protected Label getQueueLabel() {
                return null;
            }
            @Override
            public Patient removeFromQueue() {
                if (queue.size() > 0) {
                    Patient a = queue.removeLast();
                    patientServiced++;
                    reserved = false;

                    return a;
                } else
                    return null;
            }
        };

        servicePoint.addToQueue(new Patient());
        servicePoint.removeFromQueue();
        int finalSize = servicePoint.getQueueSize();

        assertTrue(finalSize == 0);
    }

    @Test
    void beginService_setsIsServicingToTrue() {
        ContinuousGenerator generator = new Negexp(10);
        EventList eventList = new EventList();
        ClinicSimController controller = new ClinicSimController();
        ServicePoint servicePoint = new ServicePoint(generator, eventList, controller) {
            @Override
            protected Label getServicedLabel() {
                return null;
            }

            @Override
            protected Label getQueueLabel() {
                return null;
            }
            @Override
            public void beginService() {
                setServicing(true);
            }
        };

        servicePoint.beginService();

        assertTrue(servicePoint.isServicing());
    }

}
