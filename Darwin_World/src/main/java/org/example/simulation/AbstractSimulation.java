package org.example.simulation;

import org.example.model.*;

import java.util.*;

public class AbstractSimulation {
    protected final WorldMap map;
    protected final List<Animal> listAnimals = new ArrayList<>();
    protected final Set<Grass> setGrass = new HashSet<>();
    protected final int animalEnergy;
    protected final int reproductionEnergy;
    protected final int grassEnergy;
    protected final int readyEnergy;
    protected final int grassNum;
    protected final int ID;

    public AbstractSimulation(WorldMap map, int animalEnergy, int reproductionEnergy, int grassEnergy, int readyEnergy,
                              int grassNum, int ID) {
        this.map = map;
        this.animalEnergy = animalEnergy;
        this.reproductionEnergy = reproductionEnergy;
        this.grassEnergy = grassEnergy;
        this.readyEnergy = readyEnergy;
        this.grassNum = grassNum;
        this.ID = ID;
    }
    public int getID(){
        return ID;
    }
}
