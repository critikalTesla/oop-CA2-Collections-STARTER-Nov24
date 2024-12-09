package org.example;

import java.util.List;

// Main class to test the functionality
public class Main {
    public static void main(String[] args) {
        // Create a container manager
        ContainerManager manager = new ContainerManager();

        // Create container objects
        Box box1 = new Box(2, 3, 4, 10);
        Cylinder cylinder1 = new Cylinder(5, 3, 15);
        Pyramid pyramid1 = new Pyramid(4, 2, 8);

        // Add containers to the manager
        manager.add(box1);
        manager.add(cylinder1);
        manager.add(pyramid1);

        // Display total weight and rectangular volume
        System.out.println("Total Weight: " + manager.totalWeight());
        System.out.println("Total Rectangular Volume: " + manager.totalRectangularVolume());

        // Display information about each container
        List<IMeasurableContainer> containers = manager.getAllContainers();
        for (IMeasurableContainer container : containers) {
            if (container instanceof Box) {
                Box box = (Box) container;
                System.out.println("Box - Length: " + box.getLength() +
                        ", Width: " + box.getWidth() +
                        ", Depth: " + box.getDepth() +
                        ", Weight: " + box.getWeight());
            } else if (container instanceof Cylinder) {
                Cylinder cylinder = (Cylinder) container;
                System.out.println("Cylinder - Height: " + cylinder.getHeight() +
                        ", Diameter: " + cylinder.getDiameter() +
                        ", Weight: " + cylinder.getWeight());
            } else if (container instanceof Pyramid) {
                Pyramid pyramid = (Pyramid) container;
                System.out.println("Pyramid - Length: " + pyramid.getLength() +
                        ", Side Length: " + pyramid.getSideLength() +
                        ", Weight: " + pyramid.getWeight());
            }
        }

        // Clear all containers
        manager.clearAll();
        System.out.println("All containers cleared.");
    }
}