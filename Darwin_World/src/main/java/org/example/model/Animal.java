package org.example.model;

public class Animal implements WorldElement{

    Genotype animalGenotype;
    Vector2d animalPosition;
    MapDirection animalDirection;

    private final int ID;
    public Animal(int genNumbers, int ID){
        animalGenotype = new Genotype(genNumbers);
//        if(ID ==0 )animalPosition = new Vector2d(2,2);
//        else
            animalPosition = new Vector2d(3,3);
        animalDirection = MapDirection.NORTH;
        System.out.println(getAnimalGenotype());
        this.ID = ID;
    }

    void move(MoveValidator validator){
        animalDirection = animalDirection.rotation(animalGenotype.nextGen());
        animalPosition = validator.moveTo(animalPosition, animalDirection.toUnitVector());
        System.out.println(ID + " " + animalPosition);
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
