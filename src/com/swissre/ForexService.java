package com.swissre;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForexService {
    public BigDecimal convert(String fsym, String tsym) {
        try {
            final String urlString = "https://min-api.cryptocompare.com/data/price?fsym="+fsym+"&tsyms="+tsym;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                final String[] splitted = output.replaceAll("}", "").split(":");
                return new BigDecimal(String.valueOf(splitted[1]));
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return new BigDecimal(String.valueOf(BigDecimal.ZERO));
    }
}
