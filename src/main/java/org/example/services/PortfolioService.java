package org.example.services;

import org.example.db.AssetRepository;
import org.example.models.Asset;
import org.example.models.Portfolio;

import javax.sound.sampled.Port;
import java.io.IOException;

public class PortfolioService {
    private final Portfolio portfolio;
    private final AssetRepository repository;

    public PortfolioService(AssetRepository repository) {
        this.repository = repository;
        this.portfolio = new Portfolio();
        repository.findAll().forEach(portfolio::addAsset);
    }

    public void addAsset(String symbol, double quantity) {
        Asset asset = new Asset(symbol, quantity);
        portfolio.addAsset(asset);
        repository.save(asset);
    }

    public double getTotalValue() throws IOException, InterruptedException {
        return portfolio.calculateTotalValue();
    }
}
