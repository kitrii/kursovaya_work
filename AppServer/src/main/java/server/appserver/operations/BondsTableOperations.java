package server.appserver.operations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import server.appserver.entity.BondEntity;
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
    public void addBondInPortfolio(String bondId, String ownerId) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(PortfolioEntity.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());

        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session sessionCreate = sf.openSession();
        Transaction transaction = sessionCreate.beginTransaction();
        PortfolioEntity relationShip = new PortfolioEntity(bondId, ownerId);
        sessionCreate.save(relationShip);
        transaction.commit();
        sessionCreate.close();
    }

    public List<BondEntity> getBondsByOwnerId(String ownerId) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(PortfolioEntity.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());

        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session sessionRead = sf.openSession();
        Transaction transaction = sessionRead.beginTransaction();
        List<Object[]> dbBonds = sessionRead.createNativeQuery("select * from bond b where b.bond_id in (select p.owner_bond_id from portfolio p where p.owner_id = " + ownerId + ")").getResultList();
        List<BondEntity> portfolioBonds = new ArrayList<>();
        for (Object[] dbBond: dbBonds) {
            String bondid = (String) dbBond[1];
            String bondname = (String) dbBond[2];
            String couponfrequency = (String) dbBond[3];
            String couponrate = (String) dbBond[4];
            String nominalcost = (String) dbBond[5];
            String repaymentperiod = (String) dbBond[6];
            String yieldtomaturity = (String) dbBond[7];
            BondEntity bond = new BondEntity(bondid, bondname, nominalcost, couponfrequency, repaymentperiod, couponrate, yieldtomaturity);
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
        Query query = session.createQuery("UPDATE BondEntity SET " +
                "nominalCost = : nominalcost, " +
                "repaymentPeriod = :repaymentperiod, couponRate = :couponrate, " +
                "yieldToMaturity = :yieldtomaturity WHERE bondId = :bondid");
        query.setParameter("nominalcost", bond.nominalCost);
        query.setParameter("repaymentperiod", bond.repaymentPeriod);
        query.setParameter("couponrate", bond.couponRate);
        query.setParameter("yieldtomaturity", bond.yieldToMaturity);
        query.setParameter("bondid", bondId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}