package main.java.de.adventofcode.chrisgw.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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
 *
 * --- Part Two ---
 *
 * You finally arrive at the bathroom (it's a several minute walk from the
 * lobby so visitors can behold the many fancy conference rooms and water
 * coolers on this floor) and go to punch in the code. Much to your bladder's
 * dismay, the keypad is not at all like you imagined it. Instead, you are
 * confronted with the result of hundreds of man-hours of bathroom-keypad-design meetings:
 *     1
 *   2 3 4
 * 5 6 7 8 9
 *   A B C
 *     D
 *
 * You still start at "5" and stop when you're at an edge, but given the same
 * instructions as above, the outcome is very different:
 *
 * You start at "5" and don't move at all (up and left are both edges), ending at 5.
 * Continuing from "5", you move right twice and down three times (through "6", "7", "B", "D", "D"), ending at D.
 * Then, from "D", you move five more times (through "D", "B", "C", "C", "B"), ending at B.
 * Finally, after five more moves, you end at 3.
 * So, given the actual keypad layout, the code would be 5DB3.
 * </pre>
 */
public class BathroomSecurity {

    private Keypad keypad;


    public BathroomSecurity(Keypad keypad) {
        this.keypad = Objects.requireNonNull(keypad);
    }


    public List<String> followInstructions(String instructions) {
        String[] instructionRows = instructions.split("\n");
        List<String> buttonsToPress = new ArrayList<>(instructionRows.length);

        System.out.println(keypad);
        for (int rowIndex = 0; rowIndex < instructionRows.length; rowIndex++) {
            String instruction = instructionRows[rowIndex];

            System.out.printf("'%s'", keypad.getCurrentKey());
            for (int instructionIndex = 0; instructionIndex < instruction.length(); instructionIndex++) {
                String moveDirectionCode = String.valueOf(instruction.charAt(instructionIndex));
                MoveDirection moveDirection = MoveDirection.fromDirectionCode(moveDirectionCode);
                if (keypad.canMove(moveDirection)) {
                    keypad.move(moveDirection);
                }
                System.out.printf("-[%s]->'%s'", moveDirection, keypad.getCurrentKey());
            }
            System.out.println();
            System.out.println(keypad);
            buttonsToPress.add(keypad.getCurrentKey());
        }
        return buttonsToPress;
    }


    @Override
    public String toString() {
        return keypad.toString();
    }


    public interface Keypad {

        String getCurrentKey();

        void move(MoveDirection moveDirection);

        boolean canMove(MoveDirection moveDirection);

    }


    public static abstract class GridKeypad implements Keypad {

        protected int x;
        protected int y;


        public GridKeypad(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public abstract String[][] getGridKeypad();

        protected String getKeypadKey(int x, int y) {
            return getGridKeypad()[y][x];
        }

        public boolean isAviableKeypadKey(int x, int y) {
            String[][] gridKeypad = getGridKeypad();
            if (y < 0 || y >= gridKeypad.length) {
                return false;
            }
            if (x < 0 || x >= gridKeypad[y].length) {
                return false;
            }
            return gridKeypad[y][x] != null;
        }


        @Override
        public String getCurrentKey() {
            return getKeypadKey(x, y);
        }

        @Override
        public void move(MoveDirection moveDirection) {
            if (!canMove(moveDirection)) {
                throw new IllegalArgumentException("can't move " + moveDirection);
            }
            if (MoveDirection.UP.equals(moveDirection)) {
                y -= 1;
            } else if (MoveDirection.DOWN.equals(moveDirection)) {
                y += 1;
            } else if (MoveDirection.LEFT.equals(moveDirection)) {
                x -= 1;
            } else if (MoveDirection.RICHT.equals(moveDirection)) {
                x += 1;
            }
        }

        @Override
        public boolean canMove(MoveDirection moveDirection) {
            if (moveDirection == null) {
                return false;
            }
            if (MoveDirection.UP.equals(moveDirection) && !isAviableKeypadKey(x, y - 1)) {
                return false;
            } else if (MoveDirection.DOWN.equals(moveDirection) && !isAviableKeypadKey(x, y + 1)) {
                return false;
            } else if (MoveDirection.LEFT.equals(moveDirection) && !isAviableKeypadKey(x - 1, y)) {
                return false;
            } else if (MoveDirection.RICHT.equals(moveDirection) && !isAviableKeypadKey(x + 1, y)) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            String[][] gridKeypad = getGridKeypad();
            for (int y = 0; y < gridKeypad.length; y++) {
                for (int x = 0; x < gridKeypad[y].length; x++) {
                    if (!isAviableKeypadKey(x, y)) {
                        sb.append("    ");
                    } else if (this.y == y && this.x == x) {
                        String keypadKey = getKeypadKey(x, y);
                        sb.append("[").append(keypadKey).append("] ");
                    } else {
                        String keypadKey = getKeypadKey(x, y);
                        sb.append(" ").append(keypadKey).append("  ");
                    }
                }
                sb.append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

    }


    public static class NumberKeypad extends GridKeypad {

        // @formatter:off
        private static final String[][] KEYPAD = new String[][] {
                { "1", "2", "3" },
                { "4", "5", "6" },
                { "7", "8", "9" } };
        // @formatter:on


        public NumberKeypad() {
            super(1, 1);
        }


        @Override
        public String[][] getGridKeypad() {
            return KEYPAD;
        }

    }


    public static class FancyBathroomKeypad extends GridKeypad {

        // @formatter:off
        private static final String[][] KEYPAD = new String[][] {
                { null, null, "1", null, null },
                { null,  "2", "3", "4", null },
                {   "5", "6", "7", "8", "9" },
                {  null, "A", "B", "C", null },
                { null, null, "D", null, null } };
        // @formatter:on


        public FancyBathroomKeypad() {
            super(0, 2);
        }


        @Override
        public String[][] getGridKeypad() {
            return KEYPAD;
        }

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
        List<GridKeypad> aviableKeypads = Arrays.asList(new NumberKeypad(), new FancyBathroomKeypad());
        for (Keypad keypad : aviableKeypads) {
            BathroomSecurity bathroomSecurity = new BathroomSecurity(keypad);
            List<String> buttonsToPress = bathroomSecurity.followInstructions(instructions);
            System.out.println(String.join("", buttonsToPress));
        }
    }

}
