package de.adventofcode.chrisgw.day19;

public record ResourceQuantity(Resource resource, int quantity) {


    public boolean isNegativ() {
        return quantity() < 0;
    }

    public ResourceQuantity negativ() {
        int negativQuantity = this.quantity() * -1;
        return new ResourceQuantity(this.resource(), negativQuantity);
    }


    @Override
    public String toString() {
        return quantity + " " + resource;
    }

}
