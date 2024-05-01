package client.controllers;

import client.api.Api;
import client.models.Bond;
import client.models.BondInPortfolio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class GetPortfolioBondsController extends MenuController{

    private ObservableList<BondInPortfolio> bondsTableValues = FXCollections.observableArrayList();
    protected Api api = new Api();
    @FXML
    public TableView<BondInPortfolio> porfolioBondsTableValues = new TableView<BondInPortfolio>();
    @FXML
    public TableColumn<BondInPortfolio, String> bondNameField;
    @FXML
    public TableColumn<BondInPortfolio, Integer> bondIdField;
    @FXML
    public TableColumn<BondInPortfolio, Integer>  nominalcostField;
    @FXML
    public TableColumn<BondInPortfolio, Integer> repaymentPeriodField;
    @FXML
    public TableColumn<BondInPortfolio, String> couponExistingField;
    @FXML
    public TableColumn<BondInPortfolio, String> couponSizePayField;
    @FXML
    public TableColumn<BondInPortfolio, String> couponFrequencyField;
    @FXML
    public TableColumn<BondInPortfolio, String> countField;
    @FXML
    TextField ownerId = new TextField();


    /**
     * Нажатие на добавления
     * отправка данных GET запросом на сервер
     */
    @FXML
    private void initialize() {
        // устанавливаем тип и значение которое должно хранится в колонке
        this.bondNameField.setCellValueFactory(value -> value.getValue().bondName);
        this.bondIdField.setCellValueFactory(value -> value.getValue().bondId.asObject());
        this.nominalcostField.setCellValueFactory(value -> value.getValue().nominalCost.asObject());
        this.repaymentPeriodField.setCellValueFactory(value -> value.getValue().repaymentPeriod.asObject());
        this.couponExistingField.setCellValueFactory(value -> value.getValue().couponExisting);
        this.couponFrequencyField.setCellValueFactory(value -> value.getValue().couponFrequency);
        this.couponSizePayField.setCellValueFactory(value -> value.getValue().couponSize);
        this.countField.setCellValueFactory(value -> value.getValue().count);
        //добавляю данные об облигациях в таблицу
        this.porfolioBondsTableValues.setItems(bondsTableValues);
    }

    @FXML
    private void handleGetBondsByOwner() {
        porfolioBondsTableValues.getItems().clear();
        List<BondInPortfolio> getBondsByOwnerResult = api.getBondsByOwnerId(ownerId.getText());
        bondsTableValues.addAll(getBondsByOwnerResult);
    }
}
