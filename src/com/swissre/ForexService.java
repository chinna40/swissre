package com.swissre;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForexService {
    public BigDecimal convert() {
        try {
            URL url = new URL("https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=EUR");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {

                final String[] splitted = output.replaceAll("}", "").split(":");
                System.out.println(splitted[1]);
                return new BigDecimal(String.valueOf(BigDecimal.ZERO));
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return new BigDecimal(String.valueOf(BigDecimal.ZERO));
    }
}
