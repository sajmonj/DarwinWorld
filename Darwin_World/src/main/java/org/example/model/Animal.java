package org.example.model;

public class Animal implements WorldElement{

    private Genotype animalGenotype;
    private Vector2d animalPosition;
    private MapDirection animalDirection;
    private int energy;
    private int numOfChildren;
    private int age;

    private final int ID;
    public Animal(int genNumbers, int ID, int animalEnergy){
        animalGenotype = new Genotype(genNumbers);
//        if(ID ==0 )animalPosition = new Vector2d(2,2);
//        else
            animalPosition = new Vector2d(3,3);
        animalDirection = MapDirection.NORTH;
        System.out.println(getAnimalGenotype());
        this.ID = ID;
        energy = animalEnergy;
        age = 0;
        numOfChildren = 0;
    }

    void move(MoveValidator validator){
        animalDirection = animalDirection.rotation(animalGenotype.nextGen());
        animalPosition = validator.moveTo(animalPosition, animalDirection.toUnitVector());
        System.out.println(ID + " " + animalPosition);
        age++;
        energy--;
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

    @Override
    public Vector2d getPosition() {
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


}
