package framework;

import java.util.PriorityQueue;

/**
 * Stores the events by type into a priority queue with add and remove methods.
 * 
 *
 */

public class EventList {
    private PriorityQueue<Event> eventList;

    // Constructor
    public EventList(){
        eventList = new PriorityQueue<>();
    }

    // Methods
    // This adds an event to the eventList
    public void addToEventList(Event e){
        System.out.printf(" Adding to the event list %s %.2f", e.getType(), e.getTime());
        eventList.add(e);
    }
    public double getNextEventTime() {
        if (eventList.isEmpty())
            return 0;
        return eventList.peek().getTime();
    }

    public Event removeFromEventList(){
        // check if the list is Empty
        if(eventList.isEmpty()){
            return null;
        }else
            System.out.printf(" Removing from the event list %s %.2f", eventList.peek().getType(), eventList.peek().getTime());
            return eventList.remove();
    }
}
