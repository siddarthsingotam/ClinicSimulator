# ClinicSimulator

ClinicSimulator is a simulation project developed in Java and JavaScript, using the Maven build tool.

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
