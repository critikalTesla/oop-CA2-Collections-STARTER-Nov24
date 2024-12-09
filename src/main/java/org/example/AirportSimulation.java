package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AirportSimulation {

    public static void main(String[] args) {
        // Queues for takeoff and landing
        Queue<String> takeoffQueue = new LinkedList<>();
        Queue<String> landingQueue = new LinkedList<>();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands (takeoff flightCode, land flightCode, next, quit):");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();
            String[] parts = command.split("\\s+", 2);

            if (parts.length == 0) continue;

            switch (parts[0].toLowerCase()) {
                case "takeoff":
                    if (parts.length < 2) {
                        System.out.println("Error: Flight code is required for takeoff.");
                        break;
                    }
                    takeoffQueue.offer(parts[1]);
                    System.out.println("Flight " + parts[1] + " queued for takeoff.");
                    break;

                case "land":
                    if (parts.length < 2) {
                        System.out.println("Error: Flight code is required for landing.");
                        break;
                    }
                    landingQueue.offer(parts[1]);
                    System.out.println("Flight " + parts[1] + " queued for landing.");
                    break;

                case "next":
                    if (!landingQueue.isEmpty()) {
                        // Landing has priority
                        String landingFlight = landingQueue.poll();
                        System.out.println("Flight " + landingFlight + " has landed.");
                    } else if (!takeoffQueue.isEmpty()) {
                        // If no landing flights, allow takeoff
                        String takeoffFlight = takeoffQueue.poll();
                        System.out.println("Flight " + takeoffFlight + " has taken off.");
                    } else {
                        System.out.println("No flights are waiting.");
                    }
                    break;

                case "quit":
                    System.out.println("Simulation terminated.");
                    return;

                default:
                    System.out.println("Invalid command. Use takeoff, land, next, or quit.");
            }
        }
    }
}

