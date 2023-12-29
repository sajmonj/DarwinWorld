package org.example.model;

import org.example.util.MapVisualizer;

import java.util.*;

abstract class AbstractWorldMap implements WorldMap{
    protected final Boundary bounds;
    private final List<MapChangeListener> observers = new ArrayList<>();
    protected final Map<Vector2d, Animal> mapAnimals = new HashMap<>();
    protected MapVisualizer mapVisualizer;
    public abstract Boundary getCurrentBounds();
    public abstract boolean canMoveTo(Vector2d position);
    public abstract Vector2d MoveTo(Vector2d position, Vector2d directionVector);


    private final int Id;

    public AbstractWorldMap(int id, int width, int height) {
        Id = id;
        bounds = new Boundary(new Vector2d(0,0),new Vector2d(width, height));
    }

    @Override
    public void place(Animal element) {
        Vector2d position = element.getPosition();
        mapAnimals.put(element.getPosition(), element);
        mapChanged("Place animal, position: " + position);
    }


    @Override
    public void move(Animal animal) {
        System.out.println(isOccupied(animal.getPosition()));
//        System.out.println(" " + objectAt(animal.getPosition()).equals(animal));
        if(isOccupied(animal.getPosition()) && objectAt(animal.getPosition()).equals(animal)){
            Vector2d oldPosition = animal.getPosition();
            animal.move(this);
            Vector2d newPosition = animal.getPosition();
            mapAnimals.remove(oldPosition);
            mapAnimals.put(newPosition, animal);
            mapChanged("Move animal" +animal.getID() + " from: " + oldPosition + " to: " + newPosition + " direction: "+ animal.getAnimalDirection());
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return mapAnimals.get(position);
    }
    @Override
    public String toString() {
        return this.mapVisualizer.draw(bounds.lowerLeft(), bounds.upperRight());
    }
    @Override
    public Map<Vector2d, WorldElement> getElements() {
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
