package org.example.visualization;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import org.example.data.SimulationStatistics;
import org.example.data.Statistics;
import org.example.model.*;
import org.example.simulation.Simulation;
import org.example.data.SimulationConfiguration;
import org.example.simulation.SimulationEngine;


public class SimulationPresenter implements MapChangeListener {
    private static final int CELL_SIZE = 15;
    private static final List<Simulation> simulations = new ArrayList<>();
    private static int simulationID = 0;
    private Simulation simulation;
    private SimulationStatistics simulationStatistics;
    @FXML
    private GridPane mapGrid;
    @FXML
    private Label mapType;
    @FXML
    private Label genomType;
    @FXML
    private Label day;
    @FXML
    private Label allAnimals;
    @FXML
    private Label livingAnimals;
    @FXML
    private Label grass;
    @FXML
    private Label freeFields;
    @FXML
    private Label genotype;
    @FXML
    private Label avgEnergy;
    @FXML
    private Label avgLife;
    @FXML
    private Label avgChildren;
    @FXML
    private Label animalId;
    @FXML
    private Label lengthOfLife;
    @FXML
    private Label energy;
    @FXML
    private Label numOfChildren;
    @FXML
    private Label deathDate;

    private WorldMap worldMap;
    private SimulationConfiguration configuration;
    private final Map<Statistics, Label> mapLabelStatistics = new HashMap<>();

    private Animal chosen = null;

    public int startSimulationPresenter(SimulationConfiguration configuration, int mapType) {
        setOptions(configuration, mapType);
        runStatistics();
        increaseID();
        try {
            simulation = new Simulation(configuration, worldMap, simulationID);
            simulationStatistics = simulation.getSimulationStatistics();

            simulations.add(simulation);

            SimulationEngine simulationEngine = new SimulationEngine(simulations);
            simulationEngine.runAsyncInThreadPool();


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return simulation.getID();
    }

    public void runStatistics() {
        mapLabelStatistics.put(Statistics.DAY, day);
        mapLabelStatistics.put(Statistics.MAP_TYPE, mapType);
        mapLabelStatistics.put(Statistics.GENOM_TYPE, genomType);
        mapLabelStatistics.put(Statistics.NUMBER_OF_ALL_ANIMALS, allAnimals);
        mapLabelStatistics.put(Statistics.NUMBER_OF_LIVING_ANIMALS, livingAnimals);
        mapLabelStatistics.put(Statistics.FIELD_WITH_GRASS, grass);
        mapLabelStatistics.put(Statistics.FREE_FIELDS, freeFields);
        mapLabelStatistics.put(Statistics.MOST_POPULAR_GENOTYPE, genotype);
        mapLabelStatistics.put(Statistics.AVG_ANIMALS_ENERGY, avgEnergy);
        mapLabelStatistics.put(Statistics.AVG_LENGTH_OF_LIFE, avgLife);
        mapLabelStatistics.put(Statistics.AVG_NUMBER_OF_CHILDREN, avgChildren);
    }

    private void drawMap(int day){
        clearGrid();
        mapGrid.setGridLinesVisible(true);
        Boundary currentBounds = worldMap.getCurrentBounds();
        int cols = Math.abs(currentBounds.upperRight().x()-currentBounds.lowerLeft().x())+1;
        int rows = Math.abs(currentBounds.upperRight().y()-currentBounds.lowerLeft().y())+1;
        Vector2d currentPosition = new Vector2d(currentBounds.lowerLeft().x(),currentBounds.upperRight().y());
        addCells(cols, rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Vector2d addVector = new Vector2d(i,-j);
                addIcon(day, currentPosition, addVector, i, j);
            }
        }
    }

    private void addIcon(int day, Vector2d currentPosition, Vector2d addVector, int i, int j) {
        if(worldMap.isOccupied(currentPosition.add(addVector))){
            Circle circle = new Circle(5);
            setIcon(circle, worldMap.objectAt(currentPosition.add(addVector)), day);
            GridPane.setHalignment(circle, HPos.CENTER);
            if(chosen != null && chosen.position().equals(currentPosition.add(addVector)) && chosen.getDayOfDeath().isEmpty()){
                circle.setFill(Color.ORANGE);
                displayAnimalStatistics(Optional.ofNullable(chosen));
            }
            addLabel(circle, i, j);
        }
    }

    private void displayAnimalStatistics(Optional<Animal> optionalAnimal) {
        if(optionalAnimal.isPresent()){
            Animal animal = optionalAnimal.orElseThrow();
            animalId.setText(Integer.toString(animal.getID()));
            lengthOfLife.setText(Integer.toString(animal.getAge()));
            energy.setText(Integer.toString(animal.getEnergy()));
            numOfChildren.setText(Integer.toString(animal.getNumOfChildren()));
            if(animal.getDayOfDeath().isPresent()) {
                deathDate.setText(Integer.toString(animal.getDayOfDeath().orElse(null)));
            }
            else deathDate.setText("-");
        }
        else {
            animalId.setText("-");
            lengthOfLife.setText("-");
            energy.setText("-");
            numOfChildren.setText("-");
            deathDate.setText("-");
        }
    }

    private void addLabel(Circle circle, int i, int j) {
        GridPane.setHalignment(circle, HPos.CENTER);
        mapGrid.add(circle, i, j);
    }

    private void setIcon(Circle circle, List<WorldElement> worldElements, int day) {
        WorldElement worldElement = worldElements.get(0);
        circle.setFill(worldElement.toIcon());
        circle.setOnMouseClicked(event -> {
            if(worldElement instanceof Animal animal){
                chosen = animal.equals(chosen) ? null : animal;
                if(chosen == null){
                    displayAnimalStatistics(Optional.empty());
                }
                mapChanged(worldMap, day);
            }
        });
    }

    private void addCells(int cols, int rows) {
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

    private void setOptions(SimulationConfiguration configuration, int mapType) {
        if(mapType == 1){
            worldMap = new RectangularMap(1, configuration.getMapWidth(), configuration.getMapHeight());
        }else {
            worldMap = new HellPortal(2, configuration.getMapWidth(), configuration.getMapHeight());
        }
        worldMap.registerObserver(this);
        this.configuration = configuration;
    }

    private void increaseID() {
        synchronized (this){
            simulationID += 1;
        }
    }

    @FXML
    public void onContinueClicked(){
        simulation.continueSimulation();
    }

    @FXML
    public void onStopClicked(){
        simulation.stopSimulation();
    }

    @Override
    public void mapChanged(WorldMap worldMap, int day) {
        Platform.runLater(() -> {
            drawMap(day);
            statisticsUpdate(day);
        });
    }

    public void statisticsUpdate(int day) {
        simulationStatistics.updateStatistic(day);
        Map<Statistics, Double> mapStatistics = simulationStatistics.getMapStatistics();
        mapLabelStatistics.forEach((name, label) -> {
            if(name == Statistics.MAP_TYPE) {
                if(mapStatistics.get(name) == 1) {
                    label.setText("Earth");
                }
                else {
                    label.setText(("Hell Portal"));
                }
            }
            else if(name == Statistics.GENOM_TYPE) {
                if(mapStatistics.get(name) == 1) {
                    label.setText("Normal genome");
                }
                else {
                    label.setText(("Back and Forward"));
                }
            }
            else {
                label.setText(mapStatistics.get(name).toString());
            }
        });
    }
}
