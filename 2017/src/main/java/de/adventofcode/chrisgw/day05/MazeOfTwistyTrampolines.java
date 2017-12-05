package de.adventofcode.chrisgw.day05;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;


/**
 * <pre>
 * --- Day 5: A Maze of Twisty Trampolines, All Alike ---
 *
 * An urgent interrupt arrives from the CPU: it's trapped in a maze of jump
 * instructions, and it would like assistance from any programs with spare
 * cycles to help find the exit.
 *
 * The message includes a list of the offsets for each jump. Jumps are
 * relative: -1 moves to the previous instruction, and 2 skips the next one.
 * Start at the first instruction in the list. The goal is to follow the
 * jumps until one leads outside the list.
 *
 * In addition, these instructions are a little strange; after each jump, the
 * offset of that instruction increases by 1. So, if you come across an offset
 * of 3, you would move three instructions forward, but change it to a 4 for
 * the next time it is encountered.
 *
 * For example, consider the following list of jump offsets:
 * 0
 * 3
 * 0
 * 1
 * -3
 * Positive jumps ("forward") move downward; negative jumps move upward. For
 * legibility in this example, these offset values will be written all on one
 * line, with the current instruction marked in parentheses. The following
 * steps would be taken before an exit is found:
 *
 * - (0) 3  0  1  -3  - before we have taken any steps.
 * - (1) 3  0  1  -3  - jump with offset 0 (that is, don't jump at all).
 *   Fortunately, the instruction is then incremented to 1.
 * - 2 (3) 0  1  -3  - step forward because of the instruction we just
 *   modified. The first instruction is incremented again, now to 2.
 * - 2  4  0  1 (-3) - jump all the way to the end; leave a 4 behind.
 * - 2 (4) 0  1  -2  - go back to where we just were; increment -3 to -2.
 * - 2  5  0  1  -2  - jump 4 steps forward, escaping the maze.
 * In this example, the exit is reached in 5 steps.
 *
 * How many steps does it take to reach the exit?
 *
 * --- Part Two ---
 *
 * Now, the jumps are even stranger: after each jump, if the offset was three
 * or more, instead decrease it by 1. Otherwise, increase it by 1 as before.
 *
 * Using this rule with the above example, the process now takes 10 steps, and
 * the offset values after finding the exit are left as 2 3 2 3 -1.
 *
 * How many steps does it now take to reach the exit?
 * </pre>
 */
public class MazeOfTwistyTrampolines {

    private List<Integer> jumpInstructions;
    private int pointer = 0;
    private long stepCount = 0;
    private Function<Integer, Integer> jumpInsturctionModifier;


    public MazeOfTwistyTrampolines(List<Integer> jumpInstructions) {
        this(jumpInstructions, incrementJumpInsturction());
    }

    public MazeOfTwistyTrampolines(List<Integer> jumpInstructions, Function<Integer, Integer> jumpInsturctionModifier) {
        this.jumpInstructions = jumpInstructions;
        this.jumpInsturctionModifier = jumpInsturctionModifier;
    }


    public static Function<Integer, Integer> incrementJumpInsturction() {
        return jumpInstruction -> jumpInstruction + 1;
    }

    public static Function<Integer, Integer> incrementWhenSmallerThanLimitOtherwiseDecrement(int upperLimit) {
        return jumpInstruction -> {
            if (jumpInstruction < upperLimit) {
                return jumpInstruction + 1;
            } else {
                return jumpInstruction - 1;
            }
        };
    }


    public void followJumpInstructionsTillEnd() {
        while (isPointerInsideOfJumpInstructions()) {
            followNextJumpInstruction();
        }
    }

    public boolean isPointerInsideOfJumpInstructions() {
        return pointer >= 0 && pointer < jumpInstructions.size();
    }

    public void followNextJumpInstruction() {
        if (isPointerInsideOfJumpInstructions()) {
            int currentJumpInstruction = jumpInstructions.get(pointer);
            jumpInstructions.set(pointer, jumpInsturctionModifier.apply(currentJumpInstruction));
            pointer += currentJumpInstruction;
            stepCount++;
        }
    }


    public int getPointer() {
        return pointer;
    }

    public long getStepCount() {
        return stepCount;
    }

    public List<Integer> getJumpInstructions() {
        return Collections.unmodifiableList(jumpInstructions);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jumpInstructions.size(); i++) {
            int jumpInstruction = jumpInstructions.get(i);
            if (i == pointer) {
                sb.append("(").append(jumpInstruction).append(")");
            } else {
                sb.append(jumpInstruction);
            }
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
