package server.appserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "bonds")
public class BondsEntity {
    @Id
    @Column(nullable = false)
    public int bondid;
    @Column(nullable = false)
    public String bondname;
    @Column
    public long nominalcost;
    @ Column
    public long couponfrequency;
    @Column
    public int repaymentperiod;
    @Column
    public int couponrate;
    @Column
    public float yieldtomaturity;
    @Column(nullable = false)
    public int ownerid;
    @Column(nullable = false)
    public String owner;


    public void setValues(String bondName, int bondId, long nominalCost,
                          long couponFrequency, int repaymentPeriod, int couponRate,
                          float yieldToMaturity, int ownerId, String owner){
        this.bondname = bondName;
        this.bondid = bondId;
        this.nominalcost = nominalCost;
        this.couponfrequency = couponFrequency;
        this.repaymentperiod = repaymentPeriod;
        this.couponrate = couponRate;
        this.yieldtomaturity = yieldToMaturity;
        this.ownerid = ownerId;
        this.owner = owner;
    }
}
