package org.portfoliotracker.models;

public class Asset {
    private String symbol;
    private double quantity;

    public Asset(String symbol, double quantity) {
        this.symbol = symbol;
        this.quantity = quantity;
    }

    public String getSymbol() { return symbol; }
    public double getQuantity() { return quantity; }
}
