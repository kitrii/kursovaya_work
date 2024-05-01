package server.appserver.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.appserver.entity.BondEntity;
import server.appserver.entity.BondInPortfolioEntity;
import server.appserver.entity.PortfolioEntity;
import server.appserver.operations.BondsTableOperations;

import java.util.List;

@Service
public class PortfolioService {

    private final BondsTableOperations BTO = new BondsTableOperations();

    @Transactional(readOnly = true)
    public List<BondInPortfolioEntity> getBondsByOwnerId(String ownerId){
        List<BondInPortfolioEntity> result = BTO.getBondsByOwnerId(ownerId);
        return result;
    }

    @Transactional()
    public void addBondInPortfolio(PortfolioEntity portfolio){
        String bondId = portfolio.ownerBondId;
        String ownerId = portfolio.ownerId;
        BTO.addBondInPortfolio(bondId, ownerId);
    }
}
