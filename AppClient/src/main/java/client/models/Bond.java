package client.models;

import javafx.beans.property.*;

public class Bond {
    public StringProperty bondName;
    public SimpleIntegerProperty bondId;
    public SimpleIntegerProperty nominalCost;
    public SimpleIntegerProperty repaymentPeriod;
    public StringProperty couponExisting;
    public SimpleStringProperty couponSize;
    public SimpleStringProperty couponFrequency;


    /**Конструктор Облигации*/
    public Bond(String bondName, Integer bondId, int nominalCost,
                Integer repaymentPeriod, String couponExisting,
                String couponSize, String couponFrequency) {
        this.bondName= new SimpleStringProperty(bondName);
        this.bondId = new SimpleIntegerProperty(bondId);
        this.nominalCost = new SimpleIntegerProperty(nominalCost);
        this.repaymentPeriod = new SimpleIntegerProperty(repaymentPeriod);
        this.couponExisting = new SimpleStringProperty(couponExisting);
        this.couponSize = new SimpleStringProperty(couponSize);
        this.couponFrequency = new SimpleStringProperty(couponFrequency);
    }
}
