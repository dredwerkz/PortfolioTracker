package org.portfoliotracker.db;

import org.portfoliotracker.models.Asset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssetRepository {

    public AssetRepository() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS assets (" +
                "symbol TEXT NOT NULL," +
                "quantity REAL NOT NULL" +
                ");";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create assets table", e);
        }
    }

    public void save(Asset asset) {
        String sql = "INSERT INTO assets (symbol, quantity) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, asset.getSymbol());
            pstmt.setDouble(2, asset.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save asset", e);
        }
    }

    public List<Asset> findAll() {
        List<Asset> assets = new ArrayList<>();
        String sql = "SELECT symbol, quantity FROM assets";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String symbol = rs.getString("symbol");
                double quantity = rs.getDouble("quantity");
                assets.add(new Asset(symbol, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load assets", e);
        }

        return assets;
    }
}

