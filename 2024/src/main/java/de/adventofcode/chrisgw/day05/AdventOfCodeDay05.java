package de.adventofcode.chrisgw.day05;

import de.adventofcode.chrisgw.AdventOfCodePuzzleSolver;

import java.time.Year;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <a href="https://adventofcode.com/2024/day/5">Advent of Code - day 5</a>
 */
public class AdventOfCodeDay05 extends AdventOfCodePuzzleSolver {

    public AdventOfCodeDay05(List<String> inputLines) {
        super(Year.of(2024), 5, inputLines);
    }


    @Override
    public Integer solveFirstPart() {
        List<PagePrintOrderRule> pagePrintOrderRules = parseAllPagePrintOrderRules();
        List<PageUpdate> pageUpdates = parseAllPageUpdates(pagePrintOrderRules.size() + 1);
        return pageUpdates.stream()
                .filter(pageUpdate -> pageUpdate.isValid(pagePrintOrderRules))
                .mapToInt(PageUpdate::getMiddlePageNumber)
                .sum();
    }

    private List<PagePrintOrderRule> parseAllPagePrintOrderRules() {
        return inputLines()
                .takeWhile(Predicate.not(String::isBlank))
                .map(PagePrintOrderRule::parse)
                .toList();
    }

    private List<PageUpdate> parseAllPageUpdates(int skippedLines) {
        return inputLines()
                .skip(skippedLines)
                .map(PageUpdate::parse)
                .toList();
    }


    @Override
    public Integer solveSecondPart() {
        // TODO solveSecondPart
        return 0;
    }


    record PagePrintOrderRule(int page, int beforePage) {

        public static PagePrintOrderRule parse(String line) {
            Pattern pattern = Pattern.compile("(\\d+)\\|(\\d+)");
            Matcher matcher = pattern.matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Expect line matching pattern " + pattern + " but was: " + line);
            }
            int page = Integer.parseInt(matcher.group(1));
            int pageBefore = Integer.parseInt(matcher.group(2));
            return new PagePrintOrderRule(page, pageBefore);
        }


        @Override
        public String toString() {
            return page + "|" + beforePage;
        }

    }


    record PageUpdate(List<Integer> pages) {

        public static PageUpdate parse(String line) {
            List<Integer> pages = Pattern.compile(",")
                    .splitAsStream(line)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            return new PageUpdate(pages);
        }


        public boolean isValid(List<PagePrintOrderRule> pagePrintOrderRules) {
            for (int i = 0; i < pages.size(); i++) {
                int currentPage = pages.get(i);
                List<Integer> updatedPages = subUpdate(i);

                boolean validatesAllRules = pagePrintOrderRules.stream()
                        .filter(pagePrintOrderRule -> pagePrintOrderRule.beforePage() == currentPage)
                        .map(PagePrintOrderRule::page)
                        .filter(pages::contains)
                        .allMatch(updatedPages::contains);
                if (!validatesAllRules) {
                    return false;
                }
            }
            return true;
        }


        public List<Integer> subUpdate(int toIndex) {
            return pages().subList(0, toIndex);
        }

        public int getMiddlePageNumber() {
            int middleIndex = pages().size() / 2;
            return pages().get(middleIndex);
        }

        @Override
        public String toString() {
            return pages.toString();
        }

    }

}
