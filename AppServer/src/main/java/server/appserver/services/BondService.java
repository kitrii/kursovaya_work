package server.appserver.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.appserver.entity.BondEntity;
import server.appserver.entity.BondsEntity;
import server.appserver.operations.BondsTableOperations;

import java.util.List;
@Service
public class BondService {

    private final BondsTableOperations BTO = new BondsTableOperations();
    @Transactional(readOnly = true)
    public List<BondEntity> getAllBonds(){
        List<BondEntity> result = BTO.getAllBonds();
        return result;
    }
    @Transactional(readOnly = true)
    public List<BondsEntity> getBondsByOwnerId(int ownerId){
        List<BondsEntity> result = BTO.getBondsByOwnerId(ownerId);
        return result;
    }
    @Transactional
    public void deleteBondByOwnerIdBondId(int bondId, int ownerId){
        BTO.deleteBondByBondIdOwnerId(bondId, ownerId);
    }
    @Transactional
    public void addBondInfo(BondsEntity bond){
        BTO.addBond(bond);
    }
    @Transactional
    public void editBondInfo(BondsEntity bond){
        int ownerId = bond.ownerid;
        int bondId = bond.bondid;
        BTO.editBondByBondIdOwnerId(bondId, ownerId, bond);
    }
}
