package org.example;

import org.example.model.Animal;
import org.example.model.WorldMap;

import java.util.List;


public class DayCycleSimulation extends AbstractSimulation {
    private final List<Animal> simulationAnimalsList;

    public DayCycleSimulation(List<Animal> animalsList, WorldMap map, int animalEnergy, int reproductionEnergy, int grassEnergy, int readyEnergy, int grassNum) {
        super(map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum);
        this.simulationAnimalsList = animalsList;
    }

    public void move(){
        for(Animal animal : simulationAnimalsList){
            map.move(animal);
        }
    }
}
