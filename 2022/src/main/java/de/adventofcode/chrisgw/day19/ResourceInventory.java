package de.adventofcode.chrisgw.day19;

import java.util.EnumMap;
import java.util.Map;


public class ResourceInventory {

    private final Map<Resource, Integer> inventory;

    public ResourceInventory() {
        this.inventory = new EnumMap<>(Resource.class);
        for (Resource resource : Resource.values()) {
            this.inventory.put(resource, 0);
        }
    }

    public ResourceInventory(ResourceInventory otherResourceInventory) {
        this.inventory = new EnumMap<>(otherResourceInventory.inventory);
    }


    public boolean contains(Resource resource, int quantity) {
        int storedQuantity = get(resource);
        return storedQuantity >= quantity;
    }

    public boolean contains(ResourceQuantity resourceQuantity) {
        return contains(resourceQuantity.resource(), resourceQuantity.quantity());
    }


    public int get(Resource resource) {
        return inventory.getOrDefault(resource, 0);
    }

    public int add(Resource resource, int quantity) {
        if (quantity < 0 && get(resource) < quantity) {
            throw new IllegalArgumentException("could not remove " + quantity);
        }
        return inventory.merge(resource, quantity, Integer::sum);
    }

    public int add(ResourceQuantity resourceQuantity) {
        return add(resourceQuantity.resource(), resourceQuantity.quantity());
    }


    public int subtract(ResourceQuantity resourceQuantity) {
        return add(resourceQuantity.negativ());
    }


    public int totalSize() {
        return inventory.values().stream().reduce(Integer::sum).orElse(0);
    }


    @Override
    public String toString() {
        return inventory.toString();
    }

}
