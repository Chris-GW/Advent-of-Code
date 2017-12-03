package de.adventofcode.chrisgw.day03;

import java.util.*;


public class SpiralMemory {


    private SortedMap<Integer, SpiralMemoryData> spiralMemory = new TreeMap<>();
    private SpiralMemoryDataValueCalculater valueCalculater;


    public SpiralMemory(SpiralMemoryDataValueCalculater valueCalculater) {
        this.valueCalculater = Objects.requireNonNull(valueCalculater);
        SpiralMemoryData firstMemoryData = new SpiralMemoryData(0, 0, 1);
        this.spiralMemory.put(firstMemoryData.value, firstMemoryData);
    }


    public SpiralMemoryData getSpiralMemoryData(int value) {
        SpiralMemoryData spiralMemoryData = spiralMemory.get(value);
        if (spiralMemoryData != null) {
            return spiralMemoryData;
        }

        spiralMemoryData = spiralMemory.get(spiralMemory.lastKey());
        while (spiralMemoryData.value < value) {
            spiralMemoryData = addSpiralMemoryData();
        }
        return spiralMemoryData;
    }

    private SpiralMemoryData addSpiralMemoryData() {
        SpiralMemoryData lastMemoryData = spiralMemory.get(spiralMemory.lastKey());
        SpiralMemoryData newMemoryData = lastMemoryData.getNextMemoryData();
        newMemoryData.value = valueCalculater.calculateMemoryValue(newMemoryData);
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
            SpiralMemoryData newMemoryData;

            if (leftMemoryData != null && topMemoryData == null) {
                newMemoryData = new SpiralMemoryData(x, y + 1, value);
                topMemoryData = newMemoryData;
                newMemoryData.bottomMemoryData = this;
                if (leftMemoryData != null) {
                    newMemoryData.leftMemoryData = leftMemoryData.topMemoryData;
                }

            } else if (bottomMemoryData != null && leftMemoryData == null) {
                newMemoryData = new SpiralMemoryData(x - 1, y, value);
                leftMemoryData = newMemoryData;
                newMemoryData.rightMemoryData = this;
                if (bottomMemoryData != null) {
                    newMemoryData.bottomMemoryData = bottomMemoryData.leftMemoryData;
                }

            } else if (rightMemoryData != null && bottomMemoryData == null) {
                newMemoryData = new SpiralMemoryData(x, y - 1, value);
                bottomMemoryData = newMemoryData;
                newMemoryData.topMemoryData = this;
                if (rightMemoryData != null) {
                    newMemoryData.rightMemoryData = rightMemoryData.bottomMemoryData;
                }

            } else if (topMemoryData != null && rightMemoryData == null) {
                newMemoryData = new SpiralMemoryData(x + 1, y, value);
                rightMemoryData = newMemoryData;
                newMemoryData.leftMemoryData = this;
                if (topMemoryData != null) {
                    newMemoryData.topMemoryData = topMemoryData.rightMemoryData;
                }

            } else { // first memoryCell
                newMemoryData = new SpiralMemoryData(x + 1, y, value);
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

            if (x != that.x)
                return false;
            if (y != that.y)
                return false;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + value;
            return result;
        }

        @Override
        public String toString() {
            return "(" + x + ";" + y + ")=" + value;
        }

    }


    public interface SpiralMemoryDataValueCalculater {

        int calculateMemoryValue(SpiralMemoryData memoryData);

    }


    public static SpiralMemoryDataValueCalculater increaseValueByOneCalculater() {
        return memoryData -> memoryData.value + 1;
    }

    public static SpiralMemoryDataValueCalculater sumAdjustedDataValuesCalculater() {
        return (SpiralMemoryData memoryData) -> {
            Set<SpiralMemoryData> memoryDataToSum = new HashSet<>();
            if (memoryData.rightMemoryData != null) {
                memoryDataToSum.add(memoryData.rightMemoryData);
                memoryDataToSum.add(memoryData.rightMemoryData.topMemoryData);
                memoryDataToSum.add(memoryData.rightMemoryData.bottomMemoryData);
            }
            if (memoryData.topMemoryData != null) {
                memoryDataToSum.add(memoryData.topMemoryData);
                memoryDataToSum.add(memoryData.topMemoryData.leftMemoryData);
                memoryDataToSum.add(memoryData.topMemoryData.rightMemoryData);
            }
            if (memoryData.leftMemoryData != null) {
                memoryDataToSum.add(memoryData.leftMemoryData);
                memoryDataToSum.add(memoryData.leftMemoryData.topMemoryData);
                memoryDataToSum.add(memoryData.leftMemoryData.bottomMemoryData);
            }
            if (memoryData.bottomMemoryData != null) {
                memoryDataToSum.add(memoryData.bottomMemoryData);
                memoryDataToSum.add(memoryData.bottomMemoryData.leftMemoryData);
                memoryDataToSum.add(memoryData.bottomMemoryData.rightMemoryData);
            }
            return memoryDataToSum.stream().filter(Objects::nonNull).mapToInt((data) -> data.value).sum();
        };
    }

}
