package org.example.model;

public interface MoveValidator {
    /**
     * Move an animal
     *
     * @param position New position of the animal.
     * @param directionVector Direction of moving.
     * @param animal Animal to move.
     * @return animal position after move.
     */
    Vector2d moveTo(Vector2d position, Vector2d directionVector, Animal animal);
}