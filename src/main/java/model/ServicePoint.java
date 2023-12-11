package model;

import controller.ClinicSimController;
import framework.EventList;
import distributions.ContinuousGenerator;
import javafx.scene.control.Label;

import java.util.LinkedList;

public abstract class ServicePoint { // Service points as abstract class to be inherited by Simu.model.Reception and GeneralPractitioner
    protected ClinicSimController controller;
        protected LinkedList<Patient> queue;
        protected ContinuousGenerator generator;
        protected static final String GREEN = "\033[0;32m";
        protected static final String WHITE = "\033[0;37m";
        protected EventList eventList;
        protected boolean isServicing;
        protected double serviceTimeSum;
        protected int patientServiced;
        protected boolean reserved = false;

        public ServicePoint(ContinuousGenerator g, EventList tl,ClinicSimController controller) { // Constructor
            this.controller = controller;
            this.generator = g;
            this.eventList = tl;

            this.serviceTimeSum = 0;
            this.patientServiced = 0;

            queue = new LinkedList<>();
        }

        public void addToQueue(Patient a) {
            queue.addFirst(a);
        } // Add patient to queue
    public boolean isServicing() {return isServicing;} // Check if service point is servicing

    public void setServicing(boolean isServicing) {
        this.isServicing = isServicing;
    }

        public Patient removeFromQueue() { // Remove patient from queue
            if (queue.size() > 0) {
                Patient a = queue.removeLast();
                patientServiced++;
                reserved = false;
                updateQueueLabel();
                return a;
            } else
                return null;
        }
        public int getQueueSize() {
            return queue.size();
        }
    private void updateQueueLabel() {
        if (controller != null) {
            controller.updateQueueLabel(this, getQueueLabel());
        }
    }
        protected abstract Label getQueueLabel();

        public void beginService() {
            setServicing(true);
            if (controller != null) {
                controller.updateRectangleColor(this, isServicing());
            }
        }

        public boolean isReserved() {
            return reserved;
        } // Check if service point is reserved

        public boolean isOnQueue() {
            return queue.size() > 0;
        } // Check if service point is on queue

    public void endService() {
        setServicing(false);
        if (controller != null) {
            controller.updateRectangleColor(this, isServicing());
        }
    }
        public int getPatientServiced() {
            return patientServiced;
        } // Get number of patients serviced

        public double getMeanServiceTime() {
            return serviceTimeSum / patientServiced;
        } // Get mean service time
}

