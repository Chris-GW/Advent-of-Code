package de.adventofcode.chrisgw.day08;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@EqualsAndHashCode
public class LicenseNode {

    @Getter
    private final int id;
    private final List<Integer> metaData;

    private LicenseNode parentNode;
    private List<LicenseNode> childNodes;


    public LicenseNode(int id) {
        this(id, 0, 0);
    }

    public LicenseNode(int id, int childNodeQuantity, int metaDataQuantity) {
        this.id = id;
        this.childNodes = new ArrayList<>(childNodeQuantity);
        this.metaData = new ArrayList<>(metaDataQuantity);
    }


    public IntStream metaData() {
        return metaData.stream().mapToInt(Integer::intValue);
    }

    public Stream<LicenseNode> childNodes() {
        return childNodes.stream();
    }


    public int getChildNodeQuantity() {
        return childNodes.size();
    }

    public int getMetaDataQuantity() {
        return metaData.size();
    }


    public void addMetaData(int metaData) {
        this.metaData.add(metaData);
    }

    public void addChild(LicenseNode childNode) {
        childNode.parentNode = this;
        this.childNodes.add(childNode);
    }


    @Override
    public String toString() {
        String idStr;
        if (id < 'Z' - 'A') {
            idStr = String.valueOf((char) ('A' + id));
        } else {
            idStr = String.valueOf(id);
        }
        return idStr + " " + metaData;
    }

}
