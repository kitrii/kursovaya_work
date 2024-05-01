package client.controllers;

import client.api.Api;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCouponToBondController extends MenuController{
    protected Stage AddCouponToBondStage;

    protected Api api = new Api();
    @FXML
    TextField bondIdField;
    @FXML
    TextField couponSizeField;
    @FXML
    TextField couponFrequencyField;


    /** Инициализация полей */
    @FXML
    private void initialize() {
        bondIdField.setText(null);
        couponSizeField.setText(null);
        couponFrequencyField.setText(null);
    }
    @FXML
    private void handleAddCouponToBond() {
        boolean addCouponToBondResult = api.addCouponToBond(
                bondIdField.getText(),
                couponSizeField.getText(),
                couponFrequencyField.getText());
        if (addCouponToBondResult) {
            AddCouponToBondStage.close();
        }
    }
}
