package client.controllers;

import client.api.Api;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBondInPortfolioController extends MenuController{
    public Stage AddBondInPortfolioStage = new Stage();
    public Api api = new Api();

    @FXML
    TextField bondIdField;
    @FXML
    TextField ownerIdField;

    @FXML
    private void handleAddBondInPortfolio() {
        boolean addBondResult = api.addBondInPortfolio(
                bondIdField.getText(),
                ownerIdField.getText());

        if (addBondResult) {
            AddBondInPortfolioStage.close();
        }
    }
}
