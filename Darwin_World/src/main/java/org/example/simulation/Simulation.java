package org.example.simulation;

import org.example.model.*;
import org.example.simulation.AbstractSimulation;
import org.example.simulation.DayCycleSimulation;


import java.util.List;

public class Simulation extends AbstractSimulation {

    public Simulation(int animalsNumber,int genNumbers, WorldMap map, int animalEnergy, int readyEnergy,
                      int reproductionEnergy, int grassEnergy, int grassNum) {
        super(map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum);
        for(int i=0; i<animalsNumber; ++i){
            Animal animal = new Animal(genNumbers, i, animalEnergy);
            listAnimals.add(animal);
            this.map.place(animal);
        }
        GrassPositionGenerator grassPositionGenerator = new GrassPositionGenerator(grassNum, map.getCurrentBounds(), map);
        for (Vector2d position : grassPositionGenerator) {
            Grass grass = new Grass(position);
            setGrass.add(grass);
            this.map.place(grass);
        }
    }

    public void run() {
        DayCycleSimulation dayCycleSimulation = new DayCycleSimulation(listAnimals, setGrass, map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum);

        for (int i = 0; i < 5; ++i) {
            dayCycleSimulation.removeDeadAnimals();
            dayCycleSimulation.move();
            dayCycleSimulation.consumption();
            dayCycleSimulation.reproduction();
            dayCycleSimulation.grassGrowth();
        }
    }
    public List<Animal> getAnimalsList() {
        return listAnimals;
    }
}