package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void rotation() {
        MapDirection direction1 = MapDirection.NORTHEAST;
        MapDirection direction2 = MapDirection.SOUTHWEST;
        MapDirection direction3 = MapDirection.EAST;

        Gen gen1 = Gen.DEGREES_180;
        Gen gen2 = Gen.DEGREES_315;
        Gen gen3 = Gen.DEGREES_225;


        MapDirection rotation1 = direction1.rotation(gen1);
        MapDirection rotation2 = direction2.rotation(gen2);
        MapDirection rotation3 = direction3.rotation(gen3);


        assertEquals(MapDirection.SOUTHWEST,rotation1);
        assertEquals(MapDirection.SOUTH,rotation2);
        assertEquals(MapDirection.NORTHWEST,rotation3);
    }
}