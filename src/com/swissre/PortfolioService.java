package com.swissre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PortfolioService {
    public void convertPortfolio(Map<String, String> cryptoCoins){
        Map<String, BigDecimal> portfolioEurValue = new HashMap<>();
        ForexService forexService = new ForexService();
        for (Map.Entry<String, String> entry : cryptoCoins.entrySet()) {
            final BigDecimal eurValue = forexService.convert(entry.getKey(), "EUR");
            final BigDecimal cryptoAmount = new BigDecimal(entry.getValue());
            final BigDecimal cryptoEurValue = cryptoAmount.multiply(eurValue);
            portfolioEurValue.put(entry.getKey(), cryptoEurValue);
        }
        portfolioEurValue.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "  : " + entry.getValue());
        });

        BigDecimal total = portfolioEurValue.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println( " Total : " + total.toPlainString());
    }
}
