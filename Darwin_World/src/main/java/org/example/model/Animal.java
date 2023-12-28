package org.example.model;

public class Animal implements WorldElement{

    Genotype animalGenotype;
    Vector2d animalPosition;
    MapDirection animalDirection;
    public Animal(int genNumbers){
        animalGenotype = new Genotype(genNumbers);
        animalPosition = new Vector2d(2,2);
        animalDirection = MapDirection.NORTH;
    }

    void move(MoveValidator validator){
        animalDirection = animalDirection.rotation(animalGenotype.nextGen());
        animalPosition = validator.MoveTo(animalPosition, animalDirection.toUnitVector());
    }

    public MapDirection getAnimalDirection() {
        return animalDirection;
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
