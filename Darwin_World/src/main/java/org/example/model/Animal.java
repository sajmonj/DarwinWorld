package org.example.model.Animal;

import org.example.model.*;

public class Animal implements WorldElement {

    private final Genotype animalGenotype;
    private Vector2d animalPosition;
    private MapDirection animalDirection;
    private int energy;
    private int numOfChildren;
    private int age;
    private final int ID;
    public Animal(int genNumbers, int ID, int animalEnergy){
        animalGenotype = new Genotype(genNumbers);
        animalPosition = new Vector2d(3,3);
        animalDirection = MapDirection.NORTH;
        this.ID = ID;
        energy = animalEnergy;
        age = 0;
        numOfChildren = 0;
    }

    public Animal(Animal a, Animal b, int id){
        this.ID = id;
        this.age = 0;
        this.animalPosition = a.position();
        animalGenotype = new Genotype(a,b);
        animalDirection = MapDirection.NORTH;
    }

    void move(MoveValidator validator){
        animalDirection = animalDirection.rotation(animalGenotype.nextGen());
        animalPosition = validator.moveTo(animalPosition, animalDirection.toUnitVector());
        incrementAge();
        energy--;
        System.out.println("Id: " + ID + " " + animalPosition + " " + "E: " + energy);
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
        return "Animal "+ID;
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

    public void increaseNumberOfChildren(){
        numOfChildren++;
    }


    @Override
    public String toString(){
        return switch (animalDirection){
            case EAST -> "E";
            case WEST -> "W";
            case NORTH -> "N";
            case SOUTH -> "S";
            case SOUTHEAST -> "SE";
            case SOUTHWEST -> "SW";
            case NORTHEAST -> "NE";
            case NORTHWEST-> "NW";
        };
    }
    public void incrementAge() {
        age++;
    }

    public void addConsumptionEnergy() {
        energy += 3;
    }
}