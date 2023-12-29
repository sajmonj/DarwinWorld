package org.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class Genotype {

    private int currentGen = -1;
    private final int genNumbers;
    List<Gen> Gens = new ArrayList<>();
    public Genotype(int genNumbers){
        this.genNumbers = genNumbers;
        genotypeGenerate();
    }
    private void genotypeGenerate(){
        Random random = new Random();
        List<Gen> values = Arrays.asList(Gen.values());

        IntStream.range(0, genNumbers).forEach(i -> Gens.add(values.get(random.nextInt(values.size()))));
    }
    public Gen nextGen(){
        currentGen += 1;
        return Gens.get(currentGen%genNumbers);
    }

    @Override
    public String toString() {
        return "Genotype{" + Gens + '}';
    }
}
