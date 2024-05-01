package client.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BondInPortfolio {
    public StringProperty bondName;
    public SimpleIntegerProperty bondId;
    public SimpleIntegerProperty nominalCost;
    public SimpleIntegerProperty repaymentPeriod;
    public StringProperty couponExisting;
    public SimpleStringProperty couponSize;
    public SimpleStringProperty couponFrequency;
    public SimpleStringProperty count;


    /**Конструктор Облигации в портфеле*/
    public BondInPortfolio(String bondName, Integer bondId, int nominalCost,
                Integer repaymentPeriod, String couponExisting,
                           String couponSize, String couponFrequency, String count) {
        this.bondName= new SimpleStringProperty(bondName);
        this.bondId = new SimpleIntegerProperty(bondId);
        this.nominalCost = new SimpleIntegerProperty(nominalCost);
        this.repaymentPeriod = new SimpleIntegerProperty(repaymentPeriod);
        this.couponExisting = new SimpleStringProperty(couponExisting);
        this.couponSize = new SimpleStringProperty(couponSize);
        this.couponFrequency = new SimpleStringProperty(couponFrequency);
        this.count = new SimpleStringProperty(count);
    }
}
