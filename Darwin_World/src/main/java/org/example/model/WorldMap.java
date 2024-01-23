package org.example.model;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WorldMap extends MoveValidator {


    /**
     * Return Map Id.
     */
    int getID();

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
    void removeDeadAnimals(Animal animal, int day);

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
        return !objectAt(position).isEmpty();
    }

    /**
     * Return list of animals at a given position.
     *
     * @param position The position of the animals.
     * @return return list of animals or null if the position is not occupied.
     */
    List<WorldElement> objectAt(Vector2d position);

    /**
     * Return map of world elements from the maps.
     *
     * @return return map of world element.
     */

    Map<Vector2d, List<WorldElement>> getElements();

    /**
     * Return current bounds of map.
     *
     * @return return current bounds of map.
     */

    Boundary getCurrentBounds();

    /**
     * register a observer
     *
     * @param observer to register
     */

    void registerObserver(MapChangeListener observer);

    /**
     * unregister a observer
     *
     * @param observer to register
     */

    void unregisterObserver(MapChangeListener observer);

    /**
     * Animals reproduction
     *
     * @param simulationAnimalsList is a list of animals
     */

    void reproduction(List<Animal> simulationAnimalsList);

    /**
     * Animals consume grass
     *
     * @param grassSet is a set of grass
     */

    void consumption(Set<Grass> grassSet);

    /**
     * Update tha map
     *
     * @param day Day of the simulation.
     */

    void mapChanged(int day);
}
