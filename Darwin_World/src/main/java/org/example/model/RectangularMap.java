package org.example.model;

import org.example.util.MapVisualizer;


public class RectangularMap extends AbstractWorldMap{
    public RectangularMap(int width, int height, int Id) {
        super(Id, width, height);
        this.mapVisualizer = new MapVisualizer(this);
    }

    public boolean canMoveTo(Vector2d position) {
        return bounds.lowerLeft().precedes(position) && bounds.upperRight().follows(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }

    @Override
    public Vector2d moveTo(Vector2d position, Vector2d directionVector) {
        if(canMoveTo(position.add(directionVector))){
            return position.add(directionVector);
        }
        Vector2d newPosition = position.add(directionVector);
        if(bounds.upperRight().getY() > newPosition.getY() && bounds.lowerLeft().getY() < newPosition.getY()) {
            if (bounds.upperRight().getX() < newPosition.getX()) {
                return new Vector2d(bounds.lowerLeft().getX(), newPosition.getY());
            } else if (bounds.lowerLeft().getX() > newPosition.getX()) {
                return new Vector2d(bounds.upperRight().getX(), newPosition.getY());
            }
        }

        return position.add(directionVector.opposite());
    }
}