package de.adventofcode.chrisgw.day03;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;


public class RucksackWithTwoCompartments {

    private final List<RucksackItem> items = new ArrayList<>();


    public static RucksackWithTwoCompartments parseRucksackContent(String rucksackContentLine) {
        var rucksackWithTwoCompartments = new RucksackWithTwoCompartments();
        for (int i = 0; i < rucksackContentLine.length(); i++) {
            char itemType = rucksackContentLine.charAt(i);
            RucksackItem rucksackItem = new RucksackItem(itemType);
            rucksackWithTwoCompartments.items.add(rucksackItem);
        }
        return rucksackWithTwoCompartments;
    }

    public int compartmentSize() {
        return items.size() / 2;
    }

    public int size() {
        return items.size();
    }


    public List<RucksackItem> itemsFirstCompartment() {
        return items.subList(0, compartmentSize());
    }

    public List<RucksackItem> itemsSecondCompartment() {
        return items.subList(compartmentSize(), items.size());
    }


    public RucksackItem findMisplacedItem() {
        List<RucksackItem> itemsSecondCompartment = itemsSecondCompartment();
        for (RucksackItem item : itemsFirstCompartment()) {
            if (itemsSecondCompartment.contains(item)) {
                return item;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RucksackWithTwoCompartments that)) {
            return false;
        }
        return new EqualsBuilder().append(items, that.items).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(items).toHashCode();
    }

    @Override
    public String toString() {
        return items.toString();
    }

}
