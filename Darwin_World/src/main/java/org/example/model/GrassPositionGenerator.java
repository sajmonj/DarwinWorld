package org.example.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassPositionGenerator implements Iterable<Vector2d> {
    private final List<Vector2d> grids = new ArrayList<>();
    private final List<Vector2d> selectedGrids = new ArrayList<>();
    private final int howManyToGenerate;
    private final Boundary boundaries;


    public GrassPositionGenerator(int howManyToGenerate, Boundary boundaries) {
        this.howManyToGenerate = howManyToGenerate;
        this.boundaries = boundaries;
        generatePosition();
    }

    private int generateRandom(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }

    private void generatePosition() {
        Vector2d position;
        for (int x = boundaries.lowerLeft().getX(); x <= boundaries.upperRight().getX(); x++) {
            for (int y = boundaries.lowerLeft().getY(); y <= boundaries.upperRight().getY(); y++) {
                position = new Vector2d(x, y);
                grids.add(position);
                if (y >= (boundaries.upperRight().getY()/2 - boundaries.upperRight().getY()*0.2/2)
                        && y <= (boundaries.upperRight().getY()/2 + boundaries.upperRight().getY()*0.2/2)) {
                    for (int i=0; i<16; i++) {
                        grids.add(position);
                    }
                }
            }
        }
        System.out.println(grids);

        for (int i=0; i<howManyToGenerate; i++) {
            Vector2d selectedGrid = grids.get(generateRandom(grids.size()));
            selectedGrids.add(selectedGrid);
            grids.removeIf(selectedGrid::equals);
        }
    }
    @Override
    public Iterator<Vector2d> iterator() {
        return this.selectedGrids.iterator();
    }
}
