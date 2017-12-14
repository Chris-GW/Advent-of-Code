package de.adventofcode.chrisgw.day07;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <h1><a href="https://adventofcode.com/2016/day/10>Day 7: Internet Protocol Version 7</a></h1>
 * <pre>
 * While snooping around the local network of EBHQ, you compile a list of
 * IP addresses (they're IPv7, of course; IPv6 is much too limited). You'd
 * like to figure out which IPs support TLS (transport-layer snooping).
 *
 * An IP supports TLS if it has an Autonomous Bridge Bypass Annotation, or
 * ABBA. An ABBA is any four-character sequence which consists of a pair of
 * two different characters followed by the reverse of that pair, such as xyyx
 * or abba. However, the IP also must not have an ABBA within any hypernet
 * sequences, which are contained by square brackets.
 *
 * For example:
 *
 * - abba[mnop]qrst supports TLS (abba outside square brackets).
 * - abcd[bddb]xyyx does not support TLS (bddb is within square brackets,
 *   even though xyyx is outside square brackets).
 * - aaaa[qwer]tyui does not support TLS (aaaa is invalid; the interior
 *   characters must be different).
 * - ioxxoj[asdfgh]zxcvbn supports TLS (oxxo is outside square brackets,
 *   even though it's within a larger string).
 *
 * How many IPs in your puzzle input support TLS?
 * </pre>
 */
public class InternetProtocolVersion7 {

    private static final Pattern ABBA_PATTERN = Pattern.compile("([a-z])([a-z])\\2\\1", Pattern.CASE_INSENSITIVE);
    private static final Pattern HYPERNET_SEQUENCE_PATTERN = Pattern.compile("\\[([a-z]*?)\\]",
            Pattern.CASE_INSENSITIVE);

    public String ip7;


    public InternetProtocolVersion7(String ip7) {
        this.ip7 = ip7;
    }


    public boolean isSupportingTLS() {
        List<MatchResult> abbaMatchResults = findMatchResultForABBA();
        List<MatchResult> hypernetSequenceMatchResults = findMatchResultHypernetSequence();

        List<MatchResult> realAbbaMatchResults = new ArrayList<>();
        for (MatchResult abbaMatchResult : abbaMatchResults) {
            if (isEnclosedByHypernetSequence(abbaMatchResult, hypernetSequenceMatchResults)) {
                return false;
            }
            realAbbaMatchResults.add(abbaMatchResult);
        }
        return realAbbaMatchResults.size() > 0;
    }

    private List<MatchResult> findMatchResultForABBA() {
        List<MatchResult> abbaMatchResults = new LinkedList<>();
        Matcher abbaMatcher = ABBA_PATTERN.matcher(ip7);
        while (abbaMatcher.find()) {
            MatchResult abbaMatchResult = abbaMatcher.toMatchResult();
            if (isDifferentLetterPair(abbaMatchResult)) {
                abbaMatchResults.add(abbaMatchResult);
            }
        }
        return abbaMatchResults;
    }

    private boolean isDifferentLetterPair(MatchResult abbaMatchResult) {
        return !abbaMatchResult.group(1).equalsIgnoreCase(abbaMatchResult.group(2));
    }


    private List<MatchResult> findMatchResultHypernetSequence() {
        List<MatchResult> hypernetSequenceMatchResults = new LinkedList<>();
        Matcher hypernetSequenceMatcher = HYPERNET_SEQUENCE_PATTERN.matcher(ip7);
        while (hypernetSequenceMatcher.find()) {
            hypernetSequenceMatchResults.add(hypernetSequenceMatcher.toMatchResult());
        }
        return hypernetSequenceMatchResults;
    }


    private boolean isEnclosedByHypernetSequence(MatchResult abbaMatchResult,
            List<MatchResult> hypernetSequenceMatchResults) {
        for (MatchResult hypernetSequenceMatchResult : hypernetSequenceMatchResults) {
            int startInterval = hypernetSequenceMatchResult.start();
            int endInterval = hypernetSequenceMatchResult.end();
            if (startInterval <= abbaMatchResult.start() && abbaMatchResult.end() <= endInterval) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ip7).append(" = ").append(isSupportingTLS()).append("\n");
        for (MatchResult matchResult : findMatchResultForABBA()) {
            for (int i = 0; i < matchResult.start(); i++) {
                sb.append(" ");
            }
            sb.append(matchResult.group()).append("\n");
        }
        for (MatchResult matchResult : findMatchResultHypernetSequence()) {
            for (int i = 0; i < matchResult.start(); i++) {
                sb.append(" ");
            }
            sb.append(matchResult.group()).append("\n");
        }

        return sb.toString();
    }

}
