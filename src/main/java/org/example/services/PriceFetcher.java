package org.example.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PriceFetcher {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static double getPrice(String symbol) throws IOException, InterruptedException {
        String url = "https://api.coingecko.com/api/v3/coins/markets?ids=" + symbol + "&vs_currency=usd";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonArray jsonArray = JsonParser.parseString(response.body()).getAsJsonArray();

        JsonObject coinData = jsonArray.get(0).getAsJsonObject();

        return coinData.get("current_price").getAsDouble();

    }
}
