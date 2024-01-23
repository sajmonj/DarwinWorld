package org.example.model;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.max;


public class Genotype {
    private final int selectedType;
    private int currentGen;
    private int genNumbers;
    private int genotypeDirection;
    private List<Gen> Gens;

    public Genotype(int genNumbers, int genotype){
        selectedType = genotype;
        genotypeDirection = genotype == 1 ? 1 : -1;
        currentGen = genotype == 1 ? -1 : 1;
        newGenotype(genNumbers);
        genotypeGenerate();
    }

    public Genotype (Animal a, Animal b, int genotype){
        selectedType = genotype;
        genotypeDirection = genotype == 1 ? 1 : -1;
        newGenotype(a.getGenNumbers());
        inheritGenotype(a, b);
    }

    private void inheritGenotype(Animal firstAnimal, Animal secondAnimal) {
        Random random = new Random();
        int sumEnergy = firstAnimal.getEnergy() + secondAnimal.getEnergy();
        int numGensA = firstAnimal.getEnergy()*genNumbers/sumEnergy;
        int numGensB = genNumbers-numGensA;
        int maxi = max(numGensA,numGensB);
        int side = random.nextInt(2);
        int point = side == 0 ? maxi : genNumbers-maxi;
        Animal first = numGensA > numGensB && side == 0 || (numGensA < numGensB && side == 1) ? firstAnimal : secondAnimal;
        Animal second = first.equals(firstAnimal) ? secondAnimal : firstAnimal;
        copyGens(0,point, first);
        copyGens(point,genNumbers, second);
    }

    private void newGenotype(int genNumbers) {
        this.genNumbers = genNumbers;
        Gens = new ArrayList<>();
    }
    public void copyGens(int from, int to, Animal parent){
        for (int i = from; i<to; ++i){
            Gens.add(parent.getAnimalGenotype().getGens(i));
        }
    }
    private void genotypeGenerate(){
        Random random = new Random();
        List<Gen> values = Arrays.asList(Gen.values());

        IntStream.range(0, genNumbers).forEach(i -> Gens.add(values.get(random.nextInt(values.size()))));
    }
    public Gen nextGen(){
        if(selectedType == 2 && (currentGen == 0 || currentGen == genNumbers-1)) {
            genotypeDirection *= (-1);
        }
        currentGen += genotypeDirection;
        return Gens.get(currentGen%genNumbers);
    }

    public int getGenNumbers() {
        return genNumbers;
    }

    @Override
    public String toString() {
        return Gens.toString();
    }

    public Gen getGens(int i) {
        return Gens.get(i);
    }
}
