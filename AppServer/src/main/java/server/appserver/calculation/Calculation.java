package server.appserver.calculation;

import server.appserver.entity.BondEntity;

import java.util.List;

public class Calculation {

    public static float calculateDuration(List<BondEntity> bonds){
        final float bondPart = 1;
        float portfolioDuration = 0;
        float bondCount = bonds.size();

        for (BondEntity bond: bonds){
            long N = Long.parseLong(bond.nominalCost);
            float c = N / 100 * Long.parseLong(bond.couponRate);
            int n = Integer.parseInt(bond.repaymentPeriod);
            float r = Float.parseFloat(bond.yieldToMaturity) / 100;
            float num = 0;
            float denom = N;
            for (int j=1; j<=n; j++){
                float part = (float) ((j * c) / Math.pow((1 + r), j));
                num += part;
            }
            float bondDuration = (num/denom);
            float interimDuration = bondDuration * (bondPart/bondCount);
            portfolioDuration += interimDuration;
        }
        return portfolioDuration;
    }
}

//Сначала вычисляется дюрация каждой облигации.
// N - номинальная стоимость с - купонная ставка n - период выплаты r - доходность к погашению, j - номер периода
// Для каждого периода выплат находим часть значения дюрации =
//
// Σ(j*c/(1+r)^j)/N * процент данной облигации в портфеле
