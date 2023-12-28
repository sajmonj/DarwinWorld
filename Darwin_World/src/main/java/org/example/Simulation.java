package org.example;

import org.example.model.Animal;
import org.example.model.WorldMap;


import java.util.Collections;
import java.util.List;

public class Simulation{
    private final WorldMap map;
    private final List<Animal> animalsList;

    public Simulation(List<Animal> animalsList, WorldMap map) {
        this.map = map;
        this.animalsList = animalsList;
        for (Animal animal : animalsList) {
                map.place(animal);
        }
    }

    public void run(){
        for(int i=0; i<15; ++i){
            for(Animal animal : animalsList){
                map.move(animal);
            }
        }
    }
    public List<Animal> getAnimalsList() {
        return Collections.unmodifiableList(animalsList);
    }
}