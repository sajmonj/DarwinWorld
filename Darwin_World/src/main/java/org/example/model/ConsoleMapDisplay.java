package org.example.model;

public class ConsoleMapDisplay implements  MapChangeListener{
    private int totalUpdates = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        synchronized (this) {
            totalUpdates++;
            System.out.println("Map ID: " + worldMap.getId());
            System.out.println("Operacja na mapie: " + message);
            System.out.println(worldMap);
            System.out.println("Sumaryczna liczba aktualizacji: " + totalUpdates);
        }
    }
}