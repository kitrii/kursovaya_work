//package server.appserver.mapper;
//
//import server.appserver.models.Bond;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BondMapper {
//
//    public static List<Bond> BondEntityToBondMapper(List<BondsEntity> BondEntities){
//        List<Bond> bonds = new ArrayList<>();
//        for (BondsEntity bond: BondEntities) {
//            Bond vBond = new Bond();
//            vBond.setValues(bond.bondname, bond.bondid, bond.nominalcost, bond.couponfrequency,
//                    bond.repaymentperiod, bond.couponrate,
//                    bond.yieldtomaturity, bond.ownerid, bond.owner);
//            bonds.add(vBond);
//        }
//        return bonds;
//    }
//}
