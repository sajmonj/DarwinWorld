package org.example.model;

public class ConsoleMapDisplay implements  MapChangeListener{
    private int totalUpdates = 0;
    @Override
    public void mapChanged(WorldMap worldMap, int day) {
        synchronized (this) {
            totalUpdates++;
            System.out.println("Map ID: " + worldMap.getID());
            System.out.println("Map operation: " + day);
            System.out.println(worldMap);
            System.out.println("Total number of operations: " + totalUpdates);
        }
    }
}