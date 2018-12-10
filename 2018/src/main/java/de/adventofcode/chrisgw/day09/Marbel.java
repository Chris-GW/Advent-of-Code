package de.adventofcode.chrisgw.day09;

import lombok.Getter;

import java.util.stream.Stream;


public class Marbel {

    @Getter
    private final int value;

    @Getter
    private Marbel clockwiseMarbel;

    @Getter
    private Marbel counterClockwiseMarbel;


    public Marbel(int value) {
        this.value = value;
        this.clockwiseMarbel = this;
        this.counterClockwiseMarbel = this;
    }


    public Marbel removeMarbel(int marbelStep) {
        return marbelAt(marbelStep).removeMarbel();
    }

    public void placeMarbelBetween(Marbel marbel, int marbelStepA, int marbelStepB) {
        Marbel marbelA = marbelAt(marbelStepA);
        Marbel marbelB = marbelAt(marbelStepB);
        marbel.setCounterClockwiseMarbel(marbelA);
        marbel.setClockwiseMarbel(marbelB);
    }

    public Marbel marbelAt(int marbelStep) {
        boolean clockwise = marbelStep >= 0;
        marbelStep = Math.abs(marbelStep);
        if (clockwise) {
            return clockwiseMarbels().skip(marbelStep).findFirst().orElseThrow(IllegalStateException::new);
        } else {
            return counterClockwiseMarbels().skip(marbelStep).findFirst().orElseThrow(IllegalStateException::new);
        }
    }


    public boolean isDivisibleBy(int number) {
        return value % number == 0;
    }

    public Marbel removeMarbel() {
        Marbel previousMarbel = marbelAt(-1);
        Marbel nextMarbel = marbelAt(1);
        previousMarbel.setClockwiseMarbel(nextMarbel);
        return this;
    }


    public void setClockwiseMarbel(Marbel marbel) {
        this.clockwiseMarbel = marbel;
        marbel.counterClockwiseMarbel = this;
    }

    public void setCounterClockwiseMarbel(Marbel marbel) {
        this.counterClockwiseMarbel = marbel;
        marbel.clockwiseMarbel = this;
    }


    public Stream<Marbel> clockwiseMarbels() {
        return Stream.iterate(this, Marbel::getClockwiseMarbel);
    }

    public Stream<Marbel> counterClockwiseMarbels() {
        return Stream.iterate(this, Marbel::getCounterClockwiseMarbel);
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
