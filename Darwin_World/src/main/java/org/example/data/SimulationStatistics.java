package org.example.data;

import org.example.model.Animal;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationStatistics {

    private final int statisticID;
    private final Map<Statistics, Double> mapStatistics = new HashMap<>();
    private final List<Animal> listAnimals;

    public SimulationStatistics(List<Animal> listAnimals, int statisticID, int mapType, int genotype) {
        this.statisticID = statisticID;
        this.listAnimals = listAnimals;
        for(Statistics statistics : Statistics.values()){
            mapStatistics.put(statistics,0.0);
        }

        mapStatistics.put(Statistics.MAP_TYPE, (double)mapType);
        mapStatistics.put(Statistics.GENOM_TYPE, (double)genotype);
    }
    public void updateStatistic(int day){
        updateAnimalStatistic(day);
    }
    private void updateAnimalStatistic(int day){
        double numberOfChildren = 0;
        double animalsEnergy = 0;
        double animalsLife = 0;
        double numOfLivingAnimals = 0;

        for(Animal animal : listAnimals){
            if(animal.getDayOfDeath().isEmpty()) {
                animalsEnergy += animal.getEnergy();
                numberOfChildren += animal.getNumOfChildren();
                numOfLivingAnimals++;
            }
            else animalsLife += animal.getAge();
        }
        mapStatistics.put(Statistics.DAY, (double) day);
        mapStatistics.put(Statistics.NUMBER_OF_ALL_ANIMALS, (double) listAnimals.size());
        mapStatistics.put(Statistics.NUMBER_OF_LIVING_ANIMALS, (double) numOfLivingAnimals);
        mapStatistics.put(Statistics.AVG_ANIMALS_ENERGY,(double) Math.round(animalsEnergy*100/numOfLivingAnimals)/100);
        mapStatistics.put(Statistics.AVG_LENGTH_OF_LIFE,(double) Math.round(animalsLife*100/listAnimals.size())/100);
        mapStatistics.put(Statistics.AVG_NUMBER_OF_CHILDREN, (double) Math.round(numberOfChildren*100/numOfLivingAnimals)/100);
    }

    public Map<Statistics, Double> getMapStatistics() {
        return mapStatistics;
    }
}
