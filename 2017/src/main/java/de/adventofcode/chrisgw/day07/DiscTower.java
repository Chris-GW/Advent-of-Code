package de.adventofcode.chrisgw.day07;

import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.stream.Stream;


public class DiscTower {

    private String name;
    private int weight;

    private DiscTower parent;
    private Set<DiscTower> childs = new HashSet<>();


    public DiscTower(String name, int weight) {
        this(name, weight, null);
    }

    public DiscTower(String name, int weight, DiscTower parent) {
        this.name = name;
        this.weight = weight;
        this.parent = parent;
    }


    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }


    public DiscTower getParent() {
        return parent;
    }


    public Set<DiscTower> getChilds() {
        return childs;
    }

    public void addChildDiscTower(DiscTower discTower) {
        discTower.parent = this;
        childs.add(discTower);
    }


    public int getTotalWeight() {
        return weight + getChildWeightSum();
    }

    public int getChildWeightSum() {
        return getChilds().stream().mapToInt(DiscTower::getTotalWeight).sum();
    }

    public boolean isBalenced() {
        if (childs.isEmpty()) {
            return true;
        }
        IntSummaryStatistics intSummaryStatistics = getChilds().stream()
                .mapToInt(DiscTower::getTotalWeight)
                .summaryStatistics();
        return intSummaryStatistics.getMin() == intSummaryStatistics.getMax();
    }


    public Stream<DiscTower> getAllDiscTowerStream() {
        return Stream.concat(childs.stream().flatMap(DiscTower::getAllDiscTowerStream), Stream.of(this));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DiscTower discTower = (DiscTower) o;

        if (getWeight() != discTower.getWeight())
            return false;
        return getName() != null ? getName().equals(discTower.getName()) : discTower.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getWeight();
        return result;
    }

    @Override
    public String toString() {
        return name + " (" + getTotalWeight() + ")";
    }

}
