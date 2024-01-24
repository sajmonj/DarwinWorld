package org.example.data;

import org.example.model.Animal;
import org.example.model.Gen;
import org.example.model.Genotype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationStatistics {

    private final Map<List<Gen>, Integer> mapGens = new HashMap<>();
    private List<Gen> popularGens = null;
    private int counterGenotype = 0;
    private final int statisticID;
    private final Map<Statistics, String> mapStatistics = new HashMap<>();
    private final List<Animal> listAnimals;

    public SimulationStatistics(List<Animal> listAnimals, int statisticID, int mapType, int genotype) {
        this.statisticID = statisticID;
        this.listAnimals = listAnimals;
        for(Statistics statistics : Statistics.values()){
            mapStatistics.put(statistics,"-");
        }

        mapStatistics.put(Statistics.MAP_TYPE, String.valueOf(mapType));
        mapStatistics.put(Statistics.GENOM_TYPE, String.valueOf(genotype));
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
                updateGenotype(animal.getAnimalGenotype());
            }
            else {
                animalsLife += animal.getAge();
            }
        }
        mapStatistics.put(Statistics.DAY, String.valueOf(day));
        mapStatistics.put(Statistics.NUMBER_OF_ALL_ANIMALS, String.valueOf(listAnimals.size()));
        mapStatistics.put(Statistics.NUMBER_OF_LIVING_ANIMALS, String.valueOf(numOfLivingAnimals));
        mapStatistics.put(Statistics.MOST_POPULAR_GENOTYPE, popularGens.toString());
        mapStatistics.put(Statistics.AVG_ANIMALS_ENERGY, String.valueOf((double) Math.round(animalsEnergy*100/numOfLivingAnimals)/100));
        mapStatistics.put(Statistics.AVG_LENGTH_OF_LIFE, String.valueOf((double)Math.round(animalsLife*100/listAnimals.size())/100));
        mapStatistics.put(Statistics.AVG_NUMBER_OF_CHILDREN, String.valueOf((double)Math.round(numberOfChildren*100/numOfLivingAnimals)/100));
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

    public List<Gen> getPopularGens() {
        return popularGens;
    }

    public Map<Statistics, String> getMapStatistics() {
        return mapStatistics;
    }
}
