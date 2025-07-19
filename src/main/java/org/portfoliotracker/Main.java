package org.portfoliotracker;

import org.portfoliotracker.db.AssetRepository;
import org.portfoliotracker.services.PortfolioService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        AssetRepository repository = new AssetRepository();
        PortfolioService service = new PortfolioService(repository);

        while (true) {
            System.out.println("1. Add Asset\n2. Show Portfolio Value\n3. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter symbol (e.g., Bitcoin): ");
                String symbol = scanner.next();

                System.out.print("Enter quantity: ");
                double quantity = scanner.nextDouble();

                service.addAsset(symbol, quantity);
            } else if (choice == 2) {
                System.out.println("Total Portfolio Value = £" + service.getTotalValue());
            } else {
                break;
            }
        }
    }
}