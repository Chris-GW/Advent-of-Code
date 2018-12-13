package de.adventofcode.chrisgw.day13;

import de.adventofcode.chrisgw.day13.MineCart.MineCartDirection;
import lombok.Getter;
import lombok.Value;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class MineCartMadness {

    private MineCartTrack[][] trackSystem;
    private Set<MineCart> mineCarts;
    private Set<MineCart> crashedMineCarts;

    @Getter
    private int tick = 0;


    public MineCartMadness(List<String> trackLines) {
        this.mineCarts = new HashSet<>();
        this.crashedMineCarts = new HashSet<>();

        int id = 0;
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
                    mineCarts.add(new MineCart(++id, trackLetter, track));
                }
            }
        }
    }


    public Collection<MineCart> nextTick() {
        Iterator<MineCart> sortedMineCartIterator = mineCarts() //
                .sorted(Comparator.comparing(MineCart::getCurrentTrack)) //
                .iterator(); //

        while (sortedMineCartIterator.hasNext()) {
            MineCart mineCart = sortedMineCartIterator.next();
            mineCart.nextTick();
            Optional<MineCart> crashedMineCart = findCrashedMineCard(mineCart);
            crashedMineCart.ifPresent(otherMineCart -> {
                crashedMineCarts.add(mineCart);
                crashedMineCarts.add(otherMineCart);
            });
        }
        tick++;
        mineCarts.removeAll(crashedMineCarts);
        return crashedMineCarts;
    }

    private Optional<MineCart> findCrashedMineCard(MineCart mineCart) {
        return mineCarts().filter(mineCart::collideWith).findAny();
    }


    public Stream<MineCart> mineCarts() {
        return mineCarts.stream();
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


    public int mineCartCount() {
        return mineCarts.size();
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

        Set<MineCart> crashedMineCarts = new HashSet<>();
        while (mineCartMadness.mineCartCount() > 1) {
            if (crashedMineCarts.addAll(mineCartMadness.nextTick())) {
                MineCart crashedMineCart = crashedMineCarts.stream().findAny().orElseThrow(IllegalStateException::new);
                MineCartTrack trackWithCrash = crashedMineCart.getCurrentTrack();
                System.out.println("track with crash: " + trackWithCrash);
            }
        }

        System.out.println(mineCartMadness);
        System.out.println();

        MineCart survivingMineCart = mineCartMadness.mineCarts().findAny().orElseThrow(IllegalArgumentException::new);
        MineCartTrack track = survivingMineCart.getCurrentTrack();
        System.out.println("track with surviving mineCart: " + track);
    }

}
