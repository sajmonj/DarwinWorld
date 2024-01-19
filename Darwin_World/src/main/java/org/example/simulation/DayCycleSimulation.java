package org.example.simulation;

import org.example.data.SimulationConfiguration;
import org.example.model.*;
import org.example.simulation.AbstractSimulation;

import java.util.List;
import java.util.Set;


public class DayCycleSimulation extends AbstractSimulation {
    private final Set<Grass> grassSet;
    private final WorldMap map;


    public DayCycleSimulation(List<Animal> animalList, Set<Grass> setGrass, SimulationConfiguration configuration,
                              WorldMap worldMap, int ID) {
        super(animalList, configuration, worldMap, ID);
        grassSet = setGrass;
        this.map = worldMap;
    }

    public void move(){
        for(Animal animal : listAnimals){
            map.move(animal);
        }
    }

    public void reproduction(){
        map.reproduction(listAnimals);
    }

    public void consumption() {
        map.consumption(grassSet);
    }

    public void removeDeadAnimals(){
        listAnimals.removeIf(map::removeDeadAnimals);
    }

    public void grassGrowth() {
        GrassPositionGenerator generator = new GrassPositionGenerator(grassNum, map.getCurrentBounds(), map);
        for (Vector2d position : generator) {
            Grass grass = new Grass(position);
            grassSet.add(grass);
            this.map.place(grass);
        }
    }
}
