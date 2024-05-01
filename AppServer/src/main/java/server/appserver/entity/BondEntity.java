package server.appserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "bond")
public class  BondEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bond_id", nullable = false, unique = true)
    public String bondId;
    @Column(name = "bond_name", nullable = false, unique = true)
    public String bondName;
    @Column(name = "nominal_cost")
    public String nominalCost;
    @Column(name = "repayment_period")
    public String repaymentPeriod;
    @Column(name = "coupon_exist")
    public String couponExisting = "False";
    @Column(name = "coupon_size")
    public String couponSize = "0";
    @Column(name = "coupon_frequency")
    public String couponFrequency = "0";



    /**
     * Пустой конструктор класса
     */
    public BondEntity(){}

    /**
     * Конструктор класса
     * @param bondId id облигации
     * @param bondName название облигации
     * @param nominalCost номинальная стоимость облигации
     * @param repaymentPeriod срок на который берется облигация
     * @param couponExisting есть ли купон?
     */
    public BondEntity(String bondId, String bondName,  String nominalCost,
                      String repaymentPeriod){
        this.bondId= bondId;
        this.bondName = bondName;
        this.bondId = bondId;
        this.nominalCost = nominalCost;
        this.repaymentPeriod = repaymentPeriod;
        this.couponExisting = couponExisting;
//        this.couponSize = couponSize;
//        this.couponExisting = couponFrequency;
    }
}
