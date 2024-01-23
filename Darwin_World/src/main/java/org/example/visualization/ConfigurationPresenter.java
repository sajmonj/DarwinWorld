package org.example.visualization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.data.SimulationConfiguration;


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
    private TextField grassInitNum;
    @FXML
    private TextField grassNum;
    @FXML
    private TextField grassEnergy;
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
    private int selectedMapType;
    private int selectedGenotype;
    private SimulationConfiguration configuration = null;

    @FXML
    public void onSimulationStartClicked(){
        onSimulationSaveClicked();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane simulationWindow = loader.load();

            SimulationPresenter simulationPresenter = loader.getController();
            int ID = simulationPresenter.startSimulationPresenter(configuration, selectedMapType);
            Stage stage = new Stage();
            stage.setTitle("Simulation "+ID);

            Scene scene = new Scene(simulationWindow);
            stage.setScene(scene);

            stage.show();

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
        if (selectedMapType == 1) onEarthButtonClicked();
        else if (selectedMapType == 2) onHellPortalButtonClicked();
        if (selectedGenotype == 1) onGenotypeButtonClicked();
        else if (selectedGenotype == 2) onBAFButtonClicked();
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


    private void setConfiguration(){
        int height = Integer.parseInt(mapHeight.getText());
        int width = Integer.parseInt(mapWidth.getText());
        int animals = Integer.parseInt(animalsNumber.getText());
        int generations = Integer.parseInt(genNumbers.getText());
        int energy = Integer.parseInt(animalEnergy.getText());
        int readyEnergyValue = Integer.parseInt(readyEnergy.getText());
        int reproductionEnergyValue = Integer.parseInt(reproductionEnergy.getText());
        int grassInitNumber = Integer.parseInt(grassInitNum.getText());
        int grassNumber = Integer.parseInt(grassNum.getText());
        int grassEnergyValue = Integer.parseInt(grassEnergy.getText());
        int speedValue = (int) speed.getValue();
        int mapType = selectedMapType;
        int genotype = selectedGenotype;

        configuration.update(height, width, animals, generations, energy, readyEnergyValue,
                reproductionEnergyValue, grassInitNumber, grassNumber, grassEnergyValue, speedValue, mapType, genotype );
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
        speed.setValue(configuration.getSpeed());
        selectedMapType = configuration.getMapType();
        selectedGenotype = configuration.getGenotype();
    }
}
