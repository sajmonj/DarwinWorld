package org.example.data;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SimulationConfiguration {
    private static final String FILE_PATH = "config.json";
    private int mapHeight;
    private int mapWidth;
    private int animalsNumber;
    private int genNumbers;
    private int animalEnergy;
    private int readyEnergy;
    private int reproductionEnergy;
    private int grassInitNumber;
    private int grassNum;
    private int grassEnergy;
//    public SimulationConfiguration(int mapHeight, int mapWidth, int animalsNumber, int genNumbers,
//                                   int animalEnergy, int readyEnergy, int reproductionEnergy,
//                                   int grassNum, int grassEnergy) {
//        Update(mapHeight, mapWidth, animalsNumber, genNumbers, animalEnergy, readyEnergy, reproductionEnergy, grassNum, grassEnergy);
//    }

    public void update(int mapHeight, int mapWidth, int animalsNumber, int genNumbers,
                       int animalEnergy, int readyEnergy, int reproductionEnergy, int grassInitNumber,
                       int grassNum, int grassEnergy) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.animalsNumber = animalsNumber;
        this.genNumbers = genNumbers;
        this.animalEnergy = animalEnergy;
        this.readyEnergy = readyEnergy;
        this.reproductionEnergy = reproductionEnergy;
        this.grassInitNumber = grassInitNumber;
        this.grassNum = grassNum;
        this.grassEnergy = grassEnergy;
    }
    public void save() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(FILE_PATH), this);
            System.out.println("Configuration saved to JSON file: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error while writing to JSON file: " + e.getMessage());
        }
    }
    public void load() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SimulationConfiguration loadedConfig = objectMapper.readValue(new File(FILE_PATH), SimulationConfiguration.class);
            System.out.println("LoadedConfig" + loadedConfig.getGrassInitNumber());
            update(loadedConfig.getMapHeight(), loadedConfig.getMapWidth(), loadedConfig.getAnimalsNumber(),
                    loadedConfig.getGenNumbers(), loadedConfig.getAnimalEnergy(), loadedConfig.getReadyEnergy(),
                    loadedConfig.getReproductionEnergy(), loadedConfig.getGrassInitNumber(), loadedConfig.getGrassNum(),
                    loadedConfig.getGrassEnergy()
            );
            System.out.println("Configuration loaded from JSON file: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error while reading from JSON file: " + e.getMessage());
        }
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getAnimalsNumber() {
        return animalsNumber;
    }

    public int getGenNumbers() {
        return genNumbers;
    }

    public int getAnimalEnergy() {
        return animalEnergy;
    }

    public int getReadyEnergy() { return readyEnergy; }

    public int getReproductionEnergy() {
        return reproductionEnergy;
    }

    public int getGrassInitNumber() {
        return grassInitNumber;
    }

    public int getGrassNum() {
        return grassNum;
    }

    public int getGrassEnergy() {
        return grassEnergy;
    }
    @Override
    public String toString() {
        return "SimulationConfiguration{" +
                "mapHeight='" + mapHeight + '\'' +
                ", mapWidth='" + mapWidth + '\'' +
                ", animalsNumber='" + animalsNumber + '\'' +
                ", genNumbers='" + genNumbers + '\'' +
                ", animalEnergy='" + animalEnergy + '\'' +
                ", readyEnergy='" + readyEnergy + '\'' +
                ", reproductionEnergy='" + reproductionEnergy + '\'' +
                ", grassInitNumber='" + grassInitNumber + '\'' +
                ", grassNum='" + grassNum + '\'' +
                ", grassEnergy='" + grassEnergy + '\'' +
                '}';
    }
}
