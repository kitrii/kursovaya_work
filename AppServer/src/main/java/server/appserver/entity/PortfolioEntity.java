package server.appserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "portfolio")
public class PortfolioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "owner_bond_id", nullable = false)
    public String ownerBondId;
    @Column(name = "owner_id", nullable = false)
    public String ownerId;

//    @ManyToOne
//    @JoinColumn(name="owner_bond_id",
//            referencedColumnName = "bond_id",insertable=false, updatable=false)
//    private BondsEntity bondsEntity;

    public PortfolioEntity(){}

    public PortfolioEntity(String ownerBondId, String ownerId){
        this.ownerBondId= ownerBondId;
        this.ownerId = ownerId;
    }
}
