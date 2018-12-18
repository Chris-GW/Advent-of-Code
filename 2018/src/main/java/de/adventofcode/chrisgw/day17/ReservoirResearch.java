package de.adventofcode.chrisgw.day17;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.adventofcode.chrisgw.day17.ReservoirResearch.WaterSquareFlowDirection.*;


public class ReservoirResearch {

    public static final Pattern CLAY_VEIN_LINE_PATTERN = Pattern.compile("([yx])=(\\d+),\\s+[yx]=(\\d+)\\.\\.(\\d+)");
    public static final WaterSquare WATER_SPRING_SQUARE = new WaterSquare(new GroundSquare(500, 0));


    private final Map<Integer, Map<Integer, GroundSquare>> clayGroundSquareMap = new HashMap<>();
    private final Map<Integer, Map<Integer, WaterSquare>> waterSquareMap = new HashMap<>();
    private final GroundSquare topLeftGroundSquare;
    private final GroundSquare bottomRightGroundSquare;
    private int rounds = 0;


    public ReservoirResearch(List<String> groundSliceLines) {
        putClayGroundSquare(WATER_SPRING_SQUARE);
        groundSliceLines.stream()
                .map(ReservoirResearch::parseClayVeinLine)
                .flatMap(List::stream)
                .forEach(this::putClayGroundSquare);
        this.topLeftGroundSquare = cornerGroundSquare(Math::min);
        this.bottomRightGroundSquare = cornerGroundSquare(Math::max);
        putWaterSquare(new WaterSquare(new GroundSquare(500, 0)));
    }

    private static List<GroundSquare> parseClayVeinLine(String clayVeinLine) {
        Matcher groundSliceSquareMatcher = CLAY_VEIN_LINE_PATTERN.matcher(clayVeinLine);
        if (!groundSliceSquareMatcher.matches()) {
            throw new IllegalArgumentException("Expect matching clayVeinLine for pattern: " + CLAY_VEIN_LINE_PATTERN);
        }

        boolean isFixedXCoordinate = "x".equalsIgnoreCase(groundSliceSquareMatcher.group(1));
        int a = Integer.parseInt(groundSliceSquareMatcher.group(2));
        int from = Integer.parseInt(groundSliceSquareMatcher.group(3));
        int to = Integer.parseInt(groundSliceSquareMatcher.group(4));

        List<GroundSquare> clayGroundSquares = new ArrayList<>(to - from + 1);
        for (int b = from; b <= to; b++) {
            GroundSquare clayGroundSquare;
            if (isFixedXCoordinate) {
                clayGroundSquare = new GroundSquare(a, b);
            } else {
                clayGroundSquare = new GroundSquare(b, a);
            }
            clayGroundSquares.add(clayGroundSquare);
        }
        return clayGroundSquares;
    }

    private GroundSquare cornerGroundSquare(IntBinaryOperator intBinaryOperator) {
        int x = clayGroundSquares().mapToInt(GroundSquare::getX).reduce(intBinaryOperator).orElse(0);
        int y = clayGroundSquares().mapToInt(GroundSquare::getY).reduce(intBinaryOperator).orElse(0);
        return new GroundSquare(x, y);
    }


    public boolean nextWaterSquare() {
        int restingWaterCount = waterSquareCount();

        while (flowingWaterSquares.size() > 0) {
            flowingWaterSquares = flowingWaterSquares.stream()
                    .flatMap(WaterSquare::flowToNextSquare)
                    .collect(Collectors.toList());

            flowingWaterSquares.stream().filter(WaterSquare::isRestingWater).forEach(this::putWaterSquare);

            Predicate<WaterSquare> isRestingWater = WaterSquare::isRestingWater;
            flowingWaterSquares.removeIf(isRestingWater.or(WaterSquare::isFlowingEndless));
            if (rounds == 41) {
                System.out.println(this.toString(flowingWaterSquares));
                System.out.println();
            }
        }
        printToFile();
        rounds++;
        return restingWaterCount < waterSquareCount();
    }


    public int countAllWaterSquares() {
        List<WaterSquare> flowingWaterSquares = Collections.singletonList(new WaterSquare(new GroundSquare(500, 0)));
        Set<GroundSquare> allVisitedSquares = new HashSet<>();

        while (flowingWaterSquares.size() > 0) {
            flowingWaterSquares = flowingWaterSquares.stream()
                    .flatMap(WaterSquare::flowToNextSquare)
                    .collect(Collectors.toList());

            flowingWaterSquares.removeIf(WaterSquare::isFlowingEndless);
            flowingWaterSquares.stream().map(WaterSquare::getCurrentSquare).forEach(allVisitedSquares::add);
        }
        return waterSquareCount() + allVisitedSquares.size();
    }


    public boolean isClayGroundSquare(GroundSquare groundSquare) {
        return isClayGroundSquare(groundSquare.getX(), groundSquare.getY());
    }

    public boolean isClayGroundSquare(int x, int y) {
        Map<Integer, GroundSquare> row = clayGroundSquareMap.getOrDefault(y, Collections.emptyMap());
        return row.containsKey(x);
    }

    private void putClayGroundSquare(GroundSquare groundSquare) {
        int y = groundSquare.getY();
        Map<Integer, GroundSquare> row = clayGroundSquareMap.computeIfAbsent(y, HashMap::new);
        row.put(groundSquare.getX(), groundSquare);
    }


    public Optional<WaterSquare> getWaterSquare(int x, int y) {
        Map<Integer, WaterSquare> row = waterSquareMap.getOrDefault(y, Collections.emptyMap());
        return Optional.ofNullable(row.get(x));
    }

    private void putWaterSquare(WaterSquare waterSquare) {
        GroundSquare currentSquare = waterSquare.getCurrentSquare();
        int y = currentSquare.getY();
        Map<Integer, WaterSquare> row = waterSquareMap.computeIfAbsent(y, HashMap::new);
        row.put(currentSquare.getX(), waterSquare);
    }


    public Stream<GroundSquare> clayGroundSquares() {
        return clayGroundSquareMap.values().stream().map(Map::values).flatMap(Collection::stream);
    }

    public Stream<WaterSquare> waterSquares() {
        return waterSquareMap.values().stream().map(Map::values).flatMap(Collection::stream);
    }


    public int clayGroundSquareCount() {
        return clayGroundSquareMap.values().stream().mapToInt(Map::size).sum();
    }

    public int waterSquareCount() {
        return waterSquareMap.values().stream().mapToInt(Map::size).sum();
    }


    @Data
    @Setter(AccessLevel.PRIVATE)
    public class WaterSquare {

        private GroundSquare currentSquare;
        private WaterSquareFlowDirection flowDirection;
        private Map<WaterSquareFlowDirection, WaterSquare> nearbyWaterSquares = new HashMap<>();
        private boolean isResting = false;


        public WaterSquare(GroundSquare currentSquare) {
            this.currentSquare = currentSquare;
            this.flowDirection = DOWN;
        }

        private WaterSquare(WaterSquare waterSquare) {
            this.currentSquare = waterSquare.getCurrentSquare();
            this.flowDirection = waterSquare.getFlowDirection();
            this.isResting = waterSquare.isResting;
        }


        private void flowToNextSquare() {
            if (isRestingWater()) {
            } else if (canFlow(DOWN)) {
                flow(DOWN);
            } else if (canFlow(flowDirection)) {
                flow(flowDirection);
            } else {
                WaterSquare leftWaterSquare = new WaterSquare(this);
                WaterSquare rightWaterSquare = new WaterSquare(this);
                if (leftWaterSquare.canSettleIn(LEFT) && rightWaterSquare.canSettleIn(RIGHT)) {
                    return collectRestingWaterSquares(leftWaterSquare, rightWaterSquare);
                } else if (canFlow(LEFT) && canFlow(RIGHT)) {
                    rightWaterSquare = new WaterSquare(this);
                    rightWaterSquare.flow(RIGHT);
                    this.flow(LEFT);
                    return Stream.of(this, rightWaterSquare);
                } else if (canFlow(RIGHT)) {
                    this.flow(RIGHT);
                } else if (canFlow(LEFT)) {
                    this.flow(RIGHT);
                } else {
                    flowDirection = RESTING;
                }
            }
            return Stream.of(this);
        }


        private boolean canSettleIn(WaterSquareFlowDirection flowDirection) {
            while (canFlow(flowDirection)) {
                flow(flowDirection);
                if (canFlow(DOWN) || isFlowingEndless()) {
                    return false;
                }
            }
            return true;
        }

        private Stream<WaterSquare> collectRestingWaterSquares(WaterSquare leftWaterSquare,
                WaterSquare rightWaterSquare) {
            int fromX = leftWaterSquare.getCurrentSquare().getX();
            int toX = rightWaterSquare.getCurrentSquare().getX();
            return IntStream.rangeClosed(fromX, toX)
                    .mapToObj(x -> new GroundSquare(x, currentSquare.getY()))
                    .map(groundSquare -> {
                        WaterSquare waterSquare = new WaterSquare(this);
                        waterSquare.currentSquare = groundSquare;
                        waterSquare.flowDirection = RESTING;
                        return waterSquare;
                    });
        }


        private boolean canFlow(WaterSquareFlowDirection flowDirection) {
            int x = currentSquare.getX() + flowDirection.getDx();
            int y = currentSquare.getY() + flowDirection.getDy();
            return !(isClayGroundSquare(x, y) || getWaterSquare(x, y).isPresent());
        }

        private WaterSquare flow(WaterSquareFlowDirection flowDirection) {
            int x = currentSquare.getX() + flowDirection.getDx();
            int y = currentSquare.getY() + flowDirection.getDy();
            WaterSquare waterSquare = new WaterSquare(this);
            waterSquare.currentSquare = new GroundSquare(x, y);
            waterSquare.flowDirection = flowDirection;
            return waterSquare;
        }


        public boolean isRestingWater() {
            return RESTING.equals(flowDirection);
        }

        public boolean isFlowingEndless() {
            return currentSquare.getX() < topLeftGroundSquare.getX()
                    || bottomRightGroundSquare.getX() < currentSquare.getX()
                    || currentSquare.getY() < topLeftGroundSquare.getY()
                    || bottomRightGroundSquare.getY() < currentSquare.getY();
        }

    }


    public enum WaterSquareFlowDirection {

        DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

        private int dx;
        private int dy;

        WaterSquareFlowDirection(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }

    }


    @Value
    public static class GroundSquare {

        private final int x;
        private final int y;


        public boolean isAt(int x, int y) {
            return this.x == x && this.y == y;
        }


        @Override
        public String toString() {
            return String.format("(%3d;%3d)", x, y);
        }

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendColumnNumbering(sb);
        for (int y = topLeftGroundSquare.getY(); y <= bottomRightGroundSquare.getY(); y++) {
            sb.append(String.format("%4d ", y));
            for (int x = topLeftGroundSquare.getX(); x <= bottomRightGroundSquare.getX(); x++) {
                appendSquare(sb, x, y);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void appendColumnNumbering(StringBuilder sb) {
        sb.append("     ");
        for (int x = topLeftGroundSquare.getX(); x <= bottomRightGroundSquare.getX(); x++) {
            sb.append((x * 10) % 10);
        }
        sb.append("\n");
        sb.append("     ");
        for (int x = topLeftGroundSquare.getX(); x <= bottomRightGroundSquare.getX(); x++) {
            sb.append(x % 10);
        }
        sb.append("\n");
    }

    private void appendSquare(StringBuilder sb, int x, int y) {
        Optional<WaterSquare> waterSquare = getWaterSquare(x, y);
        if (WATER_SPRING_SQUARE.getX() == x && WATER_SPRING_SQUARE.getY() == y) {
            sb.append('+');
        } else if (isClayGroundSquare(x, y)) {
            sb.append('#');
        } else if (waterSquare.map(WaterSquare::isRestingWater).orElse(false)) {
            sb.append('~');
        } else if (waterSquare.isPresent()) {
            sb.append('|');
        } else {
            sb.append('.');
        }
    }


    private void printToFile() {
        try {
            Path path = Paths.get("target/day16/waterSquares_" + rounds + ".txt");
            Files.createDirectories(path.getParent());
            Files.write(path, Collections.singletonList(toString()));
            System.out.println(path.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage <puzzleInputFile>");
            return;
        }
        Path puzzleInputFile = Paths.get(args[0]);

    }

}
