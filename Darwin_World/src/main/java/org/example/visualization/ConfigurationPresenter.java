package org.example.visualization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.example.data.FileSelectorController;
import org.example.data.InputValidation;
import org.example.data.SimulationConfiguration;
import org.example.simulation.Simulation;
import org.example.simulation.SimulationEngine;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.min;

public class ConfigurationPresenter {
    private static final List<Simulation> SIMULATIONS= new ArrayList<>();
    private static final SimulationEngine SIMULATION_ENGINE = new SimulationEngine(SIMULATIONS);
    private Optional<File> file = Optional.empty();

    @FXML
    private TextField mapHeight;
    @FXML
    private TextField mapWidth;
    @FXML
    private TextField animalsNumber;
    @FXML
    private TextField genNumbers;
    @FXML
    private TextField animalEnergy;
    @FXML
    private TextField readyEnergy;
    @FXML
    private TextField reproductionEnergy;
    @FXML
    private TextField grassInitNum;
    @FXML
    private TextField grassNum;
    @FXML
    private TextField grassEnergy;
    @FXML
    private TextField filePathTextField;
    @FXML
    private Spinner<Integer> minMutations = new Spinner<>(0, 10, 0);
    @FXML
    private Spinner<Integer> maxMutations = new Spinner<>(0, 10, 3);
    @FXML
    private Button earth;
    @FXML
    private Button hellPortal;
    @FXML
    private Button genotype;
    @FXML
    private Button backAndForward;
    @FXML
    private Slider speed;
    @FXML
    private CheckBox toCSV;
    private Integer selectedMapType = null;
    private Integer selectedGenotype = null;
    private SimulationConfiguration configuration = null;

    @FXML
    public void onSimulationStartClicked(){
        try {
            creatConfiguration();
            configurationValidation();
            SimulationConfiguration simulationConfiguration = new SimulationConfiguration();
            setConfiguration(simulationConfiguration);
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane simulationWindow = loader.load();

            SimulationPresenter simulationPresenter = loader.getController();
            int ID = simulationPresenter.startSimulationPresenter(this, simulationConfiguration, selectedMapType);
            Stage stage = new Stage();
            stage.setTitle("Simulation "+ID);

            Scene scene = new Scene(simulationWindow);
            stage.setScene(scene);

            stage.setOnHiding(event -> {
                simulationPresenter.endSimulation();
            });

            stage.show();

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Configuration error!");
            Popup popup = ErrorPopUp.createErrorPopup();

            Scene scene = earth.getScene();

            double centerX = scene.getWindow().getX() + scene.getWidth() / 2;
            double centerY = scene.getWindow().getY() + scene.getHeight() / 2;

            popup.show(scene.getWindow(), centerX - popup.getWidth() / 2, centerY - popup.getHeight() / 2);
        }
    }

    @FXML
    public void onSimulationSaveClicked(){
        creatConfiguration();
        setConfiguration(configuration);
        configuration.save();
    }
    public void onSimulationLoadClicked()  {
        creatConfiguration();
        configuration.load(file.orElseThrow());
        updateConfiguration();
        configurationValidation();
        if (selectedMapType == 1) onEarthButtonClicked();
        else if (selectedMapType == 2) onHellPortalButtonClicked();
        if (selectedGenotype == 1) onGenotypeButtonClicked();
        else if (selectedGenotype == 2) onBAFButtonClicked();
    }

    private void configurationValidation() {
        if( !InputValidation.inputValidation(mapHeight, mapWidth, animalsNumber, genNumbers, animalEnergy,
                readyEnergy, reproductionEnergy, grassInitNum, grassNum, grassEnergy, selectedMapType,earth,hellPortal,
                selectedGenotype, genotype, backAndForward)
                && !fileValidation()){
            System.err.println("Invalid settings");
            throw new IllegalArgumentException();
        }
    }

    private boolean fileValidation() {
        return file.isEmpty();
    }


    @FXML
    public void onEarthButtonClicked(){
        selectedMapType = 1;
        setButtonStyle(earth, true);
        setButtonStyle(hellPortal, false);
    }
    @FXML
    public void onHellPortalButtonClicked(){
        selectedMapType = 2;
        setButtonStyle(hellPortal, true);
        setButtonStyle(earth, false);
    }

    @FXML
    public void onGenotypeButtonClicked(){
        selectedGenotype = 1;
        setButtonStyle(genotype, true);
        setButtonStyle(backAndForward, false);
    }
    @FXML
    public void onBAFButtonClicked(){
        selectedGenotype = 2;
        setButtonStyle(backAndForward, true);
        setButtonStyle(genotype, false);
    }

    private void setButtonStyle(Button button, boolean isActive) {
        if (isActive) {
            button.setStyle("-fx-background-color: lightblue; -fx-font-weight: bold;");
        } else {
            button.setStyle("");
        }
    }

    private void creatConfiguration() {
        if(configuration == null){
            configuration = new SimulationConfiguration();
        }
    }

    private void setConfiguration(SimulationConfiguration tempConfiguration){
        int height = Integer.parseInt(mapHeight.getText());
        int width = Integer.parseInt(mapWidth.getText());
        int animals = Integer.parseInt(animalsNumber.getText());
        int genNumber = Integer.parseInt(genNumbers.getText());
        int energy = Integer.parseInt(animalEnergy.getText());
        int readyEnergyValue = Integer.parseInt(readyEnergy.getText());
        int reproductionEnergyValue = Integer.parseInt(reproductionEnergy.getText());
        int grassInitNumber = Integer.parseInt(grassInitNum.getText());
        int grassNumber = Integer.parseInt(grassNum.getText());
        int grassEnergyValue = Integer.parseInt(grassEnergy.getText());
        int minimumMutations = minMutations.getValue();
        int maximumMutations = maxMutations.getValue();
        int speedValue = (int) speed.getValue();
        int mapType = selectedMapType;
        int genotype = selectedGenotype;
        boolean toCVS = Boolean.parseBoolean(String.valueOf(toCSV.isSelected()));

        handleSpinnerBoundaries(genNumber, minimumMutations, maximumMutations);

        tempConfiguration.update(height, width, animals, genNumber, energy, readyEnergyValue,
                reproductionEnergyValue, grassInitNumber, grassNumber, grassEnergyValue, minimumMutations,
                maximumMutations, speedValue,mapType, genotype, toCVS);
    }
    private void updateConfiguration(){
        mapHeight.setText(String.valueOf(configuration.getMapHeight()));
        mapWidth.setText(String.valueOf(configuration.getMapWidth()));
        animalsNumber.setText(String.valueOf(configuration.getAnimalsNumber()));
        genNumbers.setText(String.valueOf(configuration.getGenNumbers()));
        animalEnergy.setText(String.valueOf(configuration.getAnimalEnergy()));
        readyEnergy.setText(String.valueOf(configuration.getReadyEnergy()));
        reproductionEnergy.setText(String.valueOf(configuration.getReproductionEnergy()));
        grassInitNum.setText(String.valueOf(configuration.getGrassInitNumber()));
        grassNum.setText(String.valueOf(configuration.getGrassNum()));
        grassEnergy.setText(String.valueOf(configuration.getGrassEnergy()));
        minMutations.getValueFactory().setValue(configuration.getMinMutations());
        maxMutations.getValueFactory().setValue(configuration.getMaxMutations());
        speed.setValue(configuration.getSpeed());
        selectedMapType = configuration.getMapType();
        selectedGenotype = configuration.getGenotype();
        toCSV.setSelected(configuration.getToCSV());

        handleSpinnerBoundaries(configuration.getGenNumbers(), minMutations.getValue(), maxMutations.getValue());
    }

    @FXML
    private void handleSpinnerBoundaries(int genNumber, int minimumMutations, int maximumMutations) {

        SpinnerValueFactory<Integer> maxMutationsValueFactory = maxMutations.getValueFactory();
        SpinnerValueFactory<Integer> minMutationsValueFactory = minMutations.getValueFactory();

        maxMutations.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, genNumber, maxMutationsValueFactory.getValue()));
        minMutations.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, genNumber, minMutationsValueFactory.getValue()));

        maximumMutations = min(maximumMutations, genNumber);
        minimumMutations = min(minimumMutations, genNumber);

        maxMutations.getValueFactory().setValue(maximumMutations);
        minMutations.getValueFactory().setValue(minimumMutations);
    }

    @FXML
    private void handleSpinnerClick() {
        int genNumber = Integer.parseInt(genNumbers.getText());
        handleSpinnerBoundaries(genNumber, minMutations.getValue(), maxMutations.getValue());
    }

    public void addSimulation(Simulation simulation){
        SIMULATIONS.add(simulation);
        SIMULATION_ENGINE.addAndRunSimulationToThreadPool(simulation);
    }
    @FXML
    private void selectFile() {
        file = FileSelectorController.openFolder(filePathTextField);
        if(file.isPresent())onSimulationLoadClicked();
    }

}
