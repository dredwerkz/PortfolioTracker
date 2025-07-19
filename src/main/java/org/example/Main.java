package org.example;

import org.example.model.Asset;
import org.example.model.Portfolio;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio();

        while (true) {
            System.out.println("1. Add Asset\n2. Show Portfolio Value\n3. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter symbol (e.g., Bitcoin): ");
                String symbol = scanner.next();
                System.out.print("Enter quantity: ");
                double quantity = scanner.nextDouble();
                portfolio.addAsset(new Asset(symbol, quantity));
            } else if (choice == 2) {
                double totalValue = portfolio.calculateTotalValue();
                System.out.println("Total Portfolio Value = £" + totalValue);
            } else {
                break;
            }
        }
    }
}