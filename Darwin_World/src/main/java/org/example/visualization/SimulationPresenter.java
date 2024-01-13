package org.example.visualization;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.example.model.*;
import org.example.simulation.Simulation;
import org.example.data.SimulationConfiguration;
import org.example.simulation.SimulationEngine;


public class SimulationPresenter implements MapChangeListener {
    private static final int CELL_SIZE = 50;
    @FXML
    private GridPane mapGrid;
    private WorldMap worldMap;
    private SimulationConfiguration configuration;

    private void drawMap(WorldMap worldMap){
        System.out.println("555");
        clearGrid();
        mapGrid.setGridLinesVisible(true);
        Boundary currentBounds = worldMap.getCurrentBounds();
        int cols = Math.abs(currentBounds.upperRight().getX()-currentBounds.lowerLeft().getX())+2;
        int rows = Math.abs(currentBounds.upperRight().getY()-currentBounds.lowerLeft().getY())+2;
        Vector2d currentPosition = new Vector2d(currentBounds.lowerLeft().getX(),currentBounds.upperRight().getY());
        addCells(cols, rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Vector2d addVector = new Vector2d(i-1,-j+1);
                Label label = new Label();
                if (i == 0 || j == 0) {
                    setAxis(i, j, label, currentPosition.add(addVector));
                }
                else if(worldMap.isOccupied(currentPosition.add(addVector))){
                    label.setText(worldMap.objectAt(currentPosition.add(addVector)).toString());
                }
                GridPane.setHalignment(label, HPos.CENTER);
                mapGrid.add(label, i, j);
            }
        }
    }

    private static void setAxis(int i, int j, Label label, Vector2d currentPosition) {
        if(i == j){
            label.setText("y/x");
        } else if (i == 0) {
            label.setText(currentPosition.yToString());
        }else {
            label.setText(currentPosition.xToString());
        }
    }

    private void addCells(int cols, int rows) {
        System.out.println("666");
        for (int i = 0; i < cols; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        for (int i = 0; i < rows; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
    private static int simulationID = 0;
    public void startSimulationPresenter(SimulationConfiguration configuration, String mapType) {
        System.out.println("111");
        setOptions(configuration, mapType);
        increaseID();
        System.out.println(simulationID);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane simulationWindow = loader.load();

            SimulationPresenter simulationPresenter = loader.getController();

            ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
            worldMap.registerObserver(consoleMapDisplay);
            Simulation simulation = new Simulation(configuration.getAnimalsNumber(), configuration.getGenNumbers(), worldMap,
                    configuration.getAnimalEnergy(), configuration.getReadyEnergy(), configuration.getReproductionEnergy(),
                    configuration.getGrassEnergy(), configuration.getGrassNum());
            List<Simulation> simulations = List.of(simulation);

            SimulationEngine simulationEngine = new SimulationEngine(simulations);
            simulationEngine.runAsyncInThreadPool();

            Stage stage = new Stage();
            stage.setTitle("Simulation " + worldMap.getId());

            Scene scene = new Scene(simulationWindow);
            stage.setScene(scene);

            stage.show();
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setOptions(SimulationConfiguration configuration, String mapType) {
        //        Kiedyś bedzie działać lepiej
        if(Objects.equals(mapType, "RectangularMap")){
            worldMap = new RectangularMap(configuration.getMapWidth(), configuration.getMapHeight(),1);
        }else {
            worldMap = new RectangularMap(configuration.getMapWidth(), configuration.getMapHeight(),2);
        }
        worldMap.registerObserver(this);
        this.configuration = configuration;
    }

    private void increaseID() {
        synchronized (this){
            simulationID += 1;
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("111");
        Platform.runLater(() -> {
            drawMap(worldMap);
        });
    }
}
