package org.example;

import org.example.model.Animal;
import org.example.model.WorldMap;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation{
    private final WorldMap map;
    private final List<Animal> animalsList = new ArrayList<>();

    public Simulation(int animalNumbers,int genNumbers, WorldMap map) {
        this.map = map;
        for(int i=0; i<animalNumbers; ++i){
            Animal animal = new Animal(genNumbers, i);
            animalsList.add(animal);
            map.place(animal);
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