package org.example;

import org.example.model.*;

import java.util.*;

public class AbstractSimulation {
    protected final WorldMap map;
    protected final Map<Vector2d,List<Animal>> mapAnimals = new HashMap<>();
    protected final Set<Grass> setGrass = new HashSet<>();
    protected final int animalEnergy;
    protected final int reproductionEnergy;
    protected final int grassEnergy;
    protected final int readyEnergy;
    protected final int grassNum;

    public AbstractSimulation(WorldMap map, int animalEnergy, int reproductionEnergy, int grassEnergy, int readyEnergy, int grassNum) {
        this.map = map;
        this.animalEnergy = animalEnergy;
        this.reproductionEnergy = reproductionEnergy;
        this.grassEnergy = grassEnergy;
        this.readyEnergy = readyEnergy;
        this.grassNum = grassNum;
    }
}
