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
    @Column(name = "count")
    public String count;

    public PortfolioEntity(){}

    public PortfolioEntity(String ownerBondId, String ownerId, String count){
        this.ownerBondId= ownerBondId;
        this.ownerId = ownerId;
        this.count = count;
    }
}
