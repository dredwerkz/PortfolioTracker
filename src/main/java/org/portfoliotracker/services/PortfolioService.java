package org.portfoliotracker.services;

import org.portfoliotracker.db.AssetRepository;
import org.portfoliotracker.models.Asset;
import org.portfoliotracker.models.Portfolio;

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
