package com.swissre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForexService {

    /**
     *
     * @param fsym
     * @param tsym
     * @return Euro value
     */
    public BigDecimal convert(String fsym, String tsym) {
        try {
            final String output = getEuroValue(fsym, tsym);
            final String[] splitted = output.replaceAll("}", "").split(":");
            if (splitted != null) return new BigDecimal(String.valueOf(splitted[1]));
        } catch (Exception e) {
            System.out.println("Exception in calling web service:- " + e);
        }
        return new BigDecimal(String.valueOf(BigDecimal.ZERO));
    }

    /**
     *
     * @param fsym
     * @param tsym
     * @return
     * @throws IOException
     */
    private String getEuroValue(String fsym, String tsym) throws IOException {
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
        String output = br.readLine();
        conn.disconnect();
        return output;
    }
}
