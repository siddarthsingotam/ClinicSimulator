package framework;

import model.EventType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventListTest {

    @Test
    void addToEventList_increasesSize() {
        EventList eventList = new EventList();
        int initialSize = eventList.getSize();
        eventList.addToEventList(new Event(1.0, EventType.ARR_RECEP));
        int finalSize = eventList.getSize();

        assertTrue(finalSize > initialSize);
    }

    @Test
    void removeFromEventList_decreasesSize() {
        EventList eventList = new EventList();
        eventList.addToEventList(new Event(1.0, EventType.ARR_RECEP));
        int initialSize = eventList.getSize();
        eventList.removeFromEventList();
        int finalSize = eventList.getSize();

        assertTrue(finalSize < initialSize);
    }

    @Test
    void getNextEventTime_returnsZero_whenListIsEmpty() {
        EventList eventList = new EventList();
        double nextEventTime = eventList.getNextEventTime();

        assertEquals(0, nextEventTime);
    }

    @Test
    void getNextEventTime_returnsCorrectTime_whenListIsNotEmpty() {
        EventList eventList = new EventList();
        eventList.addToEventList(new Event(1.0, EventType.ARR_RECEP));
        double nextEventTime = eventList.getNextEventTime();

        assertEquals(1.0, nextEventTime);
    }
}