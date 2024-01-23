package org.example.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GrassPositionGenerator implements Iterable<Vector2d> {
    private static final int EQUATORMULTIPLIER = 16;
    private final int howManyToGenerate;
    private final List<Vector2d> grids = new ArrayList<>();
    private final List<Vector2d> selectedGrids = new ArrayList<>();
    private final Boundary boundaries;
    private final WorldMap map;

    public GrassPositionGenerator(int howManyToGenerate, Boundary boundaries, WorldMap map) {
        this.howManyToGenerate = howManyToGenerate;
        this.boundaries = boundaries;
        this.map = map;
        generatePosition();
    }

    private int generateRandom(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }

    private void generatePosition() {
        Vector2d position;
        for (int x = boundaries.lowerLeft().x(); x <= boundaries.upperRight().x(); x++) {
            for (int y = boundaries.lowerLeft().y(); y <= boundaries.upperRight().y(); y++) {
                position = new Vector2d(x, y);
                addGrassPositions(position, y);
            }
        }

        for (int i=0; i<howManyToGenerate; i++) {
            if(grids.isEmpty()) break;
            Vector2d selectedGrid = grids.get(generateRandom(grids.size()));
            selectedGrids.add(selectedGrid);
            grids.removeIf(selectedGrid::equals);
        }
    }

    private void addGrassPositions(Vector2d position, int y) {
        if(!map.isOccupied(position)) {
            grids.add(position);
            if (y >= (boundaries.upperRight().y() / 2 - (int)(boundaries.upperRight().y() * 0.2) / 2 + 1)
                    && y <= (boundaries.upperRight().y() / 2 - (int)(boundaries.upperRight().y() * 0.2) / 2 + (int)(boundaries.upperRight().y()*0.2))) {
                for (int i = 0; i < EQUATORMULTIPLIER; i++) {
                    grids.add(position);
                }
            }
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return this.selectedGrids.iterator();
    }
}
