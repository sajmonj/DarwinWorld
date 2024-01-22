package org.example.model;

import org.example.data.SimulationStatistics;

public interface MapChangeListener {
    void mapChanged(WorldMap worldMap, int day);
}
