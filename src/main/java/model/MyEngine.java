package model;

import controller.ClinicSimController;
import framework.Clock;
import framework.Engine;
import framework.Event;
import distributions.Negexp;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.Random;

/* Simulate a queueing system with one service point and one queue.
 * The service time is normally distributed with mean 10.
 * The interarrival time is exponentially distributed with mean 15.
 * The simulation runs until the number of arrivals exceeds 1000.
 * The program prints the average waiting time.
 */
public class MyEngine extends Engine {
    private static final String RED = "\033[0;31m"; // https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
    private static final String WHITE = "\033[0;37m";
    private ClinicSimController controller;
    private Arrival arrivalProcess;
    private ServicePoint[] servicePoint;
    private Random random;
    private static int patientsServiced = 0;

    @Override
    public ServicePoint[] getServicePoint() {
        return servicePoint;

    }

    public MyEngine(ClinicSimController controller){
        super();
        this.controller = controller;
        arrivalProcess = new Arrival(new Negexp(controller.getArrivalDistrb()), eventList);
        System.out.println(controller.getArrivalDistrb());

        servicePoint = new ServicePoint[12];
        servicePoint[0] = new Reception("Simu.model.Reception", new Negexp(10), eventList, EventType.DEP_RECEP,controller);
        servicePoint[1] = new Nurse("Simu.model.Nurse 1", new Negexp(10), eventList, EventType.DEP_NURSE1,controller);
        servicePoint[2] = new Nurse("Simu.model.Nurse 2", new Negexp(10), eventList, EventType.DEP_NURSE2,controller);
        servicePoint[3] = new Nurse("Simu.model.Nurse 3", new Negexp(10), eventList, EventType.DEP_NURSE3,controller);
        servicePoint[4] = new Nurse("Simu.model.Nurse 4", new Negexp(10), eventList, EventType.DEP_NURSE4,controller);
        servicePoint[5] = new Doctor("Simu.model.Doctor 1", new Negexp(10), eventList, EventType.DEP_DOCTOR1,controller);
        servicePoint[6] = new Doctor("Simu.model.Doctor 2", new Negexp(10), eventList, EventType.DEP_DOCTOR2,controller);
        servicePoint[7] = new Doctor("Simu.model.Doctor 3", new Negexp(10), eventList, EventType.DEP_DOCTOR3,controller);
        servicePoint[8] = new XRay("Simu.model.XRay", new Negexp(10), eventList, EventType.DEP_XRAY,controller);
        servicePoint[9] = new Blood("Simu.model.Blood", new Negexp(10), eventList, EventType.DEP_BLOOD,controller);
        servicePoint[10] = new EKG("Simu.model.EKG", new Negexp(10), eventList, EventType.DEP_EKG,controller);
        servicePoint[11] = new MRI("Simu.model.MRI", new Negexp(10), eventList, EventType.DEP_MRI,controller);
    }

    protected void initialize() {
        arrivalProcess.generateNextEvent();
    }

    protected void runEvent(Event e) {
        Patient a;



            try {
                // Sleep for 1000 milliseconds (1 second)
                Thread.sleep(controller.getDelayTime());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }





        switch ((EventType)e.getType()) {
            case ARR_RECEP:
                servicePoint[0].addToQueue(new Patient());
                arrivalProcess.generateNextEvent();
                break;

            case ARR_NURSE:
                int countNurse = 0;
                for(int i = 1; i < 5; i++) {
                    if (!servicePoint[i].isReserved()) {
                            servicePoint[i].addToQueue(new Patient());
                        arrivalProcess.generateNextEvent();
                        countNurse++;
                        break;
                    }
                }
                if (countNurse == 0){
                    Random random = new Random();
                    servicePoint[random.nextInt(1, 4)].addToQueue(new Patient());
                    arrivalProcess.generateNextEvent();
                    break;
                }
                break;

            case ARR_DOCTOR:
                int countDoc = 0;
                for(int i = 5; i < 8; i++) {
                    if (!servicePoint[i].isReserved()) {
                        servicePoint[i].addToQueue(new Patient());
                        arrivalProcess.generateNextEvent();
                        countDoc++;
                        break;
                    }
                }
                if (countDoc == 0){
                    Random index = new Random();
                    int g = index.nextInt(5, 7);
                    servicePoint[g].addToQueue(new Patient());
                    arrivalProcess.generateNextEvent();
                    break;
                }
                break;

            case ARR_XRAY:
                servicePoint[8].addToQueue(new Patient());
                controller.updateRectangleColor(servicePoint[8], servicePoint[8].isServicing());
                arrivalProcess.generateNextEvent();
                break;

            case ARR_BLOOD:
                servicePoint[9].addToQueue(new Patient());
                controller.updateRectangleColor(servicePoint[9], servicePoint[9].isServicing());
                arrivalProcess.generateNextEvent();
                break;

            case ARR_EKG:
                servicePoint[10].addToQueue(new Patient());
                controller.updateRectangleColor(servicePoint[10], servicePoint[10].isServicing());
                arrivalProcess.generateNextEvent();
                break;

            case ARR_MRI:
                servicePoint[11].addToQueue(new Patient());
                controller.updateRectangleColor(servicePoint[11], servicePoint[11].isServicing());
                arrivalProcess.generateNextEvent();
                break;

            case DEP_RECEP:
                a = servicePoint[0].removeFromQueue();
                servicePoint[0].endService();
                controller.updateRectangleColor(servicePoint[0], servicePoint[0].isServicing());
                controller.updateserviced(servicePoint[0], servicePoint[0].getServicedLabel());
                for (int i = 1; i < 5; i++){
                    if(!servicePoint[i].isReserved()){
                        servicePoint[i].addToQueue(a);
                        break;
                    }
                }
                break;

            case DEP_NURSE1:
                a = servicePoint[1].removeFromQueue();
                servicePoint[1].endService();
                controller.updateserviced(servicePoint[1], servicePoint[1].getServicedLabel());
                controller.updateRectangleColor(servicePoint[1], servicePoint[1].isServicing());
                if(a.getDoctorAppointment()){ // if DoctorAppointment is true, go to doctor
                    for (int i = 5; i < 8; i++){
                        if(!servicePoint[i].isReserved()){
                            servicePoint[i].addToQueue(a);
                            break;
                        }
                    }
                    break;
                }
                else if(a.getLabAppointment() && !a.getLabVisit()){ // if LabAppointment is true, go to lab
                   Random random = new Random();
                    int rand = random.nextInt(8, 10);
                    if (rand == 8){
                        servicePoint[8].addToQueue(a);
                        break;
                    }
                    else if (rand == 9){
                        servicePoint[9].addToQueue(a);
                        break;
                    }
                    else if (rand == 10){
                        servicePoint[10].addToQueue(a);
                        break;
                    }
                    break;
                }
                else {
                    a.setRemovalTime(Clock.getInstance().getClock());
                    a.reportResults();
                    patientsServiced ++;
                    break;
                }

            case DEP_NURSE2:
                a = servicePoint[2].removeFromQueue();
                servicePoint[2].endService();
                controller.updateserviced(servicePoint[2], servicePoint[2].getServicedLabel());
                controller.updateRectangleColor(servicePoint[2], servicePoint[2].isServicing());
                    if(a.getDoctorAppointment()){ // if DoctorAppointment is true, go to doctor
                        for (int i = 5; i < 8; i++){
                            if(!servicePoint[i].isReserved()){
                                servicePoint[i].addToQueue(a);
                                break;
                            }
                        }
                        break;
                    }
                    else if(a.getLabAppointment() && !a.getLabVisit()){ // if LabAppointment is true, go to lab
                       Random random = new Random();
                        int rand = random.nextInt(8, 10);
                        if (rand == 8){
                            servicePoint[8].addToQueue(a);
                            break;
                        }
                        else if (rand == 9){
                            servicePoint[9].addToQueue(a);
                            break;
                        }
                        else if (rand == 10){
                            servicePoint[10].addToQueue(a);
                            break;
                        }
                        break;
                    }
                    else {
                        a.setRemovalTime(Clock.getInstance().getClock());
                        a.reportResults();
                        patientsServiced ++;
                        break;
                    }

            case DEP_NURSE3:
                a = servicePoint[3].removeFromQueue();
                servicePoint[3].endService();
                controller.updateserviced(servicePoint[3], servicePoint[3].getServicedLabel());
                controller.updateRectangleColor(servicePoint[3], servicePoint[3].isServicing());
                    if(a.getDoctorAppointment()){ // if DoctorAppointment is true, go to doctor
                        for (int i = 5; i < 8; i++){
                            if(!servicePoint[i].isReserved()){
                                servicePoint[i].addToQueue(a);
                                break;
                            }
                        }
                        break;
                    }
                    else if(a.getLabAppointment() && !a.getLabVisit()){ // if LabAppointment is true, go to lab
                        Random random = new Random();
                        int rand = random.nextInt(8, 10);
                        if (rand == 8){
                            servicePoint[8].addToQueue(a);
                            break;
                        }
                        else if (rand == 9){
                            servicePoint[9].addToQueue(a);
                            break;
                        }
                        else if (rand == 10){
                            servicePoint[10].addToQueue(a);
                            break;
                        }
                        break;
                    }
                    else {
                        a.setRemovalTime(Clock.getInstance().getClock());
                        a.reportResults();
                        patientsServiced ++;
                        break;
                    }

            case DEP_NURSE4:
                a = servicePoint[4].removeFromQueue();
                servicePoint[4].endService();
                controller.updateserviced(servicePoint[4], servicePoint[4].getServicedLabel());
                controller.updateRectangleColor(servicePoint[4], servicePoint[4].isServicing());
                    if(a.getDoctorAppointment()){ // if DoctorAppointment is true, go to doctor
                        for (int i = 5; i < 8; i++){
                            if(!servicePoint[i].isReserved()){
                                servicePoint[i].addToQueue(a);
                                break;
                            }
                        }
                        break;
                    }
                    else if(a.getLabAppointment() && !a.getLabVisit()){ // if LabAppointment is true, go to lab
                       Random random = new Random();
                        int rand = random.nextInt(8, 10);
                        if (rand == 8){
                            servicePoint[8].addToQueue(a);
                            break;
                        }
                        else if (rand == 9){
                            servicePoint[9].addToQueue(a);
                            break;
                        }
                        else if (rand == 10){
                            servicePoint[10].addToQueue(a);
                            break;
                        }
                        break;
                    }
                    else {
                        a.setRemovalTime(Clock.getInstance().getClock());
                        a.reportResults();
                        patientsServiced ++;
                        break;
                    }
            case DEP_DOCTOR1:
                a = servicePoint[5].removeFromQueue();
                servicePoint[5].endService();
                controller.updateserviced(servicePoint[5], servicePoint[5].getServicedLabel());
                controller.updateRectangleColor(servicePoint[5], servicePoint[5].isServicing());
                    if(a.getLabAppointment() && !a.getLabVisit()){ // if LabAppointment is true, go to lab
                        Random random = new Random();
                        int rand = random.nextInt(8, 11);
                            if (rand == 8){
                                servicePoint[8].addToQueue(a);
                                break;
                            }
                            else if (rand == 9){
                                servicePoint[9].addToQueue(a);
                                break;
                            }
                            else if (rand == 10){
                                servicePoint[10].addToQueue(a);
                                break;
                            }
                            else if (rand == 11){
                                servicePoint[11].addToQueue(a);
                                break;
                            }
                            break;
                    }
                    else {
                            a.setRemovalTime(Clock.getInstance().getClock());
                            a.reportResults();
                            patientsServiced ++;
                            break;
                        }

            case DEP_DOCTOR2:
                a = servicePoint[6].removeFromQueue();
                servicePoint[6].endService();
                controller.updateserviced(servicePoint[6], servicePoint[6].getServicedLabel());
                controller.updateRectangleColor(servicePoint[6], servicePoint[6].isServicing());
                    if(a.getLabAppointment() && !a.getLabVisit()){ // if LabAppointment is true, go to lab
                        Random random = new Random();
                        int rand = random.nextInt(8, 11);
                        if (rand == 8){
                            servicePoint[8].addToQueue(a);
                            break;
                        }
                        else if (rand == 9){
                            servicePoint[9].addToQueue(a);
                            break;
                        }
                        else if (rand == 10){
                            servicePoint[10].addToQueue(a);
                            break;
                        }
                        else if (rand == 11){
                            servicePoint[11].addToQueue(a);
                            break;
                        }
                        break;
                    }
                    else {
                        a.setRemovalTime(Clock.getInstance().getClock());
                        a.reportResults();
                        patientsServiced ++;
                        break;
                    }

            case DEP_DOCTOR3:
                a = servicePoint[7].removeFromQueue();
                servicePoint[7].endService();
                controller.updateserviced(servicePoint[7], servicePoint[7].getServicedLabel());
                controller.updateRectangleColor(servicePoint[7], servicePoint[7].isServicing());
                if(a.getLabAppointment() && !a.getLabVisit()){ // if LabAppointment is true, go to lab
                   Random random = new Random();
                    int rand = random.nextInt(8, 11);
                    if (rand == 8){
                        servicePoint[8].addToQueue(a);
                        break;
                    }
                    else if (rand == 9){
                        servicePoint[9].addToQueue(a);
                        break;
                    }
                    else if (rand == 10){
                        servicePoint[10].addToQueue(a);
                        break;
                    }
                    else if (rand == 11){
                        servicePoint[11].addToQueue(a);
                        break;
                    }
                    break;
                }
                else {
                    a.setRemovalTime(Clock.getInstance().getClock());
                    a.reportResults();
                    patientsServiced ++;
                    break;
                }

            case DEP_XRAY:
                a = servicePoint[8].removeFromQueue();
                servicePoint[8].endService();
                controller.updateRectangleColor(servicePoint[8], servicePoint[8].isServicing());
                controller.updateserviced(servicePoint[8], servicePoint[8].getServicedLabel());
                if(a.getDoctorAppointment()){ // if DoctorAppointment is true, go to doctor
                    for (int i = 5; i < 8; i++){
                        if(!servicePoint[i].isReserved()){
                            servicePoint[i].addToQueue(a);
                            a.wasLab();
                            break;
                        }
                    }
                    break;
                }
                else {
                    a.setRemovalTime(Clock.getInstance().getClock());
                    a.reportResults();
                    patientsServiced ++;
                    break;
                }

            case DEP_BLOOD:
                a = servicePoint[9].removeFromQueue();
                servicePoint[9].endService();
                controller.updateserviced(servicePoint[9], servicePoint[9].getServicedLabel());
                controller.updateRectangleColor(servicePoint[9], servicePoint[9].isServicing());
                if(a.getDoctorAppointment()){ // if DoctorAppointment is true, go to doctor
                    for (int i = 5; i < 8; i++){
                        if(!servicePoint[i].isReserved()){
                            servicePoint[i].addToQueue(a);
                            a.wasLab();
                            break;
                        }
                    }
                    break;
                }
                else {
                    a.setRemovalTime(Clock.getInstance().getClock());
                    a.reportResults();
                    patientsServiced ++;
                    break;
                }

            case DEP_EKG:
                a = servicePoint[10].removeFromQueue();
                servicePoint[10].endService();
                controller.updateserviced(servicePoint[10], servicePoint[10].getServicedLabel());
                controller.updateRectangleColor(servicePoint[10], servicePoint[10].isServicing());

                if(a.getDoctorAppointment()){ // if DoctorAppointment is true, go to doctor
                    for (int i = 5; i < 8; i++){
                        if(!servicePoint[i].isReserved()){
                            servicePoint[i].addToQueue(a);
                            a.wasLab();
                            break;
                        }
                    }
                    break;
                }
                else {
                    a.setRemovalTime(Clock.getInstance().getClock());
                    a.reportResults();
                    patientsServiced ++;
                    break;
                }

            case DEP_MRI:
                a = servicePoint[11].removeFromQueue();
                servicePoint[11].endService();
                controller.updateserviced(servicePoint[11], servicePoint[11].getServicedLabel());
                controller.updateRectangleColor(servicePoint[11], servicePoint[11].isServicing());
                if(a.getDoctorAppointment()){ // if DoctorAppointment is true, go to doctor
                    for (int i = 5; i < 8; i++){
                        if(!servicePoint[i].isReserved()){
                            servicePoint[i].addToQueue(a);
                            a.wasLab();
                            break;
                        }
                    }
                    break;
                }
                else {
                    a.setRemovalTime(Clock.getInstance().getClock());
                    a.reportResults();
                    patientsServiced ++;
                    break;
                }
        }
    }

    protected void tryCEvents() {
        for (ServicePoint sp : servicePoint) {
            if (!sp.isReserved() && sp.isOnQueue()) {
                sp.beginService();
            }
        }
    }

        protected Label getPatientsServicedLabel(){
            return controller.getPatientsServiced();
        }
    protected void updatePatientsServicedLabel(){
        if (controller != null) {
            controller.updatetotalserviced(getPatientsServicedLabel());
        }
    }
    public int getuniquepatients(){
        return patientsServiced;
    }
    protected Label getuniquepatientsLabel(){
        return controller.getuniquepatients();
    }
    protected int getArrivalDistrb(){
        return controller.getArrivalDistrb();
    }
    protected Label getSlidervalue(){
        return controller.getSliderValue();
    }

    protected void results() {
        System.out.printf("\nSimulation ended at %.2f\n", Clock.getInstance().getClock());
        System.out.println("Total customers serviced: " + servicePoint[1].getPatientServiced());
        System.out.println("Total patients serviced: " + patientsServiced);
        controller.updateuniqpatients(getuniquepatientsLabel(),this);
        System.out.printf("Average service time: %.2f\n", servicePoint[0].getMeanServiceTime());
    }

    @Override
    public void run(){
        initialize();
        while (simulate()) {

            System.out.printf("\n%sA-phase:%s time is %.2f\n", RED, WHITE, currentTime());
            Clock.getInstance().setClock(currentTime());
            controller.updateClock();
            controller.updatetotalserviced(getPatientsServicedLabel());

            System.out.printf("%sB-phase:%s ", RED, WHITE);
            runBEvents();

            System.out.printf("\n%sC-phase:%s", RED, WHITE);
            tryCEvents();
        }

        results();
    }

}