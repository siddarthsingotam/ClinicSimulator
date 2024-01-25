# ClinicSimulator

ClinicSimulator is a simulation project developed in Java and JavaScript, using the Maven build tool.

This project introduces a discrete-event simulation that models the dynamics of a healthcare clinic. It maps the patient journey from arrival to departure, simulating their movement through reception, nursing, and laboratory services. Utilizing a queueing system, patients wait in lines for service at each stage, with their arrival times based on service times governed by an exponential distribution. 

Implemented via the Model-View-Controller (MVC) architecture, the model stores simulation data, the view presents it, and the controller manages user interactions. Users input simulation duration and arrival rates to initiate the simulation, monitoring its progress via a graphical user interface (GUI). 

The simulation generates results within the GUI, displaying metrics like the total patients served, unique patient count, and average wait times at each service point. This tool serves as an asset for evaluating healthcare clinic performance. It facilitates bottleneck identification, highlights areas for enhancement, and allows analysis of staffing levels, scheduling policies, and patient behaviour impacts. 

## Project Structure

The project is structured into main and test packages. The main package contains the application logic and the test package contains the unit tests.

### Main Package

The main package is divided into several sub-packages:

- `controller`: Contains the `ClinicSimController` class which handles the user interface and the simulation logic. Run this to start the program.
- `framework`: Contains the `Engine` class which is the core of the simulation logic.
- `model`: Contains the `ServicePoint` and `EventType` classes which represent the different service points in the clinic and the types of events that can occur.
- `view`: Contains the `ClinicSimGUI` class which is the graphical user interface of the application.

### Test Package and Documentation

The test package contains the `EngineTest` class which tests the functionality of the `Engine` class.
JavaDoc contains the documentation for the project.

## Building the Project

The project uses Maven for dependency management and building.
