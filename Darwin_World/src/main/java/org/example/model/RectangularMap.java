package org.example.model;

import org.example.util.MapVisualizer;


public class RectangularMap extends AbstractWorldMap{
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    public RectangularMap(int width, int height, int Id) {
        super(Id);
        upperRight = new Vector2d(width, height);
        lowerLeft = new Vector2d(0,0);
        this.mapVisualizer = new MapVisualizer(this);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.precedes(position) && upperRight.follows(position) && !isOccupied( position);
    }

    @Override
    public void move(Animal animal) {

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position);
    }
}