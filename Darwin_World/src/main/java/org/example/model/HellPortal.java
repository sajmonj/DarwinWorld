package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HellPortal extends AbstractWorldMap {

    public HellPortal(int ID, int width, int height) {
        super(ID, width, height);
    }

    @Override
    public void move(Animal animal) {
        super.move(animal);
    }

    @Override
    public Vector2d moveTo(Vector2d position, Vector2d directionVector, Animal animal) {
        if(canMoveTo(position.add(directionVector))){
            return position.add(directionVector);
        }

        List<Vector2d> freeFields = new ArrayList<>();
        mapElements.forEach((field, element) -> {
            if(!isOccupied(field)) freeFields.add(field);
        });

        return animalRelocation(animal, freeFields);
    }

    private Vector2d animalRelocation(Animal animal, List<Vector2d> freeFields) {
        int numOfFreeFields = freeFields.size();
        if(numOfFreeFields > 0) {

            int newPositionIndex = generatePosition(numOfFreeFields);
            animal.subtractReproductionEnergy();

            return freeFields.get(newPositionIndex);
        }
        else {
            animal.kill();
            return animal.getPosition();
        }
    }

    private int generatePosition(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }
}
