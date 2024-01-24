package org.example.model;

import org.example.data.SimulationConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class AnimalComparatorTest {

    static SimulationConfiguration simulationConfiguration;
    @BeforeAll
    public static void setUp() {
        simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load();
    }

    @Test
    public void testEqualAnimals() {

        Animal animal = new Animal(simulationConfiguration, 1);

        AnimalComparator comparator = new AnimalComparator();
        assertEquals(0, comparator.compare(animal, animal));
    }

    @Test
    public void testDifferentEnergy() {
        SimulationConfiguration simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load();
        Animal animal1 = new Animal(simulationConfiguration, 1);
        Animal animal2 = new Animal(simulationConfiguration, 2);

        animal1.consumeGrass();

        AnimalComparator comparator = new AnimalComparator();
        assertEquals(-1, comparator.compare(animal1, animal2));
    }

    @Test
    public void testDifferentAge() {
        SimulationConfiguration simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load();
        Animal animal1 = new Animal(simulationConfiguration, 1);
        Animal animal2 = new Animal(simulationConfiguration, 2);

        animal2.incrementAge();

        AnimalComparator comparator = new AnimalComparator();
        assertEquals(1, comparator.compare(animal1, animal2));
    }

    @Test
    public void testDifferentNumberOfChildren() {
        SimulationConfiguration simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load();
        Animal animal1 = new Animal(simulationConfiguration, 1);
        Animal animal2 = new Animal(simulationConfiguration, 2);

        animal1.increaseNumberOfChildren();

        AnimalComparator comparator = new AnimalComparator();
        assertEquals(-1, comparator.compare(animal1, animal2));
    }

    @Test
    public void testDifferentID() {
        SimulationConfiguration simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load();
        Animal animal1 = new Animal(simulationConfiguration, 1);
        Animal animal2 = new Animal(simulationConfiguration, 2);

        AnimalComparator comparator = new AnimalComparator();
        assertEquals(1, comparator.compare(animal1, animal2));
    }

    @Test
    public void testMixedDifferences() {
        SimulationConfiguration simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load();
        Animal animal1 = new Animal(simulationConfiguration, 1);
        Animal animal2 = new Animal(simulationConfiguration, 2);

        animal1.consumeGrass();
        animal2.consumeGrass();
        animal1.incrementAge();
        animal2.increaseNumberOfChildren();

        AnimalComparator comparator = new AnimalComparator();
        assertEquals(-1, comparator.compare(animal1, animal2));
    }
}
