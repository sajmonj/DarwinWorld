package org.example.model;


import java.util.List;
import java.util.Map;

public interface WorldMap extends MoveValidator {


    /**
     * Return Map Id.
     */
    int getId();

    /**
     * Place a World Element on the map.
     *
     * @param element The animal to place on the map.
     */
    void place(WorldElement element);

    /**
     * Remove a dead animal for map.
     * Return true if the animal has 0 and less energy
     *
     * @param animal The animal to remove for the map.
     */
    boolean removeDeadAnimals(Animal animal);

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(Animal animal);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    default boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */
    List<WorldElement> objectAt(Vector2d position);

    Map<Vector2d, List<WorldElement>> getElements();

    Boundary getCurrentBounds();

    void registerObserver(MapChangeListener observer);

    void unregisterObserver(MapChangeListener observer);

    void reproduction(List<Animal> simulationAnimalsList);
}
