package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Block {
    int quantity;
    double price;

    public Block(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
}

public class StockGainCalculator {

    public static void main(String[] args) {
        // Queue to hold blocks of shares
        Queue<Block> stockQueue = new LinkedList<>();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands (buy quantity price, sell quantity, quit):");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();
            String[] parts = command.split("\\s+");

            if (parts.length == 0) continue;

            switch (parts[0].toLowerCase()) {
                case "buy":
                    if (parts.length < 3) {
                        System.out.println("Error: Quantity and price are required for buy.");
                        break;
                    }

                    try {
                        int quantity = Integer.parseInt(parts[1]);
                        double price = Double.parseDouble(parts[2]);
                        stockQueue.offer(new Block(quantity, price));
                        System.out.println("Bought " + quantity + " shares at $" + price + " per share.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid quantity or price.");
                    }
                    break;

                case "sell":
                    if (parts.length < 2) {
                        System.out.println("Error: Quantity is required for sell.");
                        break;
                    }

                    try {
                        int sellQuantity = Integer.parseInt(parts[1]);
                        double totalGain = 0.0;

                        while (sellQuantity > 0 && !stockQueue.isEmpty()) {
                            Block currentBlock = stockQueue.peek();

                            if (currentBlock.quantity <= sellQuantity) {
                                // Sell the entire block
                                double gain = currentBlock.quantity * (15 - currentBlock.price);
                                totalGain += gain;
                                sellQuantity -= currentBlock.quantity;
                                stockQueue.poll();
                            } else {
                                // Sell part of the block
                                double gain = sellQuantity * (15 - currentBlock.price);
                                totalGain += gain;
                                currentBlock.quantity -= sellQuantity;
                                sellQuantity = 0;
                            }
                        }

                        if (sellQuantity > 0) {
                            System.out.println("Error: Not enough shares to sell.");
                        } else {
                            System.out.printf("Sold shares for a total gain of $%.2f%n", totalGain);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid quantity.");
                    }
                    break;

                case "quit":
                    System.out.println("Simulation terminated.");
                    return;

                default:
                    System.out.println("Invalid command. Use buy, sell, or quit.");
            }
        }
    }
}

