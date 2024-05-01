package client.controllers;
import client.api.Api;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddBondController extends MenuController {
    protected Stage AddBondStage;

    protected Api api = new Api();
    @FXML
    TextField bondNameField;
    @FXML
    TextField bondIdField;
    @FXML
    TextField nominalcostField;
    @FXML
    TextField repaymentPeriodField;
    @FXML
    TextField couponSize;
    @FXML
    TextField couponfrequencyField;

    /** Инициализация полей */
    @FXML
    private void initialize() {
        bondNameField.setText(null);
        bondIdField.setText(null);
        nominalcostField.setText(null);
        repaymentPeriodField.setText(null);
    }
    @FXML
    private void handleAddBond() {
        boolean addBondResult = api.addBond(
                bondIdField.getText(),
                bondNameField.getText(),
                nominalcostField.getText(),
                repaymentPeriodField.getText());

        if (addBondResult) {
            AddBondStage.close();
        }
    }
}

