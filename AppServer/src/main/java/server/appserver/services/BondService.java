package server.appserver.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.appserver.entity.BondEntity;
import server.appserver.entity.CouponEntity;
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
    @Transactional
    public void deleteBondByBondId(String bondId){
        BTO.deleteBondByBondId(bondId);
    }
    @Transactional
    public void addBond(BondEntity bond){
        BTO.addBond(bond);
    }
    @Transactional
    public void editBond(BondEntity bond){
        String bondId = bond.bondId;
        BTO.editBondByBondId(bondId, bond);
    }
    @Transactional
    public void addCouponToBond(CouponEntity coupon){
        BTO.addCouponToBond(coupon);
    }
    @Transactional
    public void editCouponToBond(CouponEntity coupon){
        BTO.editCouponToBond(coupon);
    }
}
