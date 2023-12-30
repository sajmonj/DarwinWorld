package org.example;

import org.example.model.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation{
    private final WorldMap map;
    private final List<Animal> animalsList = new ArrayList<>();
    private final int animalEnergy;
    private final int reproductionEnergy;
    private final int grassEnergy;
    private final int readyEnergy;
    private final int grassNum;


    public Simulation(int animalNumbers,int genNumbers, WorldMap map, int animalEnergy, int readyEnergy,
                      int reproductionEnergy, int grassEnergy, int grassNum) {
        this.map = map;
        this.reproductionEnergy = reproductionEnergy;
        this.animalEnergy = animalEnergy;
        this.grassEnergy = grassEnergy;
        this.readyEnergy = readyEnergy;
        this.grassNum = grassNum;
        for(int i=0; i<animalNumbers; ++i){
            Animal animal = new Animal(genNumbers, i, animalEnergy);
            animalsList.add(animal);
            this.map.place(animal);
        }
        GrassPositionGenerator grassPositionGenerator = new GrassPositionGenerator(grassNum, map.getCurrentBounds());
        for (Vector2d position : grassPositionGenerator) {
            Grass grass = new Grass(position);
            this.map.place(grass);
        }
    }

    public void run(){
        for(int i=0; i<5; ++i){
            for(Animal animal : animalsList){
                System.out.println("Animal"+animal.getID());
                map.move(animal);
            }
        }
    }
    public List<Animal> getAnimalsList() {
        return Collections.unmodifiableList(animalsList);
    }
}