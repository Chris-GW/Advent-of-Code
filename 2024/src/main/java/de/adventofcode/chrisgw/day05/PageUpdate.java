package de.adventofcode.chrisgw.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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


    public PageUpdate correctOrder(List<PagePrintOrderRule> pagePrintOrderRules) {
        List<Integer> sortedPages = new ArrayList<>(this.pages);
        sortedPages.sort((page1, page2) -> {
            if (pagePrintOrderRules.contains(new PagePrintOrderRule(page1, page2))) {
                return -1;
            } else if (pagePrintOrderRules.contains(new PagePrintOrderRule(page2, page1))) {
                return 1;
            } else {
                return 0;
            }
        });
        return new PageUpdate(sortedPages);
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
