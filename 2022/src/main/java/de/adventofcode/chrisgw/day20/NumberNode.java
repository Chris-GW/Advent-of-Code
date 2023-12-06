package de.adventofcode.chrisgw.day20;

import lombok.Getter;

import java.util.stream.Stream;


@Getter
class NumberNode {

    private final long value;
    private NumberNode nextNode;
    private NumberNode previousNode;


    public NumberNode(long value) {
        this.value = value;
    }


    public void setPreviousNode(NumberNode previousNode) {
        this.previousNode = previousNode;
        previousNode.nextNode = this;
    }

    public void setNextNode(NumberNode nextNode) {
        this.nextNode = nextNode;
        nextNode.previousNode = this;
    }


    public void remove() {
        this.previousNode.setNextNode(this.nextNode);
    }


    public Stream<NumberNode> forwardNodes() {
        return Stream.iterate(this, NumberNode::getNextNode);
    }

    public Stream<NumberNode> backwardsNodes() {
        return Stream.iterate(this, NumberNode::getPreviousNode);
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
