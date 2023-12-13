package model;

import framework.Clock;
import framework.Event;
import framework.EventList;
import distributions.ContinuousGenerator;
import distributions.Negexp;

import java.util.Random;

public class Arrival {
    private ContinuousGenerator generator;
    private Random random;
    private EventList eventList;

    public Arrival(ContinuousGenerator g, EventList tl) {
        this.generator = g;
        this.eventList = tl;
    }

    public void generateNextEvent() {
        double eventTime = Clock.getInstance().getClock() + generator.sample();
        EventType type = randomArrival();
        Event arrivalEvent = new Event(eventTime, type);
        eventList.addToEventList(arrivalEvent);
    }

    public EventType randomArrival() {
        random = new Random();
        int rand = random.nextInt(1,8);
        return switch (rand) {
            case 1 -> EventType.ARR_NURSE;
            case 2 -> EventType.ARR_DOCTOR;
            case 3 -> EventType.ARR_XRAY;
            case 4 -> EventType.ARR_MRI;
            case 5 -> EventType.ARR_BLOOD;
            case 6 -> EventType.ARR_EKG;
            default -> EventType.ARR_RECEP;
        };
    }

    public static void main(String[] args) {
        EventList eventList = new EventList();
        Arrival arrivalProcess = new Arrival(new Negexp(10), eventList);

        for (int i = 0; i < 10; i++) {
            arrivalProcess.generateNextEvent();

        }

    }
}
