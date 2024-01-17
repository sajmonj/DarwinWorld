package org.example.model;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.max;


public class Genotype {

    private int currentGen = -1;
    private int genNumbers;
    private List<Gen> Gens;

    public Genotype(int genNumbers){
        newGenotype(genNumbers);
        System.out.println("place "+Gens.size() +" "+this.genNumbers);
        genotypeGenerate();
    }

    public Genotype (Animal a, Animal b){
        System.out.println("A "+a.getGenNumbers());
        newGenotype(a.getGenNumbers());
        inheritGenotype(a, b);
    }

    private void inheritGenotype(Animal a, Animal b) {
        Random random = new Random();
        int sumEnergy = a.getEnergy() + b.getEnergy();
        int numGensA = a.getEnergy()/sumEnergy*genNumbers;
        int numGensB = genNumbers-numGensA;
        int maxi = max(numGensA,numGensB);
        int side = random.nextInt(2);
        int point = side == 0 ? maxi : genNumbers-maxi;
        Animal first = numGensA > numGensB && side == 0 || (numGensA < numGensB && side == 1) ? a : b;
        Animal second = first.equals(a) ? b : a;
        copyGens(0,point, first);
        copyGens(point,genNumbers, second);
    }

    private void newGenotype(int genNumbers) {
        this.genNumbers = genNumbers;
        Gens = new ArrayList<>();
    }
    public void copyGens(int from, int to, Animal parent){
        System.out.println("L"+Gens.size());
        for (int i = from; i<to; ++i){
            System.out.println("I "+i);
            System.out.println( "P "+parent.getAnimalGenotype().getGens(i));
            Gens.add(parent.getAnimalGenotype().getGens(i));
        }
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

    public int getGenNumbers() {
        return genNumbers;
    }

    @Override
    public String toString() {
        return "Genotype{" + Gens + '}';
    }

    public Gen getGens(int i) {
        return Gens.get(i);
    }
}
