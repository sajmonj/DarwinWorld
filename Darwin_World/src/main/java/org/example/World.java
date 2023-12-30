package org.example;

import org.example.model.ConsoleMapDisplay;
import org.example.model.RectangularMap;
import org.example.model.WorldMap;


public class World {
    public static void main(String[] args) {
        WorldMap map = new RectangularMap(5,5,1);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        map.registerObserver(consoleMapDisplay);
        Simulation simulation = new Simulation(2,5,map);
        simulation.run();
    }
}