package org.example.visualization;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.data.Configuration;

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

    @FXML
    private Configuration configuration;

    @FXML
    public void onSimulationStartClicked(){
        this.configuration = setConfiguration();
    }
    private Configuration setConfiguration(){
        return new Configuration(mapHeight,mapWidth,animalsNumber,genNumbers,animalEnergy,
                readyEnergy,reproductionEnergy, grassNum,grassEnergy);
    }
}
