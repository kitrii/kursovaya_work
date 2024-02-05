package client.controllers;
import client.api.Api;
import client.models.Bond;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;


public class GetBondsController extends MenuController{

    private ObservableList<Bond> bondsTableValues = FXCollections.observableArrayList();
    protected Api api = new Api();
    @FXML
    public TableView<Bond> bondsTableView = new TableView<Bond>();
    @FXML
    public TableColumn<Bond, String> bondNameField;
    @FXML
    public TableColumn<Bond, Integer> bondIdField;
    @FXML
    public TableColumn<Bond, Integer>  nominalcostField;
    @FXML
    public TableColumn<Bond, Integer> couponfrequencyField;
    @FXML
    public TableColumn<Bond, Integer> repaymentPeriodField;
    @FXML
    public TableColumn<Bond, Integer> couponRateField;
    @FXML
    public TableColumn<Bond, Integer> yieldToMaturityField;
    @FXML
    public TableColumn<Bond, Integer> couponSizePay;
    @FXML
    public TableColumn<Bond, Integer> ownerIdField;
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
        this.couponRateField.setCellValueFactory(value -> value.getValue().couponRate.asObject());
        this.couponfrequencyField.setCellValueFactory(value -> value.getValue().couponFrequency.asObject());
        this.repaymentPeriodField.setCellValueFactory(value -> value.getValue().repaymentPeriod.asObject());
        this.yieldToMaturityField.setCellValueFactory(value -> value.getValue().yieldToMaturity.asObject());
        this.couponSizePay.setCellValueFactory(value -> value.getValue().couponSizePay.asObject());
        //добавляю данные об облигациях в таблицу
        this.bondsTableView.setItems(bondsTableValues);
    }
    @FXML

    private void handleGetBonds() {
        // очищаем таблицу
        bondsTableView.getItems().clear();
        //  получаем данные об облигациях из бд (список Bond объектов)
        List<Bond> getAllBondsResult = api.getAllBonds();
        // добавляем данные в FX коллекцию-список (
        bondsTableValues.addAll(getAllBondsResult);
    }
    @FXML
    private void handleGetBondsByOwner() {
        bondsTableView.getItems().clear();
        List<Bond> getBondsByOwnerResult = api.getBondsByOwnerId(ownerId.getText());
        bondsTableValues.addAll(getBondsByOwnerResult);
    }
}

