package de.adventofcode.chrisgw.day15;

import lombok.*;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


public class BeverageBandits {

    @Getter
    private int battleRounds = 0;

    @Getter
    private int turns = 0;

    private GameField[][] gameArea;

    private List<GameUnit> gameUnits = new ArrayList<>();


    public BeverageBandits(List<String> gameAreaLines) {
        gameArea = new GameField[gameAreaLines.size()][];
        for (int y = 0; y < gameArea.length; y++) {
            String gameAreaRowStr = gameAreaLines.get(y);
            gameArea[y] = new GameField[gameAreaRowStr.length()];
            for (int x = 0; x < gameArea[y].length; x++) {
                char gameFieldChar = gameAreaRowStr.charAt(x);
                boolean isCavern = gameFieldChar != '#';
                GameField gameField = new GameField(x, y, isCavern);
                gameArea[y][x] = gameField;
                parseGameUnit(gameFieldChar, gameField).ifPresent(gameUnits::add);
            }
        }
    }

    private Optional<GameUnit> parseGameUnit(char gameFieldChar, GameField gameField) {
        for (GameFaction gameFaction : GameFaction.values()) {
            if (gameFieldChar == gameFaction.getUnitFactionLetter()) {
                return Optional.of(new GameUnit(gameFaction, gameField));
            }
        }
        return Optional.empty();
    }


    public void nextBattleRound() {
        List<GameUnit> livingUnits = aliveGameUnits().collect(Collectors.toList());
        while (!isOnlyOneFactionAlive() && livingUnits.size() > 0) {
            GameUnit nextUnit = livingUnits.remove(0);
            nextUnit.takeNextTurn();
        }
        if (livingUnits.size() == 0) {
            battleRounds++;
        }
    }

    void nextTurn() {
        aliveGameUnits().findFirst().ifPresent(GameUnit::takeNextTurn);
    }


    public boolean isOnlyOneFactionAlive() {
        return Arrays.stream(GameFaction.values())
                .map(this::isAnyUnitAliveOfFaction)
                .reduce(false, (factionIsAlive, otherFactionIsAlive) -> factionIsAlive ^ otherFactionIsAlive);
    }

    private boolean isAnyUnitAliveOfFaction(GameFaction faction) {
        return aliveGameUnits().map(GameUnit::getFaction).anyMatch(faction::equals);
    }


    public int calculateFinishingGameScore() {
        int remainingUnitHitPointSum = aliveGameUnits().mapToInt(GameUnit::getHitPoints).sum();
        return battleRounds * remainingUnitHitPointSum;
    }


    private Stream<GameUnit> aliveGameUnits() {
        return gameUnits.stream().filter(GameUnit::isAlive).sorted(Comparator.comparing(GameUnit::getCurrentField));
    }


    public GameField fieldAt(int x, int y) {
        if (0 <= y && y < gameArea.length && 0 <= x && x < gameArea[y].length) {
            return gameArea[y][x];
        } else {
            boolean isCavern = false;
            return new GameField(x, y, isCavern);
        }
    }


    public Stream<GameField> cavernFields() {
        return Arrays.stream(gameArea).flatMap(Arrays::stream).filter(GameField::isCavern);
    }


    public int size() {
        return gameArea.length * gameArea[0].length;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < gameArea.length; y++) {
            for (int x = 0; x < gameArea[y].length; x++) {
                GameField field = gameArea[y][x];
                if (field.isHoldingUnit()) {
                    GameUnit unit = field.getUnit();
                    sb.append(unit.getFaction().getUnitFactionLetter());
                } else if (field.isCavern()) {
                    sb.append('.');
                } else {
                    sb.append('#');
                }
            }

            sb.append("   ");
            sb.append(formatUnitStatusPerRow(gameArea[y]));
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String formatUnitStatusPerRow(GameField[] array) {
        return Arrays.stream(array).filter(GameField::isHoldingUnit).map(GameField::getUnit).map(gameUnit -> {
            char factionLetter = gameUnit.getFaction().getUnitFactionLetter();
            int hitPoints = gameUnit.getHitPoints();
            return factionLetter + "(" + hitPoints + ")";
        }).collect(Collectors.joining(", "));
    }


    @Getter
    @ToString
    @EqualsAndHashCode
    public class GameUnit implements Comparable<GameUnit> {

        private GameFaction faction;
        private GameField currentField;
        private int hitPoints = 200;
        private int attackValue = 3;


        public GameUnit(GameFaction faction, GameField currentField) {
            this.faction = faction;
            moveTo(currentField);
        }


        public void takeNextTurn() {
            if (!isAlive()) {
                return;
            }
            if (!isInRangeForAttackingEnemyUnit()) {
                moveIntoRangeOfNearestEnemyUnit();
            }
            attackNearestEnemyUnit();
            turns++;
        }

        private boolean isInRangeForAttackingEnemyUnit() {
            return adjacentCavernFields().filter(GameField::isHoldingUnit)
                    .map(GameField::getUnit)
                    .anyMatch(this::isEnemyTo);
        }


        private void moveIntoRangeOfNearestEnemyUnit() {
            List<List<GameField>> shortestPaths = aliveGameUnits().filter(this::isEnemyTo)
                    .sorted()
                    .flatMap(GameUnit::adjacentCavernFields)
                    .filter(GameField::isFreeCavern)
                    .sorted()
                    .map(currentField::shortestPathTo)
                    .collect(Collectors.toList());

            int shortestPathLength = shortestPaths.stream()
                    .mapToInt(List::size)
                    .filter(pathLength -> pathLength > 0)
                    .min()
                    .orElse(1);

            shortestPaths.stream()
                    .filter(gameFields -> gameFields.size() == shortestPathLength)
                    .map(gameFields -> gameFields.get(1))
                    .min(Comparator.naturalOrder())
                    .ifPresent(this::moveTo);
        }

        private void attackNearestEnemyUnit() {
            List<GameUnit> adjacentEnemies = currentField.adjacentFields()
                    .filter(GameField::isHoldingUnit)
                    .map(GameField::getUnit)
                    .filter(this::isEnemyTo)
                    .collect(Collectors.toList());
            Optional<GameUnit> selectedEnemyTarget = adjacentEnemies.stream()
                    .min(Comparator.comparingInt(GameUnit::getHitPoints).thenComparing(Comparator.naturalOrder()));
            selectedEnemyTarget.ifPresent(this::attackEnemyUnit);
        }

        private void attackEnemyUnit(GameUnit enemyUnit) {
            enemyUnit.hitPoints -= this.attackValue;
            if (!enemyUnit.isAlive()) {
                enemyUnit.moveTo(null);
            }
        }

        void setHitPoints(int hitPoints) {
            this.hitPoints = hitPoints;
        }


        private void moveTo(GameField field) {
            if (this.currentField != null) {
                this.currentField.unit = null;
            }
            if (field != null) {
                field.unit = this;
            }
            this.currentField = field;
        }


        public Stream<GameField> adjacentCavernFields() {
            return currentField.adjacentFields().filter(GameField::isCavern);
        }


        public boolean isAlive() {
            return hitPoints > 0;
        }

        public boolean isEnemyTo(GameUnit otherUnit) {
            return !this.faction.equals(otherUnit.faction);
        }


        @Override
        public int compareTo(GameUnit otherUnit) {
            return this.currentField.compareTo(otherUnit.currentField);
        }

    }


    @Data
    @Setter(AccessLevel.PRIVATE)
    public class GameField implements Comparable<GameField> {

        private final int x;
        private final int y;
        private final boolean isCavern;

        @EqualsAndHashCode.Exclude
        private GameUnit unit;


        public Stream<GameField> adjacentFields() {
            Builder<GameField> adjacentFields = Stream.builder();
            adjacentFields.add(fieldAt(x + 1, y + 0));
            adjacentFields.add(fieldAt(x - 1, y + 0));
            adjacentFields.add(fieldAt(x + 0, y + 1));
            adjacentFields.add(fieldAt(x + 0, y - 1));
            return adjacentFields.build();
        }


        public List<GameField> shortestPathTo(GameField otherField) {
            Map<GameField, Integer> distance = new HashMap<>(size());
            Map<GameField, GameField> prev = new HashMap<>(size());
            Set<GameField> q = cavernFields().collect(Collectors.toSet());

            distance.put(this, 0);

            while (q.size() > 0) {
                GameField u = q.stream()
                        .min(Comparator.comparingInt(
                                (GameField field) -> distance.getOrDefault(field, Integer.MAX_VALUE))
                                .thenComparing(Comparator.naturalOrder()))
                        .orElseThrow(IllegalStateException::new);
                q.remove(u);

                u.adjacentFields().filter(GameField::isCavern).sorted().forEachOrdered(v -> {
                    int distanceU = distance.getOrDefault(u, Integer.MAX_VALUE);
                    int distanceV = distance.getOrDefault(v, Integer.MAX_VALUE);
                    int altDistance = distanceU + 1;
                    if (altDistance < 0 || !u.equals(this) && !u.isFreeCavern()) {
                        return;
                    }
                    if (altDistance < distanceV) {
                        distance.put(v, altDistance);
                        prev.put(v, u);
                    }
                });
            }

            return readShortestPath(prev, otherField);
        }

        private List<GameField> readShortestPath(Map<GameField, GameField> prev, GameField target) {
            List<GameField> s = new ArrayList<>();
            GameField u = target;
            if (prev.containsKey(u)) {
                while (u != null) {
                    s.add(u);
                    u = prev.get(u);
                }
                Collections.reverse(s);
            }
            return s;
        }


        public int distanceTo(GameField otherField) {
            int dx = Math.abs(this.x - otherField.x);
            int dy = Math.abs(this.y - otherField.y);
            return dx + dy;
        }


        public boolean isHoldingUnit() {
            return unit != null;
        }


        public boolean isFreeCavern() {
            return isCavern && !isHoldingUnit();
        }


        @Override
        public int compareTo(GameField otherField) {
            return new CompareToBuilder().append(this.y, otherField.y).append(this.x, otherField.x).toComparison();
        }


        @Override
        public String toString() {
            if (!isCavern()) {
                return String.format("(%2d,%2d)=#", x, y);
            } else if (isHoldingUnit()) {
                GameFaction faction = unit.getFaction();
                return String.format("(%2d,%2d)=%s", x, y, faction.getUnitFactionLetter());
            } else {
                return String.format("(%2d,%2d)=.", x, y);
            }
        }

    }


    public enum GameFaction {
        ELVE('E'), GOBLIN('G');

        char unitFactionLetter;

        GameFaction(char unitFactionLetter) {
            this.unitFactionLetter = unitFactionLetter;
        }

        public char getUnitFactionLetter() {
            return unitFactionLetter;
        }

    }


    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage <neededRecipes>");
            return;
        }

    }

}
