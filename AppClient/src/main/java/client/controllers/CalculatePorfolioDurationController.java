package client.controllers;

import client.api.Api;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculatePorfolioDurationController extends MenuController{
    protected Api api = new Api();

    @FXML
    TextField ownerIdField;

    @FXML
    TextField portfolioDuration;
    @FXML
    private void handleCalculateDuration(){
        String result = api.calculatePortfolio(ownerIdField.getText());
        portfolioDuration.setText(result);
    }
}
