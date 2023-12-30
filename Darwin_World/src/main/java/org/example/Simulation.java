package org.example;

import org.example.model.*;


import java.util.Collections;
import java.util.List;

public class Simulation extends AbstractSimulation {

    public Simulation(int animalNumbers,int genNumbers, WorldMap map, int animalEnergy, int readyEnergy,
                      int reproductionEnergy, int grassEnergy, int grassNum) {
        super(map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum);
        for(int i=0; i<animalNumbers; ++i){
            Animal animal = new Animal(genNumbers, i, animalEnergy);
            animalsList.add(animal);
            this.map.place(animal);
        }
        System.out.println("A " +animalsList);
        GrassPositionGenerator grassPositionGenerator = new GrassPositionGenerator(grassNum, map.getCurrentBounds());
        for (Vector2d position : grassPositionGenerator) {
            Grass grass = new Grass(position);
            this.map.place(grass);
        }
    }

    public void run(){
        DayCycleSimulation dayCycleSimulation = new DayCycleSimulation(animalsList, map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum);
        for(int i=0; i<5; ++i){
            dayCycleSimulation.move();
        }
    }

    public List<Animal> getAnimalsList() {
        return Collections.unmodifiableList(animalsList);
    }
}