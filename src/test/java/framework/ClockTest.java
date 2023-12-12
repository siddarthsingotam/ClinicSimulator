package framework;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClockTest {

    @Test
    void getInstance_createsNewInstance_whenCalledFirstTime() {
        Clock clock = Clock.getInstance();
        assertNotNull(clock);
    }

    @Test
    void getInstance_returnsSameInstance_whenCalledMultipleTimes() {
        Clock clock1 = Clock.getInstance();
        Clock clock2 = Clock.getInstance();
        assertSame(clock1, clock2);
    }

    @Test
    void setClock_updatesClockValue() {
        Clock clock = Clock.getInstance();
        clock.setClock(10.5);
        assertEquals(10.5, clock.getClock());
    }


    @Test
    void getClock_returnsSetValue_whenSet() {
        Clock clock = Clock.getInstance();
        clock.setClock(20.5);
        assertEquals(20.5, clock.getClock());
    }
}
