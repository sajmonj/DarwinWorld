package org.example;

import org.example.model.*;

import java.util.List;
import java.util.Set;


public class DayCycleSimulation extends AbstractSimulation {
    private final List<Animal> simulationAnimalsList;
    private final Set<Grass> grassSet;
    private final WorldMap map;


    public DayCycleSimulation(List<Animal> animalsList, Set<Grass> setGrass, WorldMap map, int animalEnergy, int reproductionEnergy, int grassEnergy, int readyEnergy, int grassNum) {
        super(map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum);
        this.simulationAnimalsList = animalsList;
        grassSet = setGrass;
        this.map = map;
    }

    public void move(){
        for(Animal animal : simulationAnimalsList){
            map.move(animal);
        }
    }

    public void reproduction(){
        map.reproduction(simulationAnimalsList);
    }

    public void consumption() {
        map.consumption(grassSet);
    }
    public void removeDeadAnimals(){
        simulationAnimalsList.removeIf(map::removeDeadAnimals);
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
