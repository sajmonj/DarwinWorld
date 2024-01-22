package org.example.model;

import org.example.data.SimulationStatistics;

public class ConsoleMapDisplay implements  MapChangeListener{
    private int totalUpdates = 0;

    @Override
    public void mapChanged(WorldMap worldMap, int day) {
        synchronized (this) {
            totalUpdates++;
            System.out.println("Map ID: " + worldMap.getId());
            System.out.println("Map operation: " + day);
            System.out.println(worldMap);
            System.out.println("Total number of operations: " + totalUpdates);
        }
    }
}