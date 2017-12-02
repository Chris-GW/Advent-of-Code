package de.adventofcode.chrisgw.day02;

import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import java.util.ArrayList;
import java.util.List;


/**
 * --- <a href="http://adventofcode.com/2016/day/2">Day 2: Bathroom Security</a> ---
 * <pre>
 * The document goes on to explain that each button to be pressed can be found
 * by starting on the previous button and moving to adjacent buttons on the
 * keypad: <code>U</code> moves up, <code>D</code> moves down, <code>L</code> moves left, and <code>R</code> moves right.
 * Each line of instructions corresponds to one button, starting at the previous
 * button (or, for the first line, <em>the "5" button</em>); press whatever button
 * you're on at the end of each line. If a move doesn't lead to a button, ignore it.
 *
 * You can't hold it much longer, so you decide to figure out the code as you
 * walk to the bathroom. You picture a keypad like this:
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * Suppose your instructions are:
 * ULL
 * RRDDD
 * LURDL
 * UUUUD
 *
 * - You start at "5" and move up (to "2"), left (to "1"), and left (you
 *   can't, and stay on "1"), so the first button is 1.
 * - Starting from the previous button ("1"), you move right twice (to "3")
 *   and then down three times (stopping at "9" after two moves and
 *   ignoring the third), ending up with 9.
 * - Continuing from "9", you move left, up, right, down, and left, ending with 8.
 * - Finally, you move up four times (stopping at "2"), then down once, ending with 5.
 *
 * So, in this example, the bathroom code is 1985.
 * </pre>
 */
public class BathroomSecurity {

    private static final int[][] KEYPAD = new int[][] { //
            { 1, 2, 3 }, //
            { 4, 5, 6 }, //
            { 7, 8, 9 } };

    private int x = 1;
    private int y = 1;


    public List<Integer> followInstructions(String instructions) {
        String[] instructionRows = instructions.split("\n");
        List<Integer> buttonsToPress = new ArrayList<>(instructionRows.length);

        for (int rowIndex = 0; rowIndex < instructionRows.length; rowIndex++) {
            String instruction = instructionRows[rowIndex];

            for (int instructionIndex = 0; instructionIndex < instruction.length(); instructionIndex++) {
                String moveDirectionCode = String.valueOf(instruction.charAt(instructionIndex));
                MoveDirection moveDirection = MoveDirection.fromDirectionCode(moveDirectionCode);
                followMoveInstruction(moveDirection);
            }
            buttonsToPress.add(getKeypadButton());
        }
        return buttonsToPress;
    }

    private void followMoveInstruction(MoveDirection moveDirection) {
        if (moveDirection == MoveDirection.UP) {
            y -= 1;
        } else if (moveDirection == MoveDirection.DOWN) {
            y += 1;
        } else if (moveDirection == MoveDirection.LEFT) {
            x -= 1;
        } else if (moveDirection == MoveDirection.RICHT) {
            x += 1;
        } else {
            throw new IllegalArgumentException("Unexpected MoveDirection " + moveDirection);
        }
        normalizeKeypadPosition();
    }

    private void normalizeKeypadPosition() {
        if (x < 0) {
            x = 0;
        } else if (x > 2) {
            x = 2;
        }

        if (y < 0) {
            y = 0;
        } else if (y > 2) {
            y = 2;
        }
    }


    private int getKeypadButton() {
        return KEYPAD[y][x];
    }

    @Override
    public String toString() {
        return String.format("(%d;%d)=%d", x, y, getKeypadButton());
    }


    public enum MoveDirection {
        UP("U"), DOWN("D"), LEFT("L"), RICHT("R");

        private String moveDirectionCode;

        MoveDirection(String moveDirectionCode) {
            this.moveDirectionCode = moveDirectionCode;
        }

        public static MoveDirection fromDirectionCode(String moveDirectionCode) {
            for (MoveDirection moveDirection : MoveDirection.values()) {
                if (moveDirection.moveDirectionCode.equalsIgnoreCase(moveDirectionCode)) {
                    return moveDirection;
                }
            }
            throw new EnumConstantNotPresentException(MoveDirection.class, moveDirectionCode);
        }


        public String getMoveDirectionCode() {
            return moveDirectionCode;
        }

        @Override
        public String toString() {
            return getMoveDirectionCode();
        }

    }


    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No keypad instructions in args[]");
            return;
        }

        String instructions = args[0];
        BathroomSecurity bathroomSecurity = new BathroomSecurity();
        List<Integer> buttonsToPress = bathroomSecurity.followInstructions(instructions);
        for (Integer buttonToPress : buttonsToPress) {
            System.out.print(buttonToPress);
        }
        System.out.println();
    }

}
