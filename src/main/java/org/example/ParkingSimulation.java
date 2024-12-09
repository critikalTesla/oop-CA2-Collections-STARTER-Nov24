package org.example;
import java.util.Scanner;
import java.util.Stack;

public class ParkingSimulation {
    public static void main(String[] args) {
        // Stack to represent the driveway
        Stack<Integer> driveway = new Stack<>();
        // Stack to temporarily hold cars when removing a car from the driveway
        Stack<Integer> street = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter commands (positive to add, negative to remove, 0 to exit):");

        while (true) {
            // Get user input
            int input = scanner.nextInt();

            if (input == 0) {
                // Stop the simulation
                System.out.println("Simulation ended.");
                break;
            } else if (input > 0) {
                // Add a car to the driveway
                driveway.push(input);
                System.out.println("Car " + input + " added to the driveway.");
            } else {
                // Remove a car from the driveway
                int carToRemove = -input;
                boolean found = false;

                // Move cars from driveway to street until the desired car is found
                while (!driveway.isEmpty()) {
                    int topCar = driveway.pop();
                    if (topCar == carToRemove) {
                        found = true;
                        System.out.println("Car " + carToRemove + " removed from the driveway.");
                        break;
                    } else {
                        street.push(topCar);
                    }
                }

                // Move cars back from the street to the driveway
                while (!street.isEmpty()) {
                    driveway.push(street.pop());
                }

                // If the car was not found
                if (!found) {
                    System.out.println("Car " + carToRemove + " is not in the driveway.");
                }
            }

            // Print the current state of the driveway
            System.out.println("Current driveway state: " + driveway);
        }

        scanner.close();
    }
}
