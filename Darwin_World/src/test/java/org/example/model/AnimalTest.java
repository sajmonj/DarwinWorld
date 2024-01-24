package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.data.SimulationConfiguration;
import org.junit.jupiter.api.BeforeAll;
import java.util.Optional;

public class AnimalTest {

    private SimulationConfiguration configuration;
    private Animal animal;

    @BeforeEach
    public void setUp() {
        // Initialize the SimulationConfiguration and Animal objects for testing
        configuration = new SimulationConfiguration();
        configuration.load();
        animal = new Animal(configuration, 1);
    }

    @Test
    public void testAnimalInitialization() {
        // Test if the animal is initialized correctly
        assertNotNull(animal.getAnimalGenotype());
        assertNotNull(animal.getPosition());
        assertEquals(0, animal.getAge());
        assertEquals(0, animal.getNumOfChildren());
        assertEquals(configuration.getAnimalEnergy(), animal.getEnergy());
        assertEquals("Animal 1", animal.getName());
        assertEquals(configuration.getGenNumbers(), animal.getGenNumbers());
        assertEquals(Optional.empty(), animal.getDayOfDeath());
    }

    @Test
    public void testAnimalReproduction() {
        // Test animal reproduction and genotype inheritance
        Animal parentA = new Animal(configuration, 2);
        Animal parentB = new Animal(configuration, 3);
        Animal child = new Animal(parentA, parentB, 4);

        assertEquals(2, child.getParentA().getID());
        assertEquals(3, child.getParentB().getID());
        assertEquals(2 * configuration.getReproductionEnergy(), child.getEnergy());
        assertNotNull(child.getAnimalGenotype());
    }

    @Test
    public void testAnimalConsumption() {
        // Test animal consumption of energy
        int initialEnergy = animal.getEnergy();
        animal.addConsumptionEnergy();
        assertEquals(initialEnergy + configuration.getGrassEnergy(), animal.getEnergy());
    }

    @Test
    public void testAnimalReproductionEnergySubtraction() {
        // Test subtraction of reproduction energy after reproduction
        int initialEnergy = animal.getEnergy();
        animal.subtractReproductionEnergy();
        assertEquals(initialEnergy - configuration.getReproductionEnergy(), animal.getEnergy());
    }

    @Test
    public void testAnimalDeath() {
        // Test animal death and energy state after death
        animal.kill();
        animal.setDayOfDeath(123);
        assertTrue(animal.getEnergy() < 0);
        assertTrue(animal.getDayOfDeath().isPresent());
    }

}
