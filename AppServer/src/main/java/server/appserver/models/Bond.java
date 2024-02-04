package server.appserver.models;
public class Bond {
    public String bondName;
    public int bondId;

    public long nominalcost;
    public long couponfrequency;
    public int repaymentPeriod;
    public int couponRate;
    public float yieldToMaturity;
    public int ownerId;

    public String ownerName;

    public void setValues(String bondName, int bondId, long nominalCost,
                          long couponFrequency, int repaymentPeriod,
                          int couponRate, float yieldToMaturity, int ownerId,
                          String owenrName){
        this.bondName = bondName;
        this.bondId = bondId;
        this.nominalcost = nominalCost;
        this.couponfrequency = couponFrequency;
        this.repaymentPeriod = repaymentPeriod;
        this.couponRate = couponRate;
        this.yieldToMaturity = yieldToMaturity;
        this.ownerId = ownerId;
        this.ownerName = owenrName;
    }
}
