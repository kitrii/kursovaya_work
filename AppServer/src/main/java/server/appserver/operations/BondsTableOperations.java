package server.appserver.operations;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import server.appserver.entity.BondEntity;
import server.appserver.entity.BondsEntity;
import server.appserver.entity.PortfolioEntity;

import java.util.List;

public class BondsTableOperations {
    public void addBond(BondsEntity bond) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondsEntity.class);

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

    public List<BondsEntity> getBondsByOwnerId(int ownerId) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondsEntity.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());

        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session sessionRead = sf.openSession();
        Transaction transaction = sessionRead.beginTransaction();
        List<BondsEntity> bonds = sessionRead.createQuery("from BondsEntity where ownerid = " + ownerId).getResultList();
        transaction.commit();
        sessionRead.close();
        return bonds;
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
    public void deleteBondByBondIdOwnerId(int bondId, int ownerId) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondsEntity.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());
        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM BondsEntity WHERE bondid = :bondid and ownerid = :ownerid");
        query.setParameter("bondid", bondId);
        query.setParameter("ownerid", ownerId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
    public void editBondByBondIdOwnerId(int bondId, int ownerId, BondsEntity bond) {
        Configuration conf = new Configuration().configure();
        conf.addAnnotatedClass(BondsEntity.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties());
        SessionFactory sf = conf.buildSessionFactory(sBuilder.build());
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE BondsEntity SET " +
                "repaymentperiod = :repaymentperiod, couponrate = :couponrate, " +
                "yieldtomaturity = :yieldtomaturity WHERE bondid = :bondid and ownerid = :ownerid");
        query.setParameter("repaymentperiod", bond.repaymentperiod);
        query.setParameter("couponrate", bond.couponrate);
        query.setParameter("yieldtomaturity", bond.yieldtomaturity);
        query.setParameter("bondid", bondId);
        query.setParameter("ownerid", ownerId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}