package de.adventofcode.chrisgw.day11;

public class HexCell implements Comparable<HexCell> {

    private int x;
    private int y;


    public HexCell() {
        this(0, 0);
    }

    public HexCell(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public HexCell moveToAdjacentHexCell(HexDirection direction) {
        int x = this.x;
        int y = this.y;

        switch (direction) {
        case NORTH:
            y += 2;
            break;
        case NORTH_WEST:
            y++;
            x--;
            break;
        case NORTH_EAST:
            y++;
            x++;
            break;
        case SOUTH:
            y -= 2;
            break;
        case SOUTH_WEST:
            y--;
            x--;
            break;
        case SOUTH_EAST:
            y--;
            x++;
            break;
        default:
            throw new IllegalArgumentException("Unexpected direction " + direction);
        }
        return new HexCell(x, y);
    }


    public int getStepDistanceToHexCell() {
        return getStepDistanceToHexCell(new HexCell());
    }

    public int getStepDistanceToHexCell(HexCell destinationHexCell) {
        int distance = 0;

        HexCell currentHexCell = this;
        while (!currentHexCell.equals(destinationHexCell)) {

            if (currentHexCell.x > destinationHexCell.x && currentHexCell.y > destinationHexCell.y) {
                currentHexCell = currentHexCell.moveToAdjacentHexCell(HexDirection.SOUTH_WEST);

            } else if (currentHexCell.x > destinationHexCell.x && currentHexCell.y <= destinationHexCell.y) {
                currentHexCell = currentHexCell.moveToAdjacentHexCell(HexDirection.NORTH_WEST);

            } else if (currentHexCell.x < destinationHexCell.x && currentHexCell.y > destinationHexCell.y) {
                currentHexCell = currentHexCell.moveToAdjacentHexCell(HexDirection.SOUTH_EAST);

            } else if (currentHexCell.x < destinationHexCell.x && currentHexCell.y <= destinationHexCell.y) {
                currentHexCell = currentHexCell.moveToAdjacentHexCell(HexDirection.NORTH_EAST);


            } else if (currentHexCell.y < destinationHexCell.y) {
                currentHexCell = currentHexCell.moveToAdjacentHexCell(HexDirection.NORTH);

            } else {
                currentHexCell = currentHexCell.moveToAdjacentHexCell(HexDirection.SOUTH);
            }
            distance++;
        }
        return distance;
    }


    @Override
    public int compareTo(HexCell furthestHexCell) {
        return Integer.compare(this.getStepDistanceToHexCell(), furthestHexCell.getStepDistanceToHexCell());
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof HexCell))
            return false;

        HexCell hexCell = (HexCell) o;

        if (getX() != hexCell.getX())
            return false;
        return getY() == hexCell.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HexCell{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }

}
