package org.example.simulation;

import org.example.data.SimulationConfiguration;
import org.example.data.SimulationStatistics;
import org.example.model.*;

import java.util.*;

public class AbstractSimulation {

    protected final WorldMap map;
    protected Integer day;
    protected List<Animal> listAnimals;
    protected final Set<Grass> setGrass = new HashSet<>();
    protected final SimulationStatistics simulationStatistics;
    protected final int animalEnergy;
    protected final int reproductionEnergy;
    protected final int grassEnergy;
    protected final int readyEnergy;
    protected final int grassInitNum;
    protected final int grassNum;
    protected final int speed;
    protected final int mapType;
    protected final int genotype;
    protected final int ID;

    public AbstractSimulation(SimulationConfiguration configuration, WorldMap worldMap, int ID) {
        listAnimals = new ArrayList<>();
        this.map = worldMap;
        this.animalEnergy = configuration.getAnimalEnergy();
        this.reproductionEnergy = configuration.getReproductionEnergy();
        this.grassEnergy = configuration.getGrassEnergy();
        this.readyEnergy = configuration.getReadyEnergy();
        this.grassInitNum = configuration.getGrassInitNumber();
        this.grassNum = configuration.getGrassNum();
        this.mapType = configuration.getMapType();
        this.genotype = configuration.getGenotype();
        this.speed = configuration.getSpeed();
        this.ID = ID;
        this.simulationStatistics = new SimulationStatistics(listAnimals, setGrass, ID, mapType, genotype);
        day = 1;
    }
    public AbstractSimulation(List<Animal> animalList, SimulationConfiguration configuration, WorldMap worldMap, int ID) {
        this(configuration, worldMap, ID);
        listAnimals = animalList;
    }
    public int getID(){
        return ID;
    }

    public int getSpeed() {
        return speed;
    }

    public SimulationStatistics getSimulationStatistics() {
        return simulationStatistics;
    }
}
