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
    }

    public void addAndRunSimulationToThreadPool(Simulation simulation) {
        threadPool.submit(simulation);
    }


    public void interruptProgram(){
        threadPool.shutdownNow();
        for (Thread simulationThread : simulationsThread) {
            simulationThread.interrupt();
        }
    }
}