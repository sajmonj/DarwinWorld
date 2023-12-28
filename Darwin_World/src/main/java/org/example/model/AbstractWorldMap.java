package org.example.model;

import org.example.util.MapVisualizer;

import java.util.*;

abstract class AbstractWorldMap implements WorldMap{
    private final List<MapChangeListener> observers = new ArrayList<>();
    protected final Map<Vector2d, Animal> mapAnimals = new HashMap<>();
    protected MapVisualizer mapVisualizer;
    private final int Id;

    AbstractWorldMap(int id) {
        Id = id;
    }

    public abstract boolean canMoveTo(Vector2d position);
    @Override
    public void place(Animal element) {
        Vector2d position = element.getPosition();
        mapAnimals.put(element.getPosition(), element);
        mapChanged("Place animal, position: " + position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return mapAnimals.get(position);
    }
    @Override
    public String toString() {
        return "";
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
