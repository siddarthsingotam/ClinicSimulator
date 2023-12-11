package model;

import framework.Clock;

import java.util.Random;

public class Patient {
    private Random random = new Random();
    private double arrivalTime;
    private double removalTime;
    private static double serviceTimeSum;
    private int patientCount;
    private static int i = 1;
    private boolean labVisit;


    public Patient() {
        patientCount = i++;
        arrivalTime = Clock.getInstance().getClock();
        this.labVisit = false;
        System.out.printf("New Simu.model.Patient #%d arrived at %.2f", patientCount, arrivalTime);
    }

    public boolean getLabAppointment(){
        return random.nextBoolean();
    }

    public boolean getLabVisit(){
        return this.labVisit;
    }
    public void wasLab(){
        this.labVisit = true;
    }

    public boolean getDoctorAppointment() {
        return random.nextBoolean();
    }

    // Getters and Setters
    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getRemovalTime() {
        return removalTime;
    }

    public void setRemovalTime(double removalTime) {
        this.removalTime = removalTime;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }

    // Method for reporting results and calculating service time
    public void reportResults() {
        serviceTimeSum += (removalTime - arrivalTime);
        double meanServiceTime = serviceTimeSum / patientCount;   // id is the number of Patients serviced

        System.out.printf("Customer #%d has been serviced. Customer arrived: %.2f removed: %.2f stayed: %.2f mean %.2f",
                patientCount, arrivalTime, removalTime, (removalTime-arrivalTime), meanServiceTime);
    }
}

