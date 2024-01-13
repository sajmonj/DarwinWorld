package org.example.visualization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.data.SimulationConfiguration;
import org.example.model.ConsoleMapDisplay;
import org.example.model.RectangularMap;
import org.example.model.WorldMap;
import org.example.simulation.Simulation;

import java.io.IOException;

public class ConfigurationPresenter {

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
    private TextField grassNum;
    @FXML
    private TextField grassEnergy;

    private SimulationConfiguration configuration = null;

    @FXML
    public void onSimulationStartClicked(){
        onSimulationSaveClicked();
        System.out.println(configuration);
//        FXMLLoader loader = new FXMLLoader();
//        SimulationPresenter simulationPresenter = loader.getController();
//        simulationPresenter.startSimulationPresenter(configuration, "RectangularMap");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane simulationWindow = loader.load();

            SimulationPresenter simulationPresenter = loader.getController();
            simulationPresenter.startSimulationPresenter(configuration, "RectangularMap");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void onSimulationSaveClicked(){
        creatConfiguration();
        setConfiguration();
        configuration.save();
    }
    @FXML
    public void onSimulationLoadClicked(){
        creatConfiguration();
        configuration.load();
        updateConfiguration();
    }

    private void creatConfiguration() {
        if(configuration == null){
            configuration = new SimulationConfiguration();
        }
    }


    private void setConfiguration(){
        int height = Integer.parseInt(mapHeight.getText());
        int width = Integer.parseInt(mapWidth.getText());
        int animals = Integer.parseInt(animalsNumber.getText());
        int generations = Integer.parseInt(genNumbers.getText());
        int energy = Integer.parseInt(animalEnergy.getText());
        int readyEnergyValue = Integer.parseInt(readyEnergy.getText());
        int reproductionEnergyValue = Integer.parseInt(reproductionEnergy.getText());
        int grassNumber = Integer.parseInt(grassNum.getText());
        int grassEnergyValue = Integer.parseInt(grassEnergy.getText());

        configuration.update(height, width, animals, generations, energy, readyEnergyValue,
                reproductionEnergyValue, grassNumber, grassEnergyValue);
    }
    private void updateConfiguration(){
        mapHeight.setText(String.valueOf(configuration.getMapHeight()));
        mapWidth.setText(String.valueOf(configuration.getMapWidth()));
        animalsNumber.setText(String.valueOf(configuration.getAnimalsNumber()));
        genNumbers.setText(String.valueOf(configuration.getGenNumbers()));
        animalEnergy.setText(String.valueOf(configuration.getAnimalEnergy()));
        readyEnergy.setText(String.valueOf(configuration.getReadyEnergy()));
        reproductionEnergy.setText(String.valueOf(configuration.getReproductionEnergy()));
        grassNum.setText(String.valueOf(configuration.getGrassNum()));
        grassEnergy.setText(String.valueOf(configuration.getGrassEnergy()));
    }
}
