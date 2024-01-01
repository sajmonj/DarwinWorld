package org.example;

import org.example.model.*;


import java.util.ArrayList;
import java.util.List;

public class Simulation extends AbstractSimulation {

    public Simulation(int animalNumbers,int genNumbers, WorldMap map, int animalEnergy, int readyEnergy,
                      int reproductionEnergy, int grassEnergy, int grassNum) {
        super(map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum);
        for(int i=0; i<animalNumbers; ++i){
            Animal animal = new Animal(genNumbers, i, animalEnergy);
            addAnimal(animal);
            this.map.place(animal);
        }
        GrassPositionGenerator grassPositionGenerator = new GrassPositionGenerator(grassNum, map.getCurrentBounds());
        for (Vector2d position : grassPositionGenerator) {
            Grass grass = new Grass(position);
            setGrass.add(grass);
            this.map.place(grass);
        }
    }

    public void run(){
        List<Animal> animalsList = mapAnimals.values().stream()
                .flatMap(List::stream)
                .toList();
        DayCycleSimulation dayCycleSimulation = new DayCycleSimulation(animalsList, map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum);

        for(int i=0; i<5; ++i){
            dayCycleSimulation.removeDeadAnimals();
            dayCycleSimulation.move();
            dayCycleSimulation.reproduction();
        }
    }
    public void addAnimal(Animal animal) {
        Vector2d position = animal.position();
        if (mapAnimals.containsKey(position)) {
            mapAnimals.get(position).add(animal);
        } else {
            List<Animal> animals = new ArrayList<>();
            animals.add(animal);
            mapAnimals.put(position, animals);
        }
    }
    public List<Animal> getAnimalsList() {
        return mapAnimals.values().stream()
                .flatMap(List::stream)
                .toList();
    }
}