package org.example;

import org.example.model.ConsoleMapDisplay;
import org.example.model.RectangularMap;
import org.example.model.WorldMap;
import org.example.simulation.Simulation;


public class World {
    public static void main(String[] args) {
        int animalEnergy = 3;
        int reproductionEnergy = 5;
        int grassEnergy = 3;
        int readyEnergy = 7;
        int grassNum = 3;
        WorldMap map = new RectangularMap(5,5,1);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        map.registerObserver(consoleMapDisplay);
        Simulation simulation = new Simulation(10,2,map, animalEnergy, readyEnergy,
                reproductionEnergy, grassEnergy, grassNum);
        simulation.run();
    }
}