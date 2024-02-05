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
    TextField couponfrequencyField;
    @FXML
    TextField repaymentPeriodField;
    @FXML
    TextField couponRateField;
    @FXML
    TextField yieldToMaturityField;

    /** Инициализация полей */
    @FXML
    private void initialize() {
        bondNameField.setText(null);
        bondIdField.setText(null);
        nominalcostField.setText(null);
        couponRateField.setText(null);
        couponfrequencyField.setText(null);
        repaymentPeriodField.setText(null);
        yieldToMaturityField.setText(null);
    }
    @FXML
    private void handleAddBond() {
        boolean addBondResult = api.addBond(
                bondIdField.getText(),
                bondNameField.getText(),
                nominalcostField.getText(),
                couponRateField.getText(),
                repaymentPeriodField.getText(),
                couponfrequencyField.getText(),
                yieldToMaturityField.getText());

        if (addBondResult) {
            AddBondStage.close();
        }
    }
}

