package org.example.simulation;

import org.example.data.SimulationConfiguration;
import org.example.data.SimulationStatistics;
import org.example.model.*;

import java.util.*;

public class AbstractSimulation {

    protected final WorldMap map;
    protected List<Animal> listAnimals;
    protected final Set<Grass> setGrass = new HashSet<>();
    protected final SimulationStatistics simulationStatistics;
    protected final int animalEnergy;
    protected final int reproductionEnergy;
    protected final int grassEnergy;
    protected final int readyEnergy;
    protected final int grassInitNum;
    protected final int grassNum;
    protected final int ID;

    public AbstractSimulation(SimulationConfiguration configuration, WorldMap worldMap, int ID) {
        this.map = worldMap;
        this.animalEnergy = configuration.getAnimalEnergy();
        this.reproductionEnergy = configuration.getReproductionEnergy();
        this.grassEnergy = configuration.getGrassEnergy();
        this.readyEnergy = configuration.getReadyEnergy();
        this.grassInitNum = configuration.getGrassInitNumber();
        this.grassNum = configuration.getGrassNum();
        this.ID = ID;
        listAnimals = new ArrayList<>();
        this.simulationStatistics = new SimulationStatistics(listAnimals, ID);
    }
    public AbstractSimulation(List<Animal> animalList, SimulationConfiguration configuration, WorldMap worldMap, int ID) {
        this(configuration, worldMap, ID);
        listAnimals = animalList;
    }
    public int getID(){
        return ID;
    }

    public SimulationStatistics getSimulationStatistics() {
        return simulationStatistics;
    }
}
