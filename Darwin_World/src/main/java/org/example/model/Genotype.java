package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Genotype {
    List<Gen> Gens = new ArrayList<>();
    public Genotype(int genNumbers){
        Gens.add(Gen.DEGREES_0);
        Gens.add(Gen.DEGREES_45);
        Gens.add(Gen.DEGREES_180);
        Gens.add(Gen.DEGREES_225);
        Gens.add(Gen.DEGREES_315);
    }
}
