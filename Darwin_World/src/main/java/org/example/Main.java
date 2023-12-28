package org.example;

import org.example.model.Animal;
import org.example.model.ConsoleMapDisplay;
import org.example.model.RectangularMap;
import org.example.model.WorldMap;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Animal(5);
        WorldMap map = new RectangularMap(5,5,1);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        map.registerObserver(consoleMapDisplay);
        Simulation simulation = new Simulation(List.of(animal1),map);
        simulation.run();
    }
}