package server.appserver.operations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import server.appserver.entity.BondEntity;
import server.appserver.entity.BondInPortfolioEntity;
import server.appserver.entity.CouponEntity;
import server.appserver.entity.PortfolioEntity;
import java.util.ArrayList;
import java.util.List;

public class BondsTableOperations {
    public void addBond(BondEntity bond) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondEntity.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());

        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session sessionCreate = sf.openSession();
        Transaction transaction = sessionCreate.beginTransaction();
        sessionCreate.save(bond);
        transaction.commit();
        sessionCreate.close();
    }

    public String checkBondOwnerRelationShip(String bondId, String ownerId) {
        Configuration preconf = new Configuration().configure();
        preconf.addAnnotatedClass(PortfolioEntity.class);
        StandardServiceRegistryBuilder presBuilder = new StandardServiceRegistryBuilder()
                .applySettings(preconf.getProperties());
        SessionFactory presf = preconf.buildSessionFactory(presBuilder.build());
        Session presession = presf.openSession();
        Transaction pretransaction = presession.beginTransaction();
        Query prequery = presession.createQuery("from PortfolioEntity WHERE ownerBondId = :bondid and ownerId = :ownerid");
        prequery.setParameter("ownerid", ownerId);
        prequery.setParameter("bondid", bondId);
        List<PortfolioEntity> portfolios = prequery.getResultList();
        pretransaction.commit();
        presession.close();
        if (portfolios.size() == 0) {
            return "0";
        }
        return portfolios.get(0).count;
    }

    public void addBondInPortfolio(String bondId, String ownerId) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(PortfolioEntity.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());
        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Integer count = Integer.valueOf(checkBondOwnerRelationShip(bondId, ownerId));
        if (count > 0) {
            Integer new_count = count + 1;
            Query nativeQuery = session.createNativeQuery("UPDATE portfolio SET count = " + new_count + " WHERE owner_bond_id = " + bondId + " AND owner_id = " + ownerId);
            nativeQuery.executeUpdate();
            transaction.commit();
        }
        else {
            PortfolioEntity relationShip = new PortfolioEntity(bondId, ownerId, "1");
            session.save(relationShip);
            transaction.commit();
        }
        session.close();
    }

    public List<BondInPortfolioEntity> getBondsByOwnerId(String ownerId) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(PortfolioEntity.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());
        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session sessionRead = sf.openSession();
        Transaction transaction = sessionRead.beginTransaction();
        List<Object[]> dbBonds = sessionRead.createNativeQuery("select p.count, b.bond_id, b.bond_name, b.nominal_cost, b.repayment_period, b.coupon_exist, b.coupon_size, b.coupon_frequency from portfolio p join bond b on p.owner_bond_id = b.bond_id where p.owner_id = " + ownerId).getResultList();
        List<BondInPortfolioEntity> portfolioBonds = new ArrayList<>();
        for (Object[] dbBond: dbBonds) {
            String bondsCount = (String) dbBond[0];
            String bondid = (String) dbBond[1];
            String bondname = (String) dbBond[2];
            String nominalcost = (String) dbBond[3];
            String repaymentperiod = (String) dbBond[4];
            String couponExisting = (String) dbBond[5];
            String couponsize = (String) dbBond[6];
            String couponfrequency = (String) dbBond[7];
            BondInPortfolioEntity bond = new BondInPortfolioEntity(bondid, bondname, nominalcost, repaymentperiod, couponExisting, couponsize, couponfrequency, bondsCount);
            portfolioBonds.add(bond);
        }
        transaction.commit();
        sessionRead.close();
        return portfolioBonds;
    }
    public List<BondEntity> getAllBonds() {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondEntity.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());
        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session sessionRead = sf.openSession();
        Transaction transaction = sessionRead.beginTransaction();
        List<BondEntity> bonds = sessionRead.createQuery("from BondEntity").getResultList();
        transaction.commit();
        sessionRead.close();
        return bonds;
    }
    public void deleteBondByBondId(String bondId) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondEntity.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());
        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM BondEntity WHERE bondId = :bondid");
        query.setParameter("bondid", bondId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
    public void editBondByBondId(String bondId, BondEntity bond) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondEntity.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());
        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE BondEntity SET nominalCost = : nominalcost, repaymentPeriod = :repaymentperiod WHERE bondId = :bondid");
        query.setParameter("nominalcost", bond.nominalCost);
        query.setParameter("repaymentperiod", bond.repaymentPeriod);
        query.setParameter("bondid", bondId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
    public void addCouponToBond(CouponEntity coupon) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondEntity.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());

        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session sessionCreate = sf.openSession();
        Transaction transaction = sessionCreate.beginTransaction();
        Query query = sessionCreate.createQuery("UPDATE BondEntity SET " +
                "couponSize = : couponsize, " +
                "couponExisting = : couponexisting, " +
                "couponFrequency = :couponfrequency WHERE bondId = :bondid");
        query.setParameter("couponsize", coupon.couponSize);
        query.setParameter("couponfrequency", coupon.couponFrequency);
        query.setParameter("bondid", coupon.bondId);
        query.setParameter("couponexisting", "True");
        query.executeUpdate();
//        List<Object[]> dbBonds = sessionCreate.createNativeQuery("update bonds b set bond b where b.bond_id in (select p.owner_bond_id from portfolio p where p.owner_id = " + ownerId + ")").getResultList();
        transaction.commit();
        sessionCreate.close();
    }
    public void editCouponToBond(CouponEntity coupon) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondEntity.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());
        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE BondEntity SET " +
                "couponSize = : couponsize, " +
                "couponFrequency = :couponfrequency WHERE bondId = :bondid");
        query.setParameter("couponsize", coupon.couponSize);
        query.setParameter("couponfrequency", coupon.couponFrequency);
        query.setParameter("bondid", coupon.bondId);

        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}