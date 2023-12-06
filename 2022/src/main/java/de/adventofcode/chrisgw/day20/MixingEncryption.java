package de.adventofcode.chrisgw.day20;

import java.util.Arrays;
import java.util.List;


public class MixingEncryption {


    private int currentIndex;
    private NumberNode nullNode;
    private final List<NumberNode> nodes;


    public MixingEncryption(long[] originalNumbers) {
        this.currentIndex = 0;
        this.nodes = Arrays.stream(originalNumbers).mapToObj(NumberNode::new).toList();
        for (int i = 0; i < size() - 1; i++) {
            NumberNode currentNode = nodes.get(i);
            currentNode.setNextNode(nodes.get(i + 1));
            if (currentNode.getValue() == 0) {
                nullNode = currentNode;
            }
        }
        NumberNode firstNode = nodes.get(0);
        NumberNode lastNode = nodes.get(size() - 1);
        firstNode.setPreviousNode(lastNode);
    }


    public boolean hasNextMixing() {
        return currentIndex < size();
    }

    private int size() {
        return nodes.size();
    }


    public void doMixing() {
        NumberNode node = nodes.get(currentIndex++);
        long value = node.getValue();
        if (value > 0) {
            long shift = value % (size() - 1);
            NumberNode previousNode = node.forwardNodes().skip(shift).findFirst().orElseThrow();
            NumberNode nextNode = previousNode.getNextNode();
            node.remove();
            node.setPreviousNode(previousNode);
            node.setNextNode(nextNode);
        } else if (value < 0) {
            long shift = Math.abs(value) % (size() - 1);
            NumberNode nextNode = node.backwardsNodes().skip(shift).findFirst().orElseThrow();
            NumberNode previousNode = nextNode.getPreviousNode();
            node.remove();
            node.setPreviousNode(previousNode);
            node.setNextNode(nextNode);
        }
    }


    public long nthNode(long index) {
        return nullNode.forwardNodes().skip(index % size()).findFirst().map(NumberNode::getValue).orElseThrow();
    }

    public void reset() {
        currentIndex = 0;
    }


    @Override
    public String toString() {
        return Arrays.toString(nullNode.forwardNodes().limit(size()).mapToLong(NumberNode::getValue).toArray());
    }

}
