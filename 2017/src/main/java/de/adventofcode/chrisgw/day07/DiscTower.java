package de.adventofcode.chrisgw.day07;

import sun.plugin.com.DispatchClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DiscTower {

    private String name;
    private int weight;

    private DiscTower parent;
    private List<DiscTower> childs = new ArrayList<>();


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


    public List<DiscTower> getChilds() {
        return childs;
    }

    public void addChildDiscTower(DiscTower discTower) {
        discTower.parent = this;
        childs.add(discTower);
    }


    @Override
    public String toString() {
        return name + " (" + weight + ")";
    }

}
