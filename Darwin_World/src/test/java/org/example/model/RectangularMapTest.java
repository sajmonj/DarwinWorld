package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.example.data.SimulationConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class RectangularMapTest {

    static SimulationConfiguration simulationConfiguration;
    @BeforeAll
    public static void setUp() {
        File file = new File("Config/config1.json");
        simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load(file);
    }

    @Test
    void testMoveToValidPosition() {
        RectangularMap rectangularMap = new RectangularMap(1, simulationConfiguration.getMapWidth(), simulationConfiguration.getMapHeight());
        Animal animal = new Animal(simulationConfiguration, 1);
        Vector2d newPosition = rectangularMap.moveTo(new Vector2d(2,2), new Vector2d(1, 0), animal);

        assertTrue(rectangularMap.canMoveTo(newPosition));
    }

    @Test
    void testMoveToInvalidPositionWithinBounds() {
        RectangularMap rectangularMap = new RectangularMap(1, simulationConfiguration.getMapWidth(), simulationConfiguration.getMapHeight());
        Animal animal = new Animal(simulationConfiguration, 1);
        Vector2d newPosition = rectangularMap.moveTo(new Vector2d(2,2), new Vector2d(1, 0), animal);

        assertTrue(rectangularMap.canMoveTo(newPosition));
        assertEquals(new Vector2d(3, 2), newPosition);
    }

    @Test
    void testMoveToPositionOutsideBounds() {
        RectangularMap rectangularMap = new RectangularMap(1, simulationConfiguration.getMapWidth(), simulationConfiguration.getMapHeight());
        Animal animal = new Animal(simulationConfiguration,1);
        Vector2d newPosition = rectangularMap.moveTo(new Vector2d(simulationConfiguration.getMapWidth(),simulationConfiguration.getMapHeight()), new Vector2d(1, 0), animal);

        assertTrue(rectangularMap.canMoveTo(newPosition));
        assertEquals(new Vector2d(simulationConfiguration.getMapWidth()-1,simulationConfiguration.getMapHeight()), newPosition);
    }
}
