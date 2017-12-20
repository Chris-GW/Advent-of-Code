package de.adventofcode.chrisgw.day07;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <h1><a href="https://adventofcode.com/2016/day/7>Day 7: Internet Protocol Version 7</a></h1>
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
 *
 * --- Part Two ---
 *
 * You would also like to know which IPs support SSL (super-secret listening).
 *
 * An IP supports SSL if it has an Area-Broadcast Accessor, or ABA, anywhere
 * in the supernet sequences (outside any square bracketed sections), and a
 * corresponding Byte Allocation Block, or BAB, anywhere in the hypernet
 * sequences. An ABA is any three-character sequence which consists of the
 * same character twice with a different character between them, such as xyx
 * or aba. A corresponding BAB is the same characters but in reversed
 * positions: yxy and bab, respectively.
 *
 * For example:
 *
 * - aba[bab]xyz supports SSL (aba outside square brackets with
 *   corresponding bab within square brackets).
 * - xyx[xyx]xyx does not support SSL (xyx, but no corresponding yxy).
 * - aaa[kek]eke supports SSL (eke in supernet with corresponding kek in
 *   hypernet; the aaa sequence is not related, because the interior
 *   character must be different).
 * - zazbz[bzb]cdb supports SSL (zaz has no corresponding aza, but zbz has
 *   a corresponding bzb, even though zaz and zbz overlap).
 *
 * How many IPs in your puzzle input support SSL?
 * </pre>
 */
public class InternetProtocolVersion7 {

    private static final Pattern ABBA_PATTERN = Pattern.compile("([a-z])([a-z])\\2\\1", Pattern.CASE_INSENSITIVE);
    private static final Pattern ABA_PATTERN = Pattern.compile("([a-z])([a-z])\\1", Pattern.CASE_INSENSITIVE);

    private static final Pattern HYPERNET_SEQUENCE_PATTERN = Pattern.compile("\\[([a-z]*?)\\]",
            Pattern.CASE_INSENSITIVE);

    public String ip7;


    public InternetProtocolVersion7(String ip7) {
        this.ip7 = ip7;
    }


    public boolean isSupportingTLS() {
        List<MatchResult> hypernetSequenceMatchResults = findMatchResultHypernetSequence();
        List<MatchResult> abbaMatchResults = findMatchResultForABBA();

        for (MatchResult abbaMatchResult : abbaMatchResults) {
            if (isEnclosedByHypernetSequence(abbaMatchResult, hypernetSequenceMatchResults)) {
                return false;
            }
        }
        return abbaMatchResults.size() > 0;
    }

    private List<MatchResult> findMatchResultForABBA() {
        List<MatchResult> abbaMatchResults = new LinkedList<>();
        Matcher abbaMatcher = ABBA_PATTERN.matcher(ip7);
        while (abbaMatcher.find()) {
            MatchResult abbaMatchResult = abbaMatcher.toMatchResult();
            if (isValidABBA(abbaMatchResult)) {
                abbaMatchResults.add(abbaMatchResult);
            }
        }
        return abbaMatchResults;
    }

    private boolean isValidABBA(MatchResult abbaMatchResult) {
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


    private boolean isEnclosedByHypernetSequence(MatchResult matchResult,
            List<MatchResult> hypernetSequenceMatchResults) {
        for (MatchResult hypernetSequenceMatchResult : hypernetSequenceMatchResults) {
            int startInterval = hypernetSequenceMatchResult.start();
            int endInterval = hypernetSequenceMatchResult.end();
            if (startInterval <= matchResult.start() && matchResult.end() <= endInterval) {
                return true;
            }
        }
        return false;
    }


    public boolean isSupportingSSL() {
        List<MatchResult> hypernetSequenceMatchResults = findMatchResultHypernetSequence();
        List<MatchResult> abaMatchResults = findMatchResultForABA();

        for (MatchResult abaMatchResult : abaMatchResults) {
            if (isEnclosedByHypernetSequence(abaMatchResult, hypernetSequenceMatchResults)) {
                continue;
            }

            List<MatchResult> babMatchResults = findMatchResultForBAB(abaMatchResult);
            for (MatchResult babMatchResult : babMatchResults) {
                if (isEnclosedByHypernetSequence(babMatchResult, hypernetSequenceMatchResults)) {
                    return true;
                }
            }
        }

        return false;
    }


    private List<MatchResult> findMatchResultForABA() {
        List<MatchResult> abbaMatchResults = new LinkedList<>();
        Matcher abaMatcher = ABA_PATTERN.matcher(ip7);

        for (int i = 0; i < ip7.length() - 2; i++) {
            if (abaMatcher.region(i, i + 3).matches()) {
                MatchResult abaMatchResult = abaMatcher.toMatchResult();
                if (isValidABA(abaMatchResult)) {
                    abbaMatchResults.add(abaMatchResult);
                }
            }
        }
        return abbaMatchResults;
    }

    private boolean isValidABA(MatchResult abaMatchResult) {
        return !abaMatchResult.group(1).equalsIgnoreCase(abaMatchResult.group(2));
    }

    private List<MatchResult> findMatchResultForBAB(MatchResult abaMatchResult) {
        List<MatchResult> babMatchResults = new LinkedList<>();

        String a = abaMatchResult.group(1);
        String b = abaMatchResult.group(2);
        Pattern babPattern = Pattern.compile(String.format("(%s)(%s)(%s)", b, a, b), Pattern.CASE_INSENSITIVE);

        Matcher babMatcher = babPattern.matcher(ip7);
        for (int i = 0; i < ip7.length() - 2; i++) {
            if (babMatcher.region(i, i + 3).matches()) {
                MatchResult babMatchResult = babMatcher.toMatchResult();
                if (isValidABA(babMatchResult)) {
                    babMatchResults.add(babMatchResult);
                }
            }
        }
        return babMatchResults;
    }

    public String printSupportingTLS() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.toString()).append("\n");
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

    public String printSupportingSSL() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.toString()).append("\n");
        for (MatchResult abaMatchResult : findMatchResultForABA()) {
            for (int i = 0; i < abaMatchResult.start(); i++) {
                sb.append(" ");
            }
            sb.append(abaMatchResult.group()).append("\n");

            for (MatchResult babMatchResult : findMatchResultForBAB(abaMatchResult)) {
                for (int i = 0; i < babMatchResult.start(); i++) {
                    sb.append(" ");
                }
                sb.append(babMatchResult.group()).append("\n");
            }
        }

        return sb.toString();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ip7).append(" (");
        sb.append("TLS=").append(isSupportingTLS()).append(", ");
        sb.append("SSL=").append(isSupportingSSL()).append(")");
        return sb.toString();
    }

}
