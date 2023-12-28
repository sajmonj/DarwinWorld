package org.example;

import org.example.model.Gen;

import java.util.ArrayList;
import java.util.List;

//wydaje mi się że to będzie nie potrzebne
public class OptionsParser {
    public static List<Gen> convertOptions(int[] args){
        List<Gen> enumArgs = new ArrayList<>();
        for(int arg: args){
            Gen message = switch (arg){
                case 0 -> Gen.DEGREES_0;
                case 1 -> Gen.DEGREES_45;
                case 2 -> Gen.DEGREES_90;
                case 3 -> Gen.DEGREES_135;
                case 4 -> Gen.DEGREES_180;
                case 5 -> Gen.DEGREES_225;
                case 6 -> Gen.DEGREES_270;
                case 7 -> Gen.DEGREES_315;
                default -> throw new IllegalArgumentException(arg + " is not legal gen specification");
            };
            enumArgs.add(message);
        }
        return enumArgs;
    }
}