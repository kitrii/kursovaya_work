package server.appserver.entity;


import javax.persistence.*;

@Entity
@Table(name = "coupon")
public class CouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bond_id", nullable = false, unique = true)
    public String bondId;
    @Column(name = "coupon_size")
    public String couponSize;
    @Column(name = "coupon_frequency")
    public String couponFrequency;

}
