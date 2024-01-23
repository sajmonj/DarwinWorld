package org.example.model;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal firstAnimal, Animal secondAnimal) {
        if(firstAnimal.getEnergy() == secondAnimal.getEnergy()){
            if (firstAnimal.getAge() == secondAnimal.getAge()){
                if (firstAnimal.getNumOfChildren() == secondAnimal.getNumOfChildren()) {
                    return Integer.compare(secondAnimal.getID(), firstAnimal.getID());
                }
                return Integer.compare(secondAnimal.getNumOfChildren(), firstAnimal.getNumOfChildren());
            }
            return Integer.compare(secondAnimal.getAge(),firstAnimal.getAge());
        }
        return Integer.compare(secondAnimal.getEnergy(), firstAnimal.getEnergy());
    }
}
