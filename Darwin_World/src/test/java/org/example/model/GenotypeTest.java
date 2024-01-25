package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.example.data.SimulationConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class GenotypeTest {

    static SimulationConfiguration simulationConfiguration;
    @BeforeAll
    public static void setUp() {
        File file = new File("Config/config1.json");
        simulationConfiguration = new SimulationConfiguration();
        simulationConfiguration.load(file);
    }

    @Test
    void testGenotypeConstructor() {
        Genotype genotype = new Genotype(5, 1);
        assertNotNull(genotype);
        assertEquals(5, genotype.getGenNumbers());
    }

    @Test
    void testInheritGenotype() {
        Animal parent1 = new Animal(simulationConfiguration, 1);
        Animal parent2 = new Animal(simulationConfiguration, 2);

        Genotype genotype = new Genotype(parent1, parent2, 1);
        assertNotNull(genotype);
        assertEquals(parent1.getGenNumbers(), genotype.getGenNumbers());
    }

    @Test
    void testCopyGens() {
        Animal parent = new Animal(simulationConfiguration, 1);
        Genotype genotype = new Genotype(0, 1);

        int to = simulationConfiguration.getGenNumbers()/2;
        genotype.copyGens(0, to, parent);
        List<Gen> parentGens = parent.getAnimalGens();

        for (int i = 0; i < to; i++) {
            assertEquals(parentGens.get(i), genotype.getGens(i));
        }
    }

    @Test
    void testNextGen1() {
        Genotype genotype = new Genotype(3, 1);

        Gen gen0 = genotype.nextGen();
        Gen gen1 = genotype.nextGen();
        Gen gen2 = genotype.nextGen();
        Gen gen3 = genotype.nextGen();

        assertEquals(genotype.getGens(0), gen0);
        assertEquals(genotype.getGens(1), gen1);
        assertEquals(genotype.getGens(2), gen2);
        assertEquals(genotype.getGens(0), gen3);
    }

    @Test
    void testNextGen2() {
        Genotype genotype = new Genotype(3, 2);

        Gen gen0 = genotype.nextGen();
        Gen gen1 = genotype.nextGen();
        Gen gen2 = genotype.nextGen();
        Gen gen3 = genotype.nextGen();

        assertEquals(genotype.getGens(0), gen0);
        assertEquals(genotype.getGens(1), gen1);
        assertEquals(genotype.getGens(2), gen2);
        assertEquals(genotype.getGens(1), gen3);
    }

    @Test
    void testSetAndGetGens() {
        Genotype genotype = new Genotype(5, 1);

        Gen originalGen = genotype.getGens(2);
        genotype.setGens(2, Gen.DEGREES_0);

        assertEquals(Gen.DEGREES_0, genotype.getGens(2));
        assertNotEquals(originalGen, genotype.getGens(2));
    }
}
