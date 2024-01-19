package org.example.data;

import org.example.model.Animal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationStatistics {

    private final int statisticID;
    private final Map<Statistics, Double> mapStatistics = new HashMap<>();
    private final List<Animal> listAnimals;

    public SimulationStatistics(List<Animal> listAnimals, int statisticID) {
        this.statisticID = statisticID;
        this.listAnimals = listAnimals;
        for(Statistics statistics : Statistics.values()){
            mapStatistics.put(statistics,0.0);
        }
    }
    public void updateStatistic(){
        updateAnimalStatistic();
    }
    private void updateAnimalStatistic(){
        double numberOfChildren = 0;
        double animalsEnergy = 0;
        double animalsLife = 0;

        for(Animal animal : listAnimals){
            numberOfChildren += animal.getNumOfChildren();
            animalsEnergy += animal.getEnergy();
            animalsLife += animal.getAge();
        }
        mapStatistics.put(Statistics.NUMBER_OF_ANIMALS, (double) listAnimals.size());
        mapStatistics.put(Statistics.AVG_ANIMALS_ENERGY,(double) Math.round(animalsEnergy*100/listAnimals.size())/100);
        mapStatistics.put(Statistics.AVG_LENGTH_OF_LIFE,(double) Math.round(animalsLife*100/listAnimals.size())/100);
        mapStatistics.put(Statistics.AVG_NUMBER_OF_CHILDREN, (double) Math.round(numberOfChildren*100/listAnimals.size())/100);
    }
    public Map<Statistics, Double> getMapStatistics() {
        return mapStatistics;
    }
}
