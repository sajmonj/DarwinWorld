package org.example.model;

import org.example.data.SimulationConfiguration;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;

public class Animal implements WorldElement {
    private final HashSet<Animal> descendants = new HashSet<>();
    private final SimulationConfiguration configuration;
    private final Genotype animalGenotype;
    private final Animal parentA;
    private final Animal parentB;
    private final int ID;
    private final int genNumbers;
    private int energy;
    private int numOfChildren;
    private int age;
    private Vector2d animalPosition;
    private MapDirection animalDirection;
    private Integer dayOfDeath;

    public Animal(SimulationConfiguration configuration, int ID) {
        this.configuration = configuration;
        this.ID = ID;
        parentA = null;
        parentB = null;
        dayOfDeath = null;
        genNumbers = configuration.getGenNumbers();
        animalGenotype = new Genotype(genNumbers, configuration.getGenotype());
        animalPosition = generatePosition(configuration.getMapWidth(), configuration.getMapHeight());
        animalDirection=generateOrientation();
        energy = configuration.getAnimalEnergy();
        age = 0;
        numOfChildren = 0;
    }

    public Animal(Animal a, Animal b, int ID) {
        this.ID = ID;
        this.age = 0;
        this.animalPosition = a.position();
        this.configuration = a.configuration;
        genNumbers = configuration.getGenNumbers();
        energy = 2*configuration.getReproductionEnergy();
        parentA = b;
        parentB = a;
        dayOfDeath = null;
        animalDirection = generateOrientation();
        MutationsGenerator mutationsGenerator = new MutationsGenerator(configuration.getMinMutations(),
                configuration.getMaxMutations(), genNumbers, new Genotype(a, b, configuration.getGenotype()));
        animalGenotype = mutationsGenerator.mutatedGenotype();
    }

    void move(MoveValidator validator) {
        animalDirection = animalDirection.rotation(animalGenotype.nextGen());
        animalPosition = validator.moveTo(animalPosition, animalDirection.toUnitVector(), this);
        incrementAge();
        energy--;
    }

    private Vector2d generatePosition (int x, int y) {
        Random rand = new Random();
        return new Vector2d(rand.nextInt(x), rand.nextInt(y));
    }

    private MapDirection generateOrientation() {
        Random rand = new Random();
        MapDirection[] directions = MapDirection.values();
        return directions[rand.nextInt(directions.length)];
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

    public void setDayOfDeath(Integer dayOfDeath) {
        this.dayOfDeath = dayOfDeath;
    }

    public Optional<Integer> getDayOfDeath() {
        return Optional.ofNullable(dayOfDeath);
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

    public void kill() {
        energy = (-1)*configuration.getGrassEnergy()-1;
    }
}
