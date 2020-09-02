package com.swissre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PortfolioService {

    /**
     *
     * @param cryptoCoins Map containing bitcoin values
     */
    public void convertPortfolio(Map<String, String> cryptoCoins) {
        Map<String, BigDecimal> portfolioEurValue = new HashMap<>();
        ForexService forexService = new ForexService();
        for (Map.Entry<String, String> entry : cryptoCoins.entrySet()) {
            final BigDecimal eurValue = forexService.convert(entry.getKey(), "EUR");
            final BigDecimal cryptoAmount = new BigDecimal(entry.getValue());
            final BigDecimal cryptoEurValue = cryptoAmount.multiply(eurValue);
            portfolioEurValue.put(entry.getKey(), cryptoEurValue);
        }
        showPortfolio(portfolioEurValue);
    }

    /**
     * Displays bitcoin portfolio in euro
     * @param portfolioEurValue map containing euro value of bitcoin
     */
    private void showPortfolio(Map<String, BigDecimal> portfolioEurValue) {
        portfolioEurValue.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "  : " + entry.getValue());
        });

        BigDecimal total = portfolioEurValue.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println( "Total : " + total.toPlainString());
    }
}