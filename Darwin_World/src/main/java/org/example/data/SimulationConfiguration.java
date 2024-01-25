package org.example.data;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SimulationConfiguration {
    private File file;
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
    private int speed;
    private int minMutations;
    private int maxMutations;
    private int mapType;
    private int genotype;
    private boolean toCSV;

    public void update(int mapHeight, int mapWidth, int animalsNumber, int genNumbers,
                       int animalEnergy, int readyEnergy, int reproductionEnergy, int grassInitNumber,
                       int grassNum, int grassEnergy, int minMutations, int maxMutations, int speed, int mapType,
                       int genotype, boolean toCSV) {

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
        this.speed = speed;
        this.minMutations = minMutations;
        this.maxMutations = maxMutations;
        this.mapType = mapType;
        this.genotype = genotype;
        this.toCSV = toCSV;
    }
    public void save() {
        String folderPath = "Config";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File folder = new File(folderPath);
            File[] files = folder.listFiles();
            assert files != null;
            File toSave = new File("Config/config"+ (files.length+1)+".json");

            objectMapper.writeValue(toSave, this);
            System.out.println("Configuration saved to JSON file: " + toSave.getName());
        } catch (IOException e) {
            System.err.println("Error while writing to JSON file: " + e.getMessage());
        }
    }

    public void load(File file) {
        this.file = file;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SimulationConfiguration loadedConfig = objectMapper.readValue(file, SimulationConfiguration.class);
            System.out.println("LoadedConfig: " + file.getName());
            update(loadedConfig.getMapHeight(), loadedConfig.getMapWidth(), loadedConfig.getAnimalsNumber(),
                    loadedConfig.getGenNumbers(), loadedConfig.getAnimalEnergy(), loadedConfig.getReadyEnergy(),
                    loadedConfig.getReproductionEnergy(), loadedConfig.getGrassInitNumber(), loadedConfig.getGrassNum(),
                    loadedConfig.getGrassEnergy(), loadedConfig.getMinMutations(), loadedConfig.getMaxMutations(),
                    loadedConfig.getSpeed(), loadedConfig.getMapType(), loadedConfig.getGenotype(), loadedConfig.getToCSV()
            );
            System.out.println("Configuration loaded from JSON file: " + file.getName());
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
    public int getSpeed() {
        return speed;
    }
    public int getMinMutations() {
        return  minMutations;
    }
    public int getMaxMutations() {
        return maxMutations;
    }
    public int getMapType() {
        return mapType;
    }
    public int getGenotype() {
        return genotype;
    }
    public boolean getToCSV() {
        return toCSV;
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
                ", minMutations='" + minMutations + '\'' +
                ", maxMutations='" + maxMutations + '\'' +
                ", mapType='" + mapType + '\'' +
                ", genotype='" + genotype + '\'' +
                '}';
    }
}
