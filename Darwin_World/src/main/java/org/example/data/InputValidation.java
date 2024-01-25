package org.example.data;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class InputValidation {

    private static final String POSITIVE = "-fx-background-color: #a3ef8d;";
    private static final String NEGATIVE = "-fx-background-color: #efa5a5;";
    public static boolean inputValidation(TextField mapHeight, TextField mapWidth, TextField animalsNumber,
                                          TextField genNumbers, TextField animalEnergy, TextField readyEnergy,
                                          TextField reproductionEnergy, TextField grassInitNum, TextField grassNum,
                                          TextField grassEnergy, Integer selectedMapType, Button earth, Button hellPortal,
                                          Integer selectedGenotype, Button genotype, Button backAndForward,
                                          Spinner<Integer> minMutations, Spinner<Integer> maxMutations) {
        boolean validation = true;
        if(check(mapHeight,3,61)){
            mapHeight.setStyle(POSITIVE);
        }
        else{
            mapHeight.setStyle(NEGATIVE);
            validation = false;
        }

        if(check(mapWidth,3,61)){
            mapWidth.setStyle(POSITIVE);
        }
        else {
            mapWidth.setStyle(NEGATIVE);
            validation = false;
        }

        if(check(animalsNumber,0,201)){
            animalsNumber.setStyle(POSITIVE);
        }
        else {
            animalsNumber.setStyle(NEGATIVE);
            validation = false;
        }
        if(check(genNumbers,2,11)){
            genNumbers.setStyle(POSITIVE);
        }
        else {
            genNumbers.setStyle(NEGATIVE);
            validation = false;
        }

        if(check(animalEnergy,1,101)){
            animalEnergy.setStyle(POSITIVE);
        }
        else {
            animalEnergy.setStyle(NEGATIVE);
            validation = false;
        }

        if(check(readyEnergy,1,101)){
            readyEnergy.setStyle(POSITIVE);
        }
        else {
            readyEnergy.setStyle(NEGATIVE);
            validation = false;
        }

        if(check(readyEnergy,1,101) && check(reproductionEnergy,1,Integer.parseInt(readyEnergy.getText()))){
            reproductionEnergy.setStyle(POSITIVE);
        }
        else {
            reproductionEnergy.setStyle(NEGATIVE);
            validation = false;
        }

        if(check(grassInitNum,0,3601)){
            grassInitNum.setStyle(POSITIVE);
        }
        else {
            grassInitNum.setStyle(NEGATIVE);
            validation = false;
        }

        if(check(grassNum,0,501)){
            grassNum.setStyle(POSITIVE);
        }
        else {
            grassNum.setStyle(NEGATIVE);
            validation = false;
        }

        if(check(grassEnergy,0,101)){
            grassEnergy.setStyle(POSITIVE);
        }
        else {
            grassEnergy.setStyle(NEGATIVE);
            validation = false;
        }

        if(selectedMapType == null){
            earth.setStyle(NEGATIVE);
            hellPortal.setStyle(NEGATIVE);
            validation = false;
        }
        if(selectedGenotype == null){
            genotype.setStyle(NEGATIVE);
            backAndForward.setStyle(NEGATIVE);
            validation = false;
        }
        SpinnerValueFactory<Integer> valueMin = minMutations.getValueFactory();
        SpinnerValueFactory<Integer> valueMax = maxMutations.getValueFactory();

        if(valueMin.getValue() > valueMax.getValue()){
            validation = false;
        }

        return validation;
    }
    private static boolean check(TextField textField, int minValue, int maxValue){
        return !(textField.getText().isEmpty() ||
                (Integer.parseInt(textField.getText()) < minValue || Integer.parseInt(textField.getText()) > maxValue));
    }
}
