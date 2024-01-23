package org.example.model;

import org.example.util.MapVisualizer;


public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int ID, int width, int height) {
        super(ID, width, height);
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public Vector2d moveTo(Vector2d position, Vector2d directionVector, Animal animal) {
        if(canMoveTo(position.add(directionVector))){
            return position.add(directionVector);
        }
        Vector2d newPosition = position.add(directionVector);
        Vector2d bounds1 = bordersChecking(newPosition);
        if (bounds1 != null) return bounds1;
        return position.add(directionVector.opposite());
    }

    private Vector2d bordersChecking(Vector2d newPosition) {
        if(bounds.upperRight().y() > newPosition.y() && bounds.lowerLeft().y() < newPosition.y()) {
            if (bounds.upperRight().x() < newPosition.x()) {
                return new Vector2d(bounds.lowerLeft().x(), newPosition.y());
            } else if (bounds.lowerLeft().x() > newPosition.x()) {
                return new Vector2d(bounds.upperRight().x(), newPosition.y());
            }
        }
        return null;
    }
}