package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationsGenerator {
    int minMutations;
    int maxMutations;
    int genNumbers;
    Genotype genotype;
    Random random = new Random();

    public MutationsGenerator(int minMutations, int maxMutations, int genNumbers, Genotype genotype) {
        this.minMutations = minMutations;
        this.maxMutations = maxMutations;
        this.genNumbers = genNumbers;
        this.genotype = genotype;
    }

    public Genotype mutatedGenotype() {
        int numberOfMutatedGens = numberOfMutations();
        List<Integer> gensSelectedToMutation = selectedGens(numberOfMutatedGens);
        Gen[] gens = Gen.values();

        gensSelectedToMutation.forEach(gen -> {
            int rand = random.nextInt(gens.length);
            while (genotype.getGens(gen) == gens[rand]) {
                rand = random.nextInt(gens.length);
            }
            genotype.setGens(gen, gens[rand]);
        });
        return genotype;
    }

    private List<Integer> selectedGens(int numberOfMutatedGens) {
        List<Integer> gensIndexes = new ArrayList<>();
        List<Integer> gensSelectedToMutation = new ArrayList<>();

        for (int index=0; index < genNumbers; index++) {
            gensIndexes.add(index);
        }
        for (int i=0; i < numberOfMutatedGens; i++) {
            int selectedIndex = random.nextInt(gensIndexes.size());
            gensSelectedToMutation.add(gensIndexes.get(selectedIndex));
            gensIndexes.remove(selectedIndex);
        }
        return gensSelectedToMutation;
    }

    private int numberOfMutations() {
        return minMutations + random.nextInt(maxMutations-minMutations+1);
    }


}
