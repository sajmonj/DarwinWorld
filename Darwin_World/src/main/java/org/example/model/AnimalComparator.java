package org.example.model;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal a, Animal b) {
        if(a.getEnergy() == b.getEnergy()){
            if (a.getAge() == b.getAge()){
                if (a.getNumOfChildren() == b.getNumOfChildren()) {
                    return Integer.compare(b.getID(), a.getID());
                }
                return Integer.compare(b.getNumOfChildren(), a.getNumOfChildren());
            }
            return Integer.compare(b.getAge(),a.getAge());
        }
        return Integer.compare(b.getEnergy(), a.getEnergy());
    }
}
