package org.example.model;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal a, Animal b) {
        if(a.getEnergy() == b.getEnergy()){
            if (a.getAge() == b.getAge()){
                return Integer.compare(a.getID(),b.getID());
            }
            return Integer.compare(a.getAge(),b.getAge());
        }
        return Integer.compare(a.getEnergy(), b.getEnergy());
    }
}
