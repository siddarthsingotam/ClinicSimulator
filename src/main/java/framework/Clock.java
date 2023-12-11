package framework;

public class Clock {
    private static Clock instance;
    private double clockTimer;

    private Clock() {
    }

    public static Clock getInstance() {
        if (instance == null)
            instance = new Clock();
        return instance;
    }

    public void setClock(double clock) {
        this.clockTimer = clock;
    }

    public double getClock() {
        return clockTimer;
    }
}
