package org.example.model;

import org.example.data.SimulationConfiguration;
import org.example.data.SimulationStatistics;

import java.util.HashSet;
import java.util.Random;

public class Animal implements WorldElement {
    private final Genotype animalGenotype;
    private final int genNumbers;
    private Vector2d animalPosition;
    private MapDirection animalDirection;
    private int energy;
    private int numOfChildren;
    private int age;
    private final int ID;
    private final Animal parentA;
    private final Animal parentB;
    private final HashSet<Animal> descendants = new HashSet<>();
    private final SimulationConfiguration configuration;

    public Animal(SimulationConfiguration configuration, int ID) {
        this.configuration = configuration;
        this.ID = ID;
        genNumbers = configuration.getGenNumbers();
        parentA = null;
        parentB = null;
        animalGenotype = new Genotype(configuration.getGenNumbers());
        animalPosition = generatePosition(configuration.getMapWidth(), configuration.getMapHeight());
        animalDirection = MapDirection.NORTH;
        energy = configuration.getAnimalEnergy();
        age = 0;
        numOfChildren = 0;
    }

    public Animal(Animal a, Animal b, int id) {
        this.ID = id;
        this.age = 0;
        this.animalPosition = a.position();
        this.configuration = a.configuration;
        genNumbers = configuration.getGenNumbers();
        energy = 2*configuration.getReproductionEnergy();
        parentA = b;
        parentB = a;
        animalGenotype = new Genotype(a, b);
        animalDirection = MapDirection.NORTH;
    }

    void move(MoveValidator validator) {
        animalDirection = animalDirection.rotation(animalGenotype.nextGen());
        animalPosition = validator.moveTo(animalPosition, animalDirection.toUnitVector());
        incrementAge();
        energy--;
    }

    private Vector2d generatePosition (int x, int y) {
        Random rand = new Random();
        return new Vector2d(rand.nextInt(x), rand.nextInt(y));
    }

    public MapDirection getAnimalDirection() {
        return animalDirection;
    }

    public Genotype getAnimalGenotype() {
        return animalGenotype;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return "Animal " + ID;
    }

    @Override
    public Vector2d position() {
        return animalPosition;
    }

    public int getEnergy() {
        return energy;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public int getAge() {
        return age;
    }

    public void increaseNumberOfChildren() {
        numOfChildren++;
    }

    @Override
    public String toString() {
        return switch (animalDirection) {
            case EAST -> "E";
            case WEST -> "W";
            case NORTH -> "N";
            case SOUTH -> "S";
            case SOUTHEAST -> "SE";
            case SOUTHWEST -> "SW";
            case NORTHEAST -> "NE";
            case NORTHWEST -> "NW";
        };
    }

    @Override
    public String toIcon() {
        return "#800020";
    }

    public int getGenNumbers() {
        return genNumbers;
    }

    public void incrementAge() {
        age++;
    }

    public void addConsumptionEnergy() {
        energy += configuration.getGrassEnergy();
    }

    public void subtractReproductionEnergy() {
        energy -= configuration.getReproductionEnergy();
    }
}
