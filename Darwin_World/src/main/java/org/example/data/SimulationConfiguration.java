package org.example.data;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SimulationConfiguration {
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

    public void update(Integer mapHeight, Integer mapWidth, Integer animalsNumber, Integer genNumbers,
                       Integer animalEnergy, Integer readyEnergy, Integer reproductionEnergy, Integer grassInitNumber,
                       Integer grassNum, Integer grassEnergy, Integer minMutations, Integer maxMutations, Integer speed, Integer mapType,
                       Integer genotype, boolean toCSV) {

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

    public Integer getMapHeight() {
        return mapHeight;
    }
    public Integer getMapWidth() {
        return mapWidth;
    }
    public Integer getAnimalsNumber() {
        return animalsNumber;
    }
    public Integer getGenNumbers() {
        return genNumbers;
    }
    public Integer getAnimalEnergy() {
        return animalEnergy;
    }
    public Integer getReadyEnergy() {
        return readyEnergy;
    }
    public Integer getReproductionEnergy() {
        return reproductionEnergy;
    }
    public Integer getGrassInitNumber() {
        return grassInitNumber;
    }
    public Integer getGrassNum() {
        return grassNum;
    }
    public Integer getGrassEnergy() {
        return grassEnergy;
    }
    public Integer getSpeed() {
        return speed;
    }
    public Integer getMinMutations() {
        return  minMutations;
    }
    public Integer getMaxMutations() {
        return maxMutations;
    }
    public Integer getMapType() {
        return mapType;
    }
    public Integer getGenotype() {
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
