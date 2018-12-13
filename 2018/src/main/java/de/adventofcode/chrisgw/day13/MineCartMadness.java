package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.day13.MineCart.MineCartDirection;
import lombok.Getter;
import lombok.Value;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MineCartMadness {

    private MineCartTrack[][] trackSystem;
    private List<MineCart> minesCarts;

    @Getter
    private int tick = 0;


    public MineCartMadness(List<String> trackLines) {
        this.minesCarts = new ArrayList<>();

        this.trackSystem = new MineCartTrack[trackLines.size()][];
        for (int y = 0; y < trackLines.size(); y++) {
            String trackLine = trackLines.get(y);
            this.trackSystem[y] = new MineCartTrack[trackLine.length()];
            for (int x = 0; x < trackLine.length(); x++) {
                char trackLetter = trackLine.charAt(x);
                if (trackLetter == ' ') {
                    continue;
                }
                MineCartTrack track = new MineCartTrack(x, y, toTrackLetter(trackLetter));
                this.trackSystem[y][x] = track;
                if (isMineCart(trackLetter)) {
                    minesCarts.add(new MineCart(trackLetter, track));
                }
            }
        }
    }


    public void nextTick() {
        Iterator<MineCart> sortedMineCartIterator = mineCarts() //
                .sorted(Comparator.comparing(MineCart::getCurrentTrack)) //
                .iterator(); //
        while (sortedMineCartIterator.hasNext()) {
            MineCart mineCart = sortedMineCartIterator.next();
            mineCart.nextTick();
            if (mineCarts().anyMatch(mineCart::collideWith)) {
                break;
            }
        }
        tick++;
    }


    public Stream<MineCart> mineCarts() {
        return minesCarts.stream();
    }


    public Stream<MineCart> crashedMineCarts() {
        return mineCarts().filter(mineCart -> mineCarts().anyMatch(mineCart::collideWith))
                .sorted(Comparator.comparing(MineCart::getCurrentTrack));
    }

    public boolean hasAnyCartCrahsed() {
        return crashedMineCarts().findAny().isPresent();
    }


    public MineCartTrack getTrack(int x, int y) {
        return trackSystem[y][x];
    }


    private boolean isMineCart(char trackLetter) {
        return trackLetter == '>' || trackLetter == '<' || //
                trackLetter == 'v' || trackLetter == '^';
    }

    private char toTrackLetter(char trackLetter) {
        switch (trackLetter) {
        case '>':
        case '<':
            return '-';
        case 'v':
        case '^':
            return '|';
        default:
            return trackLetter;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < trackSystem.length; y++) {
            for (int x = 0; x < trackSystem[y].length; x++) {
                sb.append(printSymbolAt(y, x));
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private char printSymbolAt(int y, int x) {
        char printSymbol;
        Optional<MineCartTrack> track = Optional.ofNullable(getTrack(x, y));
        Optional<MineCart> mineCart = track.flatMap(this::findMineCartForTrack);
        Optional<MineCart> collidedMineCart = mineCart.flatMap(thisMineCart -> mineCarts() //
                .filter(thisMineCart::collideWith) //
                .findAny());
        if (track.isPresent() && mineCart.isPresent() && collidedMineCart.isPresent()) {
            printSymbol = 'X';
        } else if (track.isPresent() && mineCart.isPresent()) {
            MineCartDirection mineCartDirection = mineCart.get().getDirection();
            printSymbol = mineCartDirection.getDirectionLetter();
        } else {
            printSymbol = track.map(MineCartTrack::getTrackType).orElse(' ');
        }
        return printSymbol;
    }


    private Optional<MineCart> findMineCartForTrack(MineCartTrack track) {
        return mineCarts().filter(mineCart -> mineCart.isOnTrack(track)).findAny();
    }


    @Value
    public class MineCartTrack implements Comparable<MineCartTrack> {

        private final int x;
        private final int y;
        private final char trackType;


        public MineCartTrack trackTo(MineCartDirection direction) {
            return getTrack(x + direction.getDx(), y + direction.getDy());
        }


        public boolean isIntersection() {
            return trackType == '+';
        }


        public boolean isCurve() {
            return trackType == '/' || trackType == '\\';
        }


        @Override
        public int compareTo(MineCartTrack otherTrack) {
            return new CompareToBuilder().append(this.x, otherTrack.x).append(this.y, otherTrack.y).toComparison();
        }

    }


    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage <puzzleInputFile>");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);
        MineCartMadness mineCartMadness = new MineCartMadness(Files.readAllLines(puzzleInputFile));
        System.out.println(mineCartMadness);
        System.out.println();

        while (!mineCartMadness.hasAnyCartCrahsed()) {
            mineCartMadness.nextTick();
        }

        System.out.println(mineCartMadness);
        System.out.println();

        List<MineCart> crashedMineCarts = mineCartMadness.crashedMineCarts().collect(Collectors.toList());
        MineCartTrack trackWithCrash = crashedMineCarts.get(0).getCurrentTrack();
        System.out.println("trackWithCrash " + trackWithCrash);
    }

}
