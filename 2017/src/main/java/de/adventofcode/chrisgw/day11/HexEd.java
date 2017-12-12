package de.adventofcode.chrisgw.day11;


import java.util.ArrayList;
import java.util.List;


/**
 * <h1><a href="https://adventofcode.com/2017/day/11>Day 11: Hex Ed</a></h1>
 * <pre>
 * Crossing the bridge, you've barely reached the other side of the stream
 * when a program comes up to you, clearly in distress. "It's my child
 * process," she says, "he's gotten lost in an infinite grid!"
 *
 * Fortunately for her, you have plenty of experience with infinite grids.
 *
 * Unfortunately for you, it's a hex grid.
 *
 * The hexagons ("hexes") in this grid are aligned such that adjacent hexes
 * can be found to the north, northeast, southeast, south, southwest, and northwest:
 *
 *   \ n  /
 * nw +--+ ne
 *   /    \
 * -+      +-
 *   \    /
 * sw +--+ se
 *   / s  \
 *
 * You have the path the child process took. Starting where he started, you
 * need to determine the fewest number of steps required to reach him. (A
 * "step" means to move from the hex you are in to any adjacent hex.)
 *
 * For example:
 *
 * - ne,ne,ne is 3 steps away.
 * - ne,ne,sw,sw is 0 steps away (back where you started).
 * - ne,ne,s,s is 2 steps away (se,se).
 * - se,sw,se,sw,sw is 3 steps away (s,s,sw).
 *
 * --- Part Two ---
 *
 * How many steps away is the furthest he ever got from his starting position?
 * </pre>
 */
public class HexEd {


    public static HexCell followDirectionInstructions(String directionStr) {
        String[] splittedDirections = directionStr.split(",");
        List<HexDirection> hexDirections = new ArrayList<>(splittedDirections.length);
        for (String splittedDirection : splittedDirections) {
            hexDirections.add(HexDirection.fromShortName(splittedDirection));
        }
        return followDirectionInstructions(hexDirections);
    }


    public static HexCell followDirectionInstructions(List<HexDirection> hexDirections) {
        HexCell currentHexCell = new HexCell();
        for (HexDirection direction : hexDirections) {
            currentHexCell = currentHexCell.moveToAdjacentHexCell(direction);
        }
        return currentHexCell;
    }


    public static HexCell followDirectionInstructionsAndFindFurthestHexCell(String directionStr) {
        String[] splittedDirections = directionStr.split(",");
        List<HexDirection> hexDirections = new ArrayList<>(splittedDirections.length);
        for (String splittedDirection : splittedDirections) {
            hexDirections.add(HexDirection.fromShortName(splittedDirection));
        }
        return followDirectionInstructionsAndFindFurthestHexCell(hexDirections);
    }

    public static HexCell followDirectionInstructionsAndFindFurthestHexCell(List<HexDirection> hexDirections) {
        HexCell currentHexCell = new HexCell();
        HexCell furthestHexCell = currentHexCell;
        for (HexDirection direction : hexDirections) {
            currentHexCell = currentHexCell.moveToAdjacentHexCell(direction);
            if (currentHexCell.compareTo(furthestHexCell) > 0) {
                furthestHexCell = currentHexCell;
            }
        }
        return furthestHexCell;
    }

}
