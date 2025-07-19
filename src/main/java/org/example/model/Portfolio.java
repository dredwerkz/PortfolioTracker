package org.example.model;

import org.example.services.PriceFetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<Asset> assets = new ArrayList<>();

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    public double calculateTotalValue() throws IOException, InterruptedException {
        double total = 0;
        for (Asset asset : assets) {
            double price = PriceFetcher.getPrice(asset.getSymbol());
            total += price * asset.getQuantity();
        }

        return total;
    }
}
