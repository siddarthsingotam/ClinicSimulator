package framework;

public class Event implements Comparable<Event>{

    private double time;
    private IEventType type;

    // Constructor
    public Event(double time, IEventType type){
        this.time = time;
        this.type = type;
    }

    // methods

    // getters
    public IEventType getType() {
        return type;
    }

    public double getTime() {
        return time;
    }

    // setters

    public void setTime(double time) {
        this.time = time;
    }

    public void setType(IEventType type) {
        this.type = type;
    }

    @Override
    public int compareTo(Event o){
        if(time < o.time){
            return -1;
        } else if (time > o.time) {
            return 1;
        } else
            return 0;
    }
}
