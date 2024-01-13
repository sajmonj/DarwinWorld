package org.example.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class Configuration {
    private static final String DEFAULT_FILE_PATH = "config.json";
    private final TextField mapHeight;
    private final TextField mapWidth;
    private final TextField animalsNumber;
    private final TextField genNumbers;
    private final TextField animalEnergy;
    private final TextField readyEnergy;
    private final TextField reproductionEnergy;
    private final TextField grassNum;
    private final TextField grassEnergy;

    public Configuration(TextField mapHeight, TextField mapWidth, TextField animalsNumber, TextField genNumbers,
                         TextField animalEnergy, TextField readyEnergy, TextField reproductionEnergy, TextField grassNum, TextField grassEnergy) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.animalsNumber = animalsNumber;
        this.genNumbers = genNumbers;
        this.animalEnergy = animalEnergy;
        this.readyEnergy = readyEnergy;
        this.reproductionEnergy = reproductionEnergy;
        this.grassNum = grassNum;
        this.grassEnergy = grassEnergy;
    }
    public void saveToJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(DEFAULT_FILE_PATH), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadFromJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Configuration loadedConfig = objectMapper.readValue(new File(DEFAULT_FILE_PATH), Configuration.class);

            mapHeight.setText(loadedConfig.mapHeight.getText());
            mapWidth.setText(loadedConfig.mapWidth.getText());
            animalsNumber.setText(loadedConfig.animalsNumber.getText());
            genNumbers.setText(loadedConfig.genNumbers.getText());
            animalEnergy.setText(loadedConfig.animalEnergy.getText());
            readyEnergy.setText(loadedConfig.readyEnergy.getText());
            reproductionEnergy.setText(loadedConfig.reproductionEnergy.getText());
            grassNum.setText(loadedConfig.grassNum.getText());
            grassEnergy.setText(loadedConfig.grassEnergy.getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateGUI() {
        mapHeight.setText(mapHeight.getText());
        mapWidth.setText(mapWidth.getText());
        animalsNumber.setText(animalsNumber.getText());
        genNumbers.setText(genNumbers.getText());
        animalEnergy.setText(animalEnergy.getText());
        readyEnergy.setText(readyEnergy.getText());
        reproductionEnergy.setText(reproductionEnergy.getText());
        grassNum.setText(grassNum.getText());
        grassEnergy.setText(grassEnergy.getText());
    }
}
