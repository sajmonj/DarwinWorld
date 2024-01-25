package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.data.SimulationConfiguration;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.util.Optional;

public class AnimalTest {

    private static SimulationConfiguration simulationConfiguration;
    @BeforeAll
    public static void setUp() {
        // Initialize the SimulationConfiguration and Animal objects for testing
        File file = new File("Config/config1.json");
        simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load(file);
    }

    @Test
    public void testAnimalInitialization() {
        // Test if the animal is initialized correctly
        Animal animal = new Animal(simulationConfiguration, 1);
        assertNotNull(animal.getAnimalGenotype());
        assertNotNull(animal.getPosition());
        assertEquals(0, animal.getAge());
        assertEquals(0, animal.getNumOfChildren());
        assertEquals(simulationConfiguration.getAnimalEnergy(), animal.getEnergy());
        assertEquals("Animal 1", animal.getName());
        assertEquals(simulationConfiguration.getGenNumbers(), animal.getGenNumbers());
        assertEquals(Optional.empty(), animal.getDayOfDeath());
    }

    @Test
    public void testAnimalReproduction() {
        // Test animal reproduction and genotype inheritance
        Animal parentA = new Animal(simulationConfiguration, 2);
        Animal parentB = new Animal(simulationConfiguration, 3);
        Animal child = new Animal(parentA, parentB, 4);

        assertEquals(2, child.getParentA().getID());
        assertEquals(3, child.getParentB().getID());
        assertEquals(2 * simulationConfiguration.getReproductionEnergy(), child.getEnergy());
        assertNotNull(child.getAnimalGenotype());
    }

    @Test
    public void testAnimalConsumption() {
        // Test animal consumption of energy
        Animal animal = new Animal(simulationConfiguration, 1);
        int initialEnergy = animal.getEnergy();
        animal.consumeGrass();
        assertEquals(initialEnergy + simulationConfiguration.getGrassEnergy(), animal.getEnergy());
    }

    @Test
    public void testAnimalReproductionEnergySubtraction() {
        // Test subtraction of reproduction energy after reproduction
        Animal animal = new Animal(simulationConfiguration, 1);
        int initialEnergy = animal.getEnergy();
        animal.subtractReproductionEnergy();
        assertEquals(initialEnergy - simulationConfiguration.getReproductionEnergy(), animal.getEnergy());
    }

    @Test
    public void testAnimalDeath() {
        // Test animal death and energy state after death
        Animal animal = new Animal(simulationConfiguration, 1);
        animal.kill();
        animal.setDayOfDeath(123);
        assertTrue(animal.getEnergy() < 0);
        assertTrue(animal.getDayOfDeath().isPresent());
    }

}
