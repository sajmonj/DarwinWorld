package org.example.model;

import org.example.util.MapVisualizer;

import java.util.*;

abstract class AbstractWorldMap implements WorldMap{
    protected final Boundary bounds;
    private final List<MapChangeListener> observers = new ArrayList<>();
    protected final Map<Vector2d, List<WorldElement>> mapAnimals = new HashMap<>();
    protected MapVisualizer mapVisualizer;
    public Boundary getCurrentBounds() {
        return bounds;
    }
    public abstract boolean canMoveTo(Vector2d position);
    public abstract Vector2d moveTo(Vector2d position, Vector2d directionVector);

    private final int Id;

    public AbstractWorldMap(int id, int width, int height) {
        Id = id;
        bounds = new Boundary(new Vector2d(0,0),new Vector2d(width, height));
    }

    @Override
    public void place(WorldElement element) {
        Vector2d position = element.getPosition();
        List<WorldElement> objectsAt = objectAt(position);
        objectsAt.add(element);
        mapAnimals.put(position, objectsAt);
        if(element.getClass().equals(Animal.class))
            mapChanged("Place animal, position: " + position);
    }


    @Override
    public void move(Animal animal) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(this);
        Vector2d newPosition = animal.getPosition();
        List<WorldElement> objectsAt = objectAt(newPosition);
        objectsAt.add(animal);
        List<WorldElement> objectsAtOldPosition = mapAnimals.get(oldPosition);
        objectsAtOldPosition.remove(animal);
        mapAnimals.put(newPosition, objectsAt);
        mapChanged("Move "+ animal.getName() + "from: " + oldPosition + " to: " + newPosition + " direction: "+ animal.getAnimalDirection());
    }
    public boolean removeDeadAnimals(Animal animal) {
        if(animal.getEnergy() <= 0){
            List<WorldElement> objectsAtAnimalPosition = mapAnimals.get(animal.getPosition());
            objectsAtAnimalPosition.remove(animal);
            mapChanged("Dead: " + animal.getName());
            return true;
        }
        return false;
    }



    @Override
    public List<WorldElement> objectAt(Vector2d position) {
        if(mapAnimals.get(position) != null){
            return mapAnimals.get(position);
        }
        return new ArrayList<>();
    }
    @Override
    public String toString() {
        return this.mapVisualizer.draw(bounds.lowerLeft(), bounds.upperRight());
    }
    @Override
    public Map<Vector2d, List<WorldElement>> getElements() {
        return Collections.unmodifiableMap(mapAnimals);
    }

    @Override
    public int getId() {
        return Id;
    }

    public void registerObserver(MapChangeListener observer) {
        observers.add(observer);
    }
    public void unregisterObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
}
