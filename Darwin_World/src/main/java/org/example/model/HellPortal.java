package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Stream;

public class HellPortal extends AbstractWorldMap {

    public HellPortal(int id, int width, int height) {
        super(id, width, height);
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

//        System.out.println("FreeFields"+freeFields);

        int numOfFreeFields = freeFields.size();
        if(numOfFreeFields > 0) {

            int newPositionIndex = generatePosition(numOfFreeFields);
            animal.subtractReproductionEnergy();

//            System.out.println(freeFields.get(newPositionIndex));
//            System.out.println(freeFields);

            return freeFields.get(newPositionIndex);
        }
        else {
//            System.out.println("KILLLLLLL");
            animal.kill();
            return animal.position();
        }
    }

    private int generatePosition(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }
}
