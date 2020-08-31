package com.swissre;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PortfolioService {
    public void convertPortfolio(Map<String, String> cryptoCoins){
        Map<String, String> portfolioEurValue = new HashMap<>();
        ForexService forexService = new ForexService();
        for (Map.Entry<String, String> entry : cryptoCoins.entrySet()) {
            final BigDecimal eurValue = forexService.convert(entry.getKey(), "EUR");
            final BigDecimal cryptoAmount = new BigDecimal(entry.getValue());
            final BigDecimal cryptoEurValue = cryptoAmount.multiply(eurValue);

        }

    }
}
