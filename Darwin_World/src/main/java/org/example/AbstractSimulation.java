package org.example;

import org.example.model.Animal;
import org.example.model.WorldMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbstractSimulation {
    protected final WorldMap map;
    protected final List<Animal> animalsList = new ArrayList<>();
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
