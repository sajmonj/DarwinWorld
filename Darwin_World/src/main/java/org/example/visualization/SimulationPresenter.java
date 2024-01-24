package org.example.visualization;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.*;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.example.data.SimulationStatistics;
import org.example.data.Statistics;
import org.example.model.*;
import org.example.simulation.Simulation;
import org.example.data.SimulationConfiguration;
import org.example.simulation.SimulationEngine;

import static java.lang.Math.ceil;
import static java.lang.Math.max;


public class SimulationPresenter implements MapChangeListener {
    private int cellSize;
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
    private Label animalGens;
    @FXML
    private Label lengthOfLife;
    @FXML
    private Label energy;
    @FXML
    private Label numOfChildren;
    @FXML
    private Label deathDate;

    private WorldMap worldMap;
    private Boundary boundaries;
    private int cols;
    private int rows;
    private Boundary preferredAreaBounds;
    private SimulationConfiguration configuration;
    private final Map<Statistics, Label> mapLabelStatistics = new HashMap<>();

    private Animal chosen = null;
    private List<Gen> popularGens = null;
    private boolean isPreferredAreaShown;

    public int startSimulationPresenter(SimulationConfiguration configuration, int mapType) {
        setOptions(configuration, mapType);
        runStatistics();
        increaseID();
        boundaries = worldMap.getCurrentBounds();
        cols = Math.abs(boundaries.upperRight().x()-boundaries.lowerLeft().x())+1;
        rows = Math.abs(boundaries.upperRight().y()-boundaries.lowerLeft().y())+1;
        cellSize = 600/max(cols, rows);
        preferredAreaBounds = new Boundary(new Vector2d(1, boundaries.upperRight().y() / 2 - (int)(boundaries.upperRight().y() * 0.2) / 2 + 1),
                new Vector2d(cols, boundaries.upperRight().y() / 2 - (int)(boundaries.upperRight().y() * 0.2) / 2 + (int)(boundaries.upperRight().y()*0.2)));
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
        Vector2d currentPosition = new Vector2d(boundaries.lowerLeft().x(),boundaries.upperRight().y());
        addCells(cols, rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                showPreferredArea(i, j);
                Vector2d addVector = new Vector2d(i,-j);
                addIcon(day, currentPosition, addVector, i, j);
            }
        }
    }

    private void showPreferredArea(int i, int j) {
        Vector2d cords = new Vector2d(i +1, j +1);
        if(cords.follows(preferredAreaBounds.lowerLeft())
                && cords.precedes(preferredAreaBounds.upperRight()) && isPreferredAreaShown) {
//            System.out.println(cords + " " + preferredAreaBounds);
            addBackground(new Rectangle(cellSize, cellSize), i, boundaries.upperRight().y()- j -1);
        }
    }

    private StackPane setCellBackground() {
        StackPane cell = new StackPane();
        cell.setStyle("-fx-background-color: " + "rgba(248,225,56,0.42)" + ";");
        return cell;
    }

    private void addIcon(int day, Vector2d currentPosition, Vector2d addVector, int i, int j) {
        if(worldMap.isOccupied(currentPosition.add(addVector)) && !worldMap.objectAt(currentPosition.add(addVector)).isEmpty()){
            Circle circle = new Circle((double) cellSize/3);
            WorldElement worldElement = worldMap.objectAt(currentPosition.add(addVector)).get(0);
            setIcon(circle, worldElement , day);
            GridPane.setHalignment(circle, HPos.CENTER);
            if(chosen != null && chosen.getPosition().equals(currentPosition.add(addVector))){
                circle.setFill(Color.ORANGE);
//                displayAnimalStatistics(Optional.ofNullable(chosen));
            } else if (worldElement instanceof Animal && ((Animal) worldElement).getAnimalGens().equals(popularGens)){
                circle.setFill(Color.BLUE);
            }
            addLabel(circle, i, j);
        }
    }

    private void displayAnimalStatistics(Optional<Animal> optionalAnimal) {
        if(optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.orElseThrow();
            animalId.setText(Integer.toString(animal.getID()));
            animalGens.setText(animal.getAnimalGens().toString());
            lengthOfLife.setText(Integer.toString(animal.getAge()));
            energy.setText(Integer.toString(max(0, animal.getEnergy())));
            numOfChildren.setText(Integer.toString(animal.getNumOfChildren()));
            if(animal.getDayOfDeath().isPresent()) {
                deathDate.setText(Integer.toString(animal.getDayOfDeath().orElse(null)));
            }
            else deathDate.setText("-");
        }
        else {
            animalId.setText("-");
            animalGens.setText("-");
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

    private void setIcon(Circle circle, WorldElement worldElement, int day) {
        circle.setFill(worldElement.toIcon());
        circle.setOnMouseClicked(event -> {
            if(worldElement instanceof Animal animal){
                chosen = animal.equals(chosen) ? null : animal;
                mapChanged(worldMap,day);
            }
        });
   }

    private void addBackground(Rectangle cell, int i, int j) {
        GridPane.setHalignment(cell, HPos.CENTER);
        cell.setFill(Color.PEACHPUFF.deriveColor(1,1,1,0.4));
        mapGrid.add(cell, i, j);
    }

    private void addCells(int cols, int rows) {
        for (int i = 0; i < cols; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        }
        for (int i = 0; i < rows; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(cellSize));
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
    private void onContinueClicked(){
        simulation.continueSimulation();
    }

    @FXML
    private void onStopClicked(){
        simulation.stopSimulation();
    }
    @FXML
    private void onTrackGens(){

    }

    @FXML
    private void saveToCsv(){
        simulationStatistics.saveStatisticsToCsv();
    }
    @FXML
    public void onShowPreferredAreaClick(){
        isPreferredAreaShown = !isPreferredAreaShown;
    }

    @Override
    public void mapChanged(WorldMap worldMap, int day) {
        Platform.runLater(() -> {
            drawMap(day);
            statisticsUpdate(day);
            displayAnimalStatistics(Optional.ofNullable(chosen));
        });
    }

    public void statisticsUpdate(int day) {
        simulationStatistics.updateStatistic(day);
        popularGens = simulationStatistics.getPopularGens();

        Map<Statistics, String> mapStatistics = simulationStatistics.getMapStatistics();
                mapLabelStatistics.forEach((name, label) -> {
            if(name == Statistics.MAP_TYPE) {
                if(Objects.equals(mapStatistics.get(name), "1")) {
                    label.setText("Earth");
                }
                else {
                    label.setText(("Hell Portal"));
                }
            }
            else if(name == Statistics.GENOM_TYPE) {
                if(Objects.equals(mapStatistics.get(name), "1")) {
                    label.setText("Normal genome");
                }
                else {
                    label.setText(("Back and Forward"));
                }
            }
            else {
                label.setText(mapStatistics.get(name));
            }
        });
    }
}
