package server.appserver.entity;

import javax.persistence.Column;

public class BondInPortfolioEntity {
    public String bondId;
    public String bondName;
    public String nominalCost;
    public String repaymentPeriod;
    public String couponExisting = "False";
    public String couponSize;
    public String couponFrequency;
    public String count;

    public BondInPortfolioEntity(String bondId, String bondName, String nominalCost, String repaymentPeriod,
                                 String couponExisting, String couponSize, String couponFrequency, String count){
        this.bondId= bondId;
        this.bondName = bondName;
        this.bondId = bondId;
        this.nominalCost = nominalCost;
        this.repaymentPeriod = repaymentPeriod;
        this.couponExisting = couponExisting;
        this.couponSize = couponSize;
        this.couponFrequency = couponFrequency;
        this.count = count;
    }
}
