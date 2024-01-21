package org.example.model;

public interface MoveValidator {
    Vector2d moveTo(Vector2d position, Vector2d directionVector, Animal animal);
}