package framework;


import model.EventType;
import model.ServicePoint;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    @Test
    void run_simulationStops_whenClockExceedsSimulationTime() {
        Engine engine = new TestEngine();
        engine.setSimulationTime(10.0);
        Clock.getInstance().setClock(11.0);

        engine.run();

        assertFalse(engine.simulate());
    }

    @Test
    void currentTime_returnsNextEventTime() {
        Engine engine = new TestEngine();
        engine.eventList.addToEventList(new Event(1.0, EventType.ARR_RECEP));

        double currentTime = engine.currentTime();

        assertEquals(1.0, currentTime);
    }


    // fix time issue here - problem with clock updating
    @Test
    void runBEvents_runsAllEventsAtCurrentTime() {
        Engine engine = new TestEngine();
        engine.eventList.addToEventList(new Event(1.0, EventType.ARR_RECEP));
        engine.eventList.addToEventList(new Event(1.0, EventType.ARR_RECEP));
        Clock.getInstance().setClock(1.0);

        engine.runBEvents();

        assertEquals(2.0, engine.currentTime());
    }
}

class TestEngine extends Engine {

    @Override
    public ServicePoint[] getServicePoint() {
        return new ServicePoint[0];
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void runEvent(Event e) {
    }

    @Override
    protected void tryCEvents() {
    }

    @Override
    protected void results() {
    }
}

