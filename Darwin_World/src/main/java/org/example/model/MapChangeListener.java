package org.example.model;

public interface MapChangeListener {
    /**
     * Update tha map
     *
     * @param worldMap The map using in the simulation.
     * @param day Day of the simulation.
     */
    void mapChanged(WorldMap worldMap, int day);
}
