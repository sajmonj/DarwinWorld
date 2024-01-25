package org.example.data;

import javafx.application.Platform;
import org.example.model.Animal;
import org.example.model.Gen;
import org.example.model.Genotype;
import org.yaml.snakeyaml.events.Event;
import org.example.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationStatistics {
    private final String filePath;
    private final int statisticID;
    private final Map<List<Gen>, Integer> mapGens = new HashMap<>();
    private final Map<Statistics, String> mapStatistics = new HashMap<>();
    private final List<Animal> listAnimals;
    private List<Gen> popularGens = null;
    private int counterGenotype = 0;
    private FileWriter writer;
    private final Set<Grass> grassSet;

    public SimulationStatistics(List<Animal> listAnimals, Set<Grass> grassSet, int statisticID, int mapType, int genotype) {
        this.statisticID = statisticID;
        this.listAnimals = listAnimals;
        this.grassSet = grassSet;
        for(Statistics statistics : Statistics.values()){
            mapStatistics.put(statistics,"-");
        }
        filePath = "Statistics/statistics" + statisticID+".csv";

        mapStatistics.put(Statistics.MAP_TYPE, String.valueOf(mapType));
        mapStatistics.put(Statistics.GENOM_TYPE, String.valueOf(genotype));
    }
    public void updateStatistic(int day, WorldMap worldMap){
        double numberOfChildren = 0;
        double animalsEnergy = 0;
        double animalsLife = 0;
        int numOfLivingAnimals = 0;
        double freeFields = 0;
        counterGenotype = 0;
        mapGens.clear();

        for(Animal animal : listAnimals){
            if(animal.getDayOfDeath().isEmpty()) {
                animalsEnergy += animal.getEnergy();
                numberOfChildren += animal.getNumOfChildren();
                numOfLivingAnimals++;
                updateGenotype(animal.getAnimalGenotype());
            }
            else {
                animalsLife += animal.getAge();
            }
        }

        for (int x = 1; x <= worldMap.getCurrentBounds().upperRight().x(); x++) {
            for (int y = 1; y <= worldMap.getCurrentBounds().upperRight().y(); y++) {
                if (!worldMap.isOccupied(new Vector2d(x,y)))
                    freeFields++;
            }
        }

        updateDisplayingStatistics(day, numOfLivingAnimals, freeFields, animalsEnergy, animalsLife, numberOfChildren);
    }

    public void updateDisplayingStatistics(int day, int numOfLivingAnimals, double freeFields, double animalsEnergy, double animalsLife, double numberOfChildren){
        Platform.runLater(() -> {
            mapStatistics.put(Statistics.DAY, String.valueOf(day));
            mapStatistics.put(Statistics.NUMBER_OF_ALL_ANIMALS, String.valueOf(listAnimals.size()));
            mapStatistics.put(Statistics.NUMBER_OF_LIVING_ANIMALS, String.valueOf(numOfLivingAnimals));
            mapStatistics.put(Statistics.FIELD_WITH_GRASS, String.valueOf(grassSet.size()));
            mapStatistics.put(Statistics.FREE_FIELDS, String.valueOf(freeFields));
            mapStatistics.put(Statistics.MOST_POPULAR_GENOTYPE, popularGens.toString().replaceAll("[]\\[]", "").replace(",", "    "));
            mapStatistics.put(Statistics.AVG_ANIMALS_ENERGY, String.valueOf((double) Math.round(animalsEnergy*100/numOfLivingAnimals)/100));
            mapStatistics.put(Statistics.AVG_LENGTH_OF_LIFE, String.valueOf((double)Math.round(animalsLife*100/listAnimals.size())/100));
            mapStatistics.put(Statistics.AVG_NUMBER_OF_CHILDREN, String.valueOf((double)Math.round(numberOfChildren*100/numOfLivingAnimals)/100));
        });
        }

    private void updateGenotype(Genotype animalGenotype) {
        List<Gen> animalGens = animalGenotype.getGens();
        int tempCounterGenotype = mapGens.containsKey(animalGens) ? mapGens.get(animalGens)+1: 1;
        mapGens.put(animalGens, tempCounterGenotype);
        if(tempCounterGenotype > counterGenotype){
            counterGenotype = tempCounterGenotype;
            popularGens = animalGenotype.getGens();
        }
    }

    public void saveStatisticsToCsv() {
        try {
            writer.write(CsvGenerator.generateCsvLine(mapStatistics) + "\n");
        } catch (IOException e) {
            System.err.println("Statistics have not been added to: " + filePath);
            e.printStackTrace();
        }
    }

    public void openFile() {
        try{
            writer = new FileWriter(filePath);
            writer.write(Statistics.getHeaders() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeFile(){
        try {
            if (writer != null) {
                writer.close();
                System.out.println("File is closed: "+filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Gen> getPopularGens() {
        return popularGens;
    }

    public Map<Statistics, String> getMapStatistics() {
        return mapStatistics;
    }
}
