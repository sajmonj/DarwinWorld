package org.example.model;

import org.example.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

abstract class AbstractWorldMap implements WorldMap{
    protected final Boundary bounds;
    private final List<MapChangeListener> observers = new ArrayList<>();
    protected final Map<Vector2d, List<WorldElement>> mapElements = new HashMap<>();
    protected MapVisualizer mapVisualizer;
    public Boundary getCurrentBounds() {
        return bounds;
    }
    public abstract Vector2d moveTo(Vector2d position, Vector2d directionVector, Animal animal);
    private final int Id;

    public AbstractWorldMap(int id, int width, int height) {
        Id = id;
        bounds = new Boundary(new Vector2d(1,1),new Vector2d(width, height));
    }
    @Override
    public void place(WorldElement element) {
        Vector2d position = element.position();
        List<WorldElement> objectsAt = objectAt(position);
        objectsAt.add(element);
        mapElements.put(position, objectsAt);
    }

    public boolean canMoveTo(Vector2d position) {
        return bounds.lowerLeft().precedes(position) && bounds.upperRight().follows(position);
    }

    @Override
    public void move(Animal animal) {
        Vector2d oldPosition = animal.position();
        animal.move(this);
        Vector2d newPosition = animal.position();
        List<WorldElement> objectsAt = objectAt(newPosition);
        objectsAt.add(animal);
        List<WorldElement> objectsAtOldPosition = mapElements.get(oldPosition);
        objectsAtOldPosition.remove(animal);
        mapElements.put(newPosition, objectsAt);
    }

    @Override
    public void removeDeadAnimals(Animal animal, int day) {
        if(animal.getEnergy() <= 0 && animal.getDayOfDeath().isEmpty()){
            System.out.println(animal.getID());
            animal.setDayOfDeath(day);
            List<WorldElement> objectsAtAnimalPosition = mapElements.get(animal.position());
            objectsAtAnimalPosition.remove(animal);
            System.out.println(objectsAtAnimalPosition);
        }
    }

    @Override
    public void reproduction(List<Animal> simulationAnimalsList){
        Map<Vector2d, List<Animal>> mapAnimals = new HashMap<>();

        mapElements.forEach((position, elements) -> {
            List<Animal> animalElements = elements.stream()
                    .filter(element -> element instanceof Animal)
                    .map(element -> (Animal) element)
                    .collect(Collectors.toList());

            if (!animalElements.isEmpty()) {
                mapAnimals.put(position, animalElements);
            }
        });

        AnimalComparator animalComparator = new AnimalComparator();
        mapAnimals.forEach((position, elements) -> {
            if(elements.size()>1){
                elements.sort(animalComparator);
                if(elements.get(1).getEnergy() >= 8)bornAnimal(elements.get(0), elements.get(1), simulationAnimalsList);
            }
        });
    }

    private void bornAnimal(Animal a, Animal b, List<Animal> simulationAnimalsList) {
        Animal animal =  new Animal(a,b, simulationAnimalsList.size());
        a.increaseNumberOfChildren();
        b.increaseNumberOfChildren();
        a.subtractReproductionEnergy();
        b.subtractReproductionEnergy();
        place(animal);
        simulationAnimalsList.add(animal);
    }

    @Override
    public void consumption(Set<Grass> grassSet){
        Map<Grass, Animal> consumptionList = new HashMap<>();

        grassSet.forEach(grass -> {
            List<Animal> animalList = mapElements.get(grass.position()).stream()
                    .filter(element -> element instanceof Animal)
                    .map(element -> (Animal) element)
                    .collect(Collectors.toList());

            AnimalComparator comparator = new AnimalComparator();
            if(!animalList.isEmpty()) {
                animalList.sort(comparator);
                consumptionList.put(grass, animalList.get(0));
            }
        });

        consumptionList.forEach((grass, animal) -> {
            consumeGrass(grass, animal, grassSet);
        });
    }

    private void consumeGrass(Grass grass, Animal animal, Set<Grass> grassSet) {
        grassSet.remove(grass);

        List<WorldElement> objectsAtGrassPosition = mapElements.get(grass.position());
        objectsAtGrassPosition.remove(grass);

        animal.addConsumptionEnergy();
    }

    @Override
    public List<WorldElement> objectAt(Vector2d position) {
        if(mapElements.get(position) != null){
            return mapElements.get(position);
        }
        return new ArrayList<>();
    }
    @Override
    public String toString() {
        return this.mapVisualizer.draw(bounds.lowerLeft(), bounds.upperRight());
    }
    @Override
    public Map<Vector2d, List<WorldElement>> getElements() {
        return Collections.unmodifiableMap(mapElements);
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

    public void mapChanged(int day) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, day);
        }
    }
}
