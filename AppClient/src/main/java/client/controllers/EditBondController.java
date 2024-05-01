package client.controllers;

import client.api.Api;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditBondController extends MenuController{

    protected Api api = new Api();
    @FXML
    TextField bondIdField;
    @FXML
    TextField nominalcostField;
    @FXML
    TextField repaymentPeriodField;

    @FXML
    private void initialize() {
        bondIdField.setText(null);
        nominalcostField.setText(null);
        repaymentPeriodField.setText(null);
    }
    @FXML
    private void handleEditBond(){
        api.editBond(
                bondIdField.getText(),
                nominalcostField.getText(),
                repaymentPeriodField.getText());

    }
}
