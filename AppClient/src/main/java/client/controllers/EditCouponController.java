package client.controllers;

import client.api.Api;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCouponController extends MenuController{
    public Stage EditCouponStage = new Stage();
    public Api api = new Api();

    @FXML
    TextField bondIdField;
    @FXML
    TextField couponSizeField;
    @FXML
    TextField couponFrequencyField;

    @FXML
    private void initialize() {
        bondIdField.setText(null);
        couponSizeField.setText(null);
        couponFrequencyField.setText(null);
    }
    @FXML
    private void handleEditCoupon() {
        boolean addCouponToBondResult = api.editCoupon(
                bondIdField.getText(),
                couponSizeField.getText(),
                couponFrequencyField.getText());
        if (addCouponToBondResult) {
            EditCouponStage.close();
        }
    }
}
