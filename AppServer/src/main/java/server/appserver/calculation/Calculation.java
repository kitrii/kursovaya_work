package server.appserver.calculation;

import server.appserver.entity.BondInPortfolioEntity;

import java.util.List;

public class Calculation {

    public static Integer calculateCommonBondsCount(List<BondInPortfolioEntity> bonds){
        Integer Count = 0;
        for (BondInPortfolioEntity bond: bonds) {
            Integer bondCount = Integer.valueOf(bond.count);
            Count+=bondCount;
        }
        return Count;
    }

    public static boolean isCouponExist(BondInPortfolioEntity bond){
        String couponExisting = bond.couponExisting;
        if (couponExisting.equalsIgnoreCase("true")){
            return true;
        }
        return false;
    }

    public static float calculateDuration(List<BondInPortfolioEntity> bonds){
        float portfolioDuration = 0;
        Integer commonBondCount = calculateCommonBondsCount(bonds);
        for (BondInPortfolioEntity bond: bonds){
            float bondDuration = 0;
            float bondDurationWithRepaymentPeriod = 0;
            float bondProportion = Float.valueOf(bond.count)/commonBondCount;
            long nominalCost = Long.parseLong(bond.nominalCost);
            int repaymentPeriodYears = Integer.parseInt(bond.repaymentPeriod);
            if (isCouponExist(bond)) {
                float couponSize = Float.valueOf(bond.couponSize);
                float couponFrequency = Float.valueOf(bond.couponFrequency);
                float couponYearIncome = couponSize * couponFrequency;
                float couponYearPart = (365/couponFrequency)/365;
                float itogSummaMaturity = (couponYearIncome * repaymentPeriodYears)+nominalCost;
//                float couponFrequencyInYear = 1/couponFrequency;
//                for (int year=1; year<=repaymentPeriodYears; year++){
//                    (couponFrequencyInYear * couponSize) +
//                }
                float num = (couponSize*couponYearPart*couponFrequency + nominalCost);
                bondDuration = num/itogSummaMaturity;
                bondDurationWithRepaymentPeriod = bondDuration * repaymentPeriodYears;
            } else {
                bondDurationWithRepaymentPeriod = repaymentPeriodYears;
            }
            float bondDurationInPortfolio = bondDurationWithRepaymentPeriod * bondProportion;
            portfolioDuration += bondDurationInPortfolio;
        }
        return portfolioDuration;
    }
}

