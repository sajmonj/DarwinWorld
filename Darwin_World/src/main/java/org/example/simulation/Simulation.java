package org.example.simulation;

import org.example.data.SimulationConfiguration;
import org.example.model.*;


import java.util.List;

public class Simulation extends SuperSimulation implements Runnable {
    private final DayCycleSimulation dayCycleSimulation;
    private volatile boolean shouldStop = false;
    private Thread thread = null;

    public Simulation(SimulationConfiguration configuration, WorldMap worldMap, int ID) {
        super(configuration, worldMap, ID);
        for(int i=0; i<configuration.getAnimalsNumber(); ++i){
            Animal animal = new Animal(configuration,i);
            listAnimals.add(animal);
            this.map.place(animal);
        }
        GrassPositionGenerator grassPositionGenerator = new GrassPositionGenerator(grassInitNum, map.getCurrentBounds(), map);
        for (Vector2d position : grassPositionGenerator) {
            Grass grass = new Grass(position);
            setGrass.add(grass);
            this.map.place(grass);
        }
        dayCycleSimulation = new DayCycleSimulation(listAnimals, setGrass, configuration, worldMap, ID);
    }

    @Override
    public void run() {
        try {
            thread = Thread.currentThread();
            StartSimulation();
        } catch (RuntimeException e) {
            System.err.println("Simulation interrupted!");
        }
    }

    private void StartSimulation() {
        try{
            while (!Thread.currentThread().isInterrupted()){
                if (!shouldStop){
                    dayCycleSimulation.removeDeadAnimals(day);
                    dayCycleSimulation.move();
                    dayCycleSimulation.consumption();
                    dayCycleSimulation.reproduction();
                    dayCycleSimulation.grassGrowth();
                    Thread.sleep(super.getSpeed());
                    map.mapChanged(day);
                    day++;
                }
            }
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            simulationStatistics.closeFile();
        }
    }

    public void stopSimulation() {
        shouldStop = true;
    }
    public void continueSimulation() {
        shouldStop = false;
    }

    public boolean isShouldStop() {
        return shouldStop;
    }

    public void endSimulation() {
        thread.interrupt();
    }
}