package org.example;

import java.util.ArrayList;
import java.util.List;

// Manager class to handle a collection of containers
public class ContainerManager {
    private List<IMeasurableContainer> containers = new ArrayList<>();

    // Add a container to the collection
    public void add(IMeasurableContainer container) {
        containers.add(container);
    }

    // Calculate the total weight of all containers
    public double totalWeight() {
        double totalWeight = 0;
        for (IMeasurableContainer container : containers) {
            totalWeight += container.weight();
        }
        return totalWeight;
    }

    // Calculate the total rectangular volume of all containers
    public double totalRectangularVolume() {
        double totalVolume = 0;
        for (IMeasurableContainer container : containers) {
            totalVolume += container.rectangularVolume();
        }
        return totalVolume;
    }

    // Clear all containers from the collection
    public void clearAll() {
        containers.clear();
    }

    // Get a list of all containers
    public List<IMeasurableContainer> getAllContainers() {
        return new ArrayList<>(containers);
    }
}