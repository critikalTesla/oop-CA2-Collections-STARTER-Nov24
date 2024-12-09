package org.example;

import java.util.*;

public class MultiAirportSimulation {

    public static void main(String[] args) {
        // Map to store queues for each airport
        Map<String, Airport> airportMap = new HashMap<>();

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands (takeoff airport flightCode, land airport flightCode, next airport, quit):");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();
            String[] parts = command.split("\\s+");

            if (parts.length == 0) continue;

            switch (parts[0].toLowerCase()) {
                case "takeoff":
                    if (parts.length < 3) {
                        System.out.println("Error: Airport and flightCode are required for takeoff.");
                        break;
                    }

                    String takeoffAirport = parts[1];
                    String takeoffFlight = parts[2];
                    airportMap.putIfAbsent(takeoffAirport, new Airport(takeoffAirport));
                    airportMap.get(takeoffAirport).addTakeoff(takeoffFlight);

                    System.out.println("Flight " + takeoffFlight + " is queued for takeoff at " + takeoffAirport + ".");
                    break;

                case "land":
                    if (parts.length < 3) {
                        System.out.println("Error: Airport and flightCode are required for landing.");
                        break;
                    }

                    String landAirport = parts[1];
                    String landFlight = parts[2];
                    airportMap.putIfAbsent(landAirport, new Airport(landAirport));
                    airportMap.get(landAirport).addLanding(landFlight);

                    System.out.println("Flight " + landFlight + " is queued for landing at " + landAirport + ".");
                    break;

                case "next":
                    if (parts.length < 2) {
                        System.out.println("Error: Airport is required for next operation.");
                        break;
                    }

                    String nextAirport = parts[1];
                    if (!airportMap.containsKey(nextAirport)) {
                        System.out.println("Error: No such airport.");
                        break;
                    }

                    airportMap.get(nextAirport).processNext();
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

// Class representing an airport
class Airport {
    private String name;
    private Queue<String> landingQueue;
    private Queue<String> takeoffQueue;

    public Airport(String name) {
        this.name = name;
        this.landingQueue = new LinkedList<>();
        this.takeoffQueue = new LinkedList<>();
    }

    // Add a flight to the landing queue
    public void addLanding(String flightCode) {
        landingQueue.offer(flightCode);
    }

    // Add a flight to the takeoff queue
    public void addTakeoff(String flightCode) {
        takeoffQueue.offer(flightCode);
    }

    // Process the next operation: landing takes priority over takeoff
    public void processNext() {
        if (!landingQueue.isEmpty()) {
            String flight = landingQueue.poll();
            System.out.println("Flight " + flight + " has landed at " + name + ".");
        } else if (!takeoffQueue.isEmpty()) {
            String flight = takeoffQueue.poll();
            System.out.println("Flight " + flight + " has taken off from " + name + ".");
        } else {
            System.out.println("No flights are queued at " + name + ".");
        }
    }
}

