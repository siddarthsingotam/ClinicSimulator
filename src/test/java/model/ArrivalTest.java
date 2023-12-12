package model;

import distributions.ContinuousGenerator;
import distributions.Negexp;
import framework.EventList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrivalTest {

    @Test
    void generateNextEvent_addsEventToEventList() {
        ContinuousGenerator generator = new Negexp(10);
        EventList eventList = new EventList();
        Arrival arrival = new Arrival(generator, eventList);

        int initialSize = eventList.getSize();
        arrival.generateNextEvent();
        int finalSize = eventList.getSize();

        assertTrue(finalSize > initialSize);
    }

    @Test
    void randomArrival_returnsExpectedEventType() {
        ContinuousGenerator generator = new Negexp(10);
        EventList eventList = new EventList();
        Arrival arrival = new Arrival(generator, eventList);

        EventType result = arrival.randomArrival();

        assertTrue(result instanceof EventType);
    }
}
