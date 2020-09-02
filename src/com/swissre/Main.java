package com.swissre;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            final URI uri = ClassLoader.getSystemResource("bobs_crypto.txt").toURI();
            final Map<String, String> cryptoCoins = Files.lines(Paths.get(uri)).map(s -> s.split("=", 2))
                    .collect(Collectors.toMap(a -> a[0], a -> a.length > 1 ? a[1] : ""));

            PortfolioService portfolioService = new PortfolioService();
            portfolioService.convertPortfolio(cryptoCoins);
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
