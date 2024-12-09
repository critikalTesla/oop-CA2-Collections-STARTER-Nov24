package org.example;

// Class representing a pyramid-shaped container
public class Pyramid implements IMeasurableContainer {
    private double length, sideLength, weight;

    public Pyramid(double length, double sideLength, double weight) {
        this.length = length;
        this.sideLength = sideLength;
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public double getSideLength() {
        return sideLength;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public double rectangularVolume() {
        return length * sideLength * sideLength; // Smallest rectangular box
    }
}