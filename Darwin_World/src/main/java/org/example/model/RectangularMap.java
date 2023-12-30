package org.example.model;

import org.example.util.MapVisualizer;

public class RectangularMap extends AbstractWorldMap{
    public RectangularMap(int width, int height, int Id) {
        super(Id, width, height);
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public Boundary getCurrentBounds() {
        return bounds;
    }

    public boolean canMoveTo(Vector2d position) {
        return bounds.lowerLeft().precedes(position) && bounds.upperRight().follows(position) && !isOccupied( position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }

    @Override
    public Vector2d MoveTo(Vector2d position, Vector2d directionVector) {
        if(canMoveTo(position.add(directionVector))){
            return position.add(directionVector);
        }
        return position.add(directionVector.opposite());
    }
}