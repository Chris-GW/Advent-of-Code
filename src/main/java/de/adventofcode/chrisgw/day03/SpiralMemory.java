package de.adventofcode.chrisgw.day03;

import java.util.*;


public class SpiralMemory {

    private SortedMap<Integer, SpiralMemoryData> spiralMemory = new TreeMap<>();


    public SpiralMemory() {
        SpiralMemoryData firstMemoryData = new SpiralMemoryData(0, 0, 1);
        this.spiralMemory.put(firstMemoryData.value, firstMemoryData);
    }


    public int calculateAccessStepsForMemoryDataValue(int value) {
        int lastMemoryData = spiralMemory.lastKey();
        if (lastMemoryData >= value) {
            return spiralMemory.get(value).getDistanceToPort();
        }
        for (int i = lastMemoryData; i < value; i++) {
            addSpiralMemoryData();
        }
        return spiralMemory.get(value).getDistanceToPort();
    }

    private SpiralMemoryData addSpiralMemoryData() {
        int lastData = spiralMemory.lastKey();
        SpiralMemoryData lastMemoryData = spiralMemory.get(lastData);
        int newData = lastData + 1;
        SpiralMemoryData newMemoryData = lastMemoryData.getNextMemoryData();
        spiralMemory.put(newMemoryData.value, newMemoryData);
        return newMemoryData;
    }


    public static class SpiralMemoryData {

        public int x;
        public int y;
        public int value;

        public SpiralMemoryData rightMemoryData;
        public SpiralMemoryData topMemoryData;
        public SpiralMemoryData leftMemoryData;
        public SpiralMemoryData bottomMemoryData;


        public SpiralMemoryData(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }


        public SpiralMemoryData getNextMemoryData() {
            int newData = value + 1;
            SpiralMemoryData newMemoryData;

            if (leftMemoryData != null && topMemoryData == null) {
                newMemoryData = new SpiralMemoryData(x, y + 1, newData);
                topMemoryData = newMemoryData;
                newMemoryData.bottomMemoryData = this;
                if (leftMemoryData != null) {
                    newMemoryData.leftMemoryData = leftMemoryData.topMemoryData;
                }

            } else if (bottomMemoryData != null && leftMemoryData == null) {
                newMemoryData = new SpiralMemoryData(x - 1, y, newData);
                leftMemoryData = newMemoryData;
                newMemoryData.rightMemoryData = this;
                if (bottomMemoryData != null) {
                    newMemoryData.bottomMemoryData = bottomMemoryData.leftMemoryData;
                }

            } else if (rightMemoryData != null && bottomMemoryData == null) {
                newMemoryData = new SpiralMemoryData(x, y - 1, newData);
                bottomMemoryData = newMemoryData;
                newMemoryData.topMemoryData = this;
                if (rightMemoryData != null) {
                    newMemoryData.rightMemoryData = rightMemoryData.bottomMemoryData;
                }

            } else if (topMemoryData != null && rightMemoryData == null) {
                newMemoryData = new SpiralMemoryData(x + 1, y, newData);
                rightMemoryData = newMemoryData;
                newMemoryData.leftMemoryData = this;
                if (topMemoryData != null) {
                    newMemoryData.topMemoryData = topMemoryData.rightMemoryData;
                }

            } else { // first memoryCell
                newMemoryData = new SpiralMemoryData(x + 1, y, newData);
                rightMemoryData = newMemoryData;
                newMemoryData.leftMemoryData = this;
            }
            return newMemoryData;
        }


        public int getDistanceToPort() {
            return Math.abs(x) + Math.abs(y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            SpiralMemoryData that = (SpiralMemoryData) o;

            return value == that.value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + x + ";" + y + ")=" + value;
        }

    }

}
