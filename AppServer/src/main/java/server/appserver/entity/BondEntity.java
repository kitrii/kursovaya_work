package server.appserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "bond")
public class BondEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bond_id", nullable = false)
    public String bondId;
    @Column(name = "bond_name", nullable = false)
    public String bondName;
    @Column(name = "nominal_cost")
    public String nominalCost;
    @ Column(name = "coupon_frequency")
    public String couponFrequency;
    @Column(name = "repayment_period")
    public String repaymentPeriod;
    @Column(name = "coupon_rate")
    public String couponRate;
    @Column(name = "yield_to_maturity")
    public String yieldToMaturity;

    /**
     * Пустой конструктор класса
     */
    public BondEntity(){}

    /**
     * Конструктор класса
     * @param bondId id облигации
     * @param bondName название облигации
     * @param nominalCost номинальная стоимость облигации
     * @param couponFrequency частота выплат купона
     * @param repaymentPeriod срок на который берется облигация
     * @param couponRate купонная ставка
     * @param yieldToMaturity доходность к погашению
     */
    public BondEntity(String bondId, String bondName,  String nominalCost,
                      String couponFrequency, String repaymentPeriod, String couponRate, String yieldToMaturity){
        this.bondId= bondId;
        this.bondName = bondName;
        this.bondId = bondId;
        this.nominalCost = nominalCost;
        this.couponFrequency = couponFrequency;
        this.repaymentPeriod = repaymentPeriod;
        this.couponRate = couponRate;
        this.yieldToMaturity = yieldToMaturity;
    }
}
