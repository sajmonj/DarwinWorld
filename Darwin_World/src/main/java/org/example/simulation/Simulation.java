package org.example.simulation;

import org.example.model.*;


import java.util.List;

public class Simulation extends AbstractSimulation implements Runnable{
    private final DayCycleSimulation dayCycleSimulation;
    private volatile boolean shouldStop = false;

    public Simulation(int animalsNumber,int genNumbers, WorldMap map, int animalEnergy, int readyEnergy,
                      int reproductionEnergy, int grassEnergy, int grassNum, int ID) {
        super(map, animalEnergy, reproductionEnergy, grassEnergy, readyEnergy, grassNum, ID);
        for(int i=0; i<animalsNumber; ++i){
            Animal animal = new Animal(genNumbers, i, animalEnergy);
            listAnimals.add(animal);
            this.map.place(animal);
        }
        GrassPositionGenerator grassPositionGenerator = new GrassPositionGenerator(grassNum, map.getCurrentBounds(), map);
        for (Vector2d position : grassPositionGenerator) {
            Grass grass = new Grass(position);
            setGrass.add(grass);
            this.map.place(grass);
        }
        dayCycleSimulation = new DayCycleSimulation(listAnimals, setGrass, map, animalEnergy, reproductionEnergy,
                grassEnergy, readyEnergy, grassNum, ID);
    }

    @Override
    public void run() {
        StartSimulation();
    }

    private void StartSimulation() {
        while (!shouldStop){
            dayCycleSimulation.removeDeadAnimals();
            dayCycleSimulation.move();
            dayCycleSimulation.consumption();
            dayCycleSimulation.reproduction();
            dayCycleSimulation.grassGrowth();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopSimulation() {
        shouldStop = true;
    }
    public void continueSimulation(){
        shouldStop = false;
        StartSimulation();
    }
    public List<Animal> getAnimalsList() {
        return listAnimals;
    }
}