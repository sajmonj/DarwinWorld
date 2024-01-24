package org.example.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    private final List<Simulation> simulations;
    private final List<Thread> simulationsThread;
    private final ExecutorService threadPool;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.simulationsThread = new ArrayList<>();
        this.threadPool = Executors.newFixedThreadPool(4);
//        runAsyncInThreadPool();
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread simulationThread = new Thread(simulation);
            simulationsThread.add(simulationThread);
            simulationThread.start();
        }
    }
    public void runAsyncInThreadPool() {
        for (Simulation simulation : simulations) {
            threadPool.submit(simulation);
        }
    }

    public void addAndRunSimulationToThreadPool(Simulation simulation) {
        threadPool.submit(simulation);
        System.out.println(Thread.currentThread());
    }

    public void awaitSimulationsEnd(){
        try {
            for (Thread simulationThread : simulationsThread) {
                simulationThread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        threadPool.shutdown();

        try {
            threadPool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public void interruptSimulation(){
        threadPool.shutdownNow();
        for (Thread simulationThread : simulationsThread) {
            simulationThread.interrupt();
        }
    }

    public void endSimulation(){
        System.out.println(Thread.currentThread());
       Thread.currentThread().interrupt();
    }
}