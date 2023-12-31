package org.example;

import org.example.model.ConsoleMapDisplay;
import org.example.model.RectangularMap;
import org.example.model.WorldMap;

import java.util.Scanner;


public class World {
    public static void main(String[] args) {
        int animalEnergy = 2;
        int reproductionEnergy = 5;
        int grassEnergy = 3;
        int readyEnergy = 7;
        int grassNum = 10;
        WorldMap map = new RectangularMap(5,5,1);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        map.registerObserver(consoleMapDisplay);
        Simulation simulation = new Simulation(2,5,map, animalEnergy, readyEnergy,
                reproductionEnergy, grassEnergy, grassNum);
        simulation.run();
    }
}