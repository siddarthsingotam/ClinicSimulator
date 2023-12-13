package framework;


import model.EventType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// Testing the Event class with type Arrival Reception case
class EventTest {

    @Test
    void getType_returnsCorrectType() {
        EventType type = EventType.ARR_RECEP;
        Event event = new Event(1.0, type);

        assertEquals(type, event.getType());
    }

    @Test
    void getTime_returnsCorrectTime() {
        EventType type = EventType.ARR_RECEP;
        Event event = new Event(1.0, type);

        assertEquals(1.0, event.getTime());
    }

    @Test
    void setTime_updatesTime() {
        EventType type = EventType.ARR_RECEP;
        Event event = new Event(1.0, type);

        event.setTime(2.0);

        assertEquals(2.0, event.getTime());
    }

    @Test
    void setType_updatesType() {
        EventType type = EventType.ARR_RECEP;
        Event event = new Event(1.0, type);

        EventType newType = EventType.DEP_RECEP;
        event.setType(newType);

        assertEquals(newType, event.getType());
    }

    @Test
    void compareTo_returnsNegative_whenEventIsEarlier() {
        EventType type = EventType.ARR_RECEP;
        Event event1 = new Event(1.0, type);
        Event event2 = new Event(2.0, type);

        assertTrue(event1.compareTo(event2) < 0);
    }

    @Test
    void compareTo_returnsPositive_whenEventIsLater() {
        EventType type = EventType.ARR_RECEP;
        Event event1 = new Event(2.0, type);
        Event event2 = new Event(1.0, type);

        assertTrue(event1.compareTo(event2) > 0);
    }

    @Test
    void compareTo_returnsZero_whenEventsAreAtSameTime() {
        EventType type = EventType.ARR_RECEP;
        Event event1 = new Event(1.0, type);
        Event event2 = new Event(1.0, type);

        assertEquals(0, event1.compareTo(event2));
    }
}
