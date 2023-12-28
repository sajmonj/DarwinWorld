package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Genotype {

    private int currentGen = -1;
    private final int genNumbers;
    List<Gen> Gens = new ArrayList<>();
    public Genotype(int genNumbers){
        this.genNumbers = genNumbers;
        Gens.add(Gen.DEGREES_0);
        Gens.add(Gen.DEGREES_45);
        Gens.add(Gen.DEGREES_180);
        Gens.add(Gen.DEGREES_225);
        Gens.add(Gen.DEGREES_315);
    }
    public Gen nextGen(){
        currentGen += 1;
        return Gens.get(currentGen%genNumbers);
    }
}
