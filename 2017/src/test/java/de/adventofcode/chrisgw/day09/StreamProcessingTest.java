package de.adventofcode.chrisgw.day09;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day09.CharacterGarbageLexer.Token;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static de.adventofcode.chrisgw.day09.CharacterGarbageLexer.TokenType.*;


public class StreamProcessingTest {


    @Test
    public void tokenizeCharacterGarbageStream_1_group() {
        String charaterGarbageStream = "{}";
        // @formatter:off
        List<Token> expectedTokens = Arrays.asList(
          new Token(GROUP_START, "{"),
          new Token(GROUP_END, "}"));
        // @formatter:on
        int expectedGroupScore = 1;

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);
        int groupScore = StreamProcessing.countGroupScoreForTokens(tokens);

        Assert.assertEquals("Same tokens", expectedTokens, tokens);
        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }

    @Test
    public void tokenizeCharacterGarbageStream_3_groups_nested() {
        String charaterGarbageStream = "{{{}}}";
        // @formatter:off
        List<Token> expectedTokens = Arrays.asList(
                new Token(GROUP_START, "{"),
                new Token(GROUP_START, "{"),
                new Token(GROUP_START, "{"),
                new Token(GROUP_END, "}"),
                new Token(GROUP_END, "}"),
                new Token(GROUP_END, "}"));
        // @formatter:on
        int expectedGroupScore = 6;

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);
        int groupScore = StreamProcessing.countGroupScoreForTokens(tokens);

        Assert.assertEquals("Same tokens", expectedTokens, tokens);
        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }

    @Test
    public void tokenizeCharacterGarbageStream_group_with_2_groups() {
        String charaterGarbageStream = "{{},{}}";
        // @formatter:off
        List<Token> expectedTokens = Arrays.asList(
                new Token(GROUP_START, "{"),
                new Token(GROUP_START, "{"),
                new Token(GROUP_END, "}"),
                new Token(SEPERATOR, ","),
                new Token(GROUP_START, "{"),
                new Token(GROUP_END, "}"),
                new Token(GROUP_END, "}"));
        // @formatter:on
        int expectedGroupScore = 5;

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);
        int groupScore = StreamProcessing.countGroupScoreForTokens(tokens);

        Assert.assertEquals("Same tokens", expectedTokens, tokens);
        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }

    @Test
    public void tokenizeCharacterGarbageStream_complicatedGroup() {
        String charaterGarbageStream = "{{{},{},{{}}}}";
        // @formatter:off
        List<Token> expectedTokens = Arrays.asList(
                new Token(GROUP_START, "{"),
                new Token(GROUP_START, "{"),
                new Token(GROUP_START, "{"),
                new Token(GROUP_END, "}"),
                new Token(SEPERATOR, ","),
                new Token(GROUP_START, "{"),
                new Token(GROUP_END, "}"),
                new Token(SEPERATOR, ","),
                new Token(GROUP_START, "{"),
                new Token(GROUP_START, "{"),
                new Token(GROUP_END, "}"),
                new Token(GROUP_END, "}"),
                new Token(GROUP_END, "}"),
                new Token(GROUP_END, "}"));
        // @formatter:on
        int expectedGroupScore = 16;

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);
        int groupScore = StreamProcessing.countGroupScoreForTokens(tokens);

        Assert.assertEquals("Same tokens", expectedTokens, tokens);
        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }

    @Test
    public void tokenizeCharacterGarbageStream_group_with_garbage() {
        String charaterGarbageStream = "{<{},{},{{}}>}";
        // @formatter:off
        List<Token> expectedTokens = Arrays.asList(
                new Token(GROUP_START, "{"),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "{},{},{{}}"),
                new Token(GARBAGE_END, ">"),
                new Token(GROUP_END, "}"));
        // @formatter:on

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);

        Assert.assertEquals("Same tokens", expectedTokens, tokens);
    }


    @Test
    public void tokenizeCharacterGarbageStream_group_with_seperated_garbage() {
        String charaterGarbageStream = "{<aa>,<aa>,<aa>,<aa>}";
        // @formatter:off
        List<Token> expectedTokens = Arrays.asList(
                new Token(GROUP_START, "{"),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "aa"),
                new Token(GARBAGE_END, ">"),
                new Token(SEPERATOR, ","),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "aa"),
                new Token(GARBAGE_END, ">"),
                new Token(SEPERATOR, ","),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "aa"),
                new Token(GARBAGE_END, ">"),
                new Token(SEPERATOR, ","),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "aa"),
                new Token(GARBAGE_END, ">"),
                new Token(GROUP_END, "}"));
        // @formatter:on
        int expectedGroupScore = 1;

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);
        int groupScore = StreamProcessing.countGroupScoreForTokens(tokens);

        Assert.assertEquals("Same tokens", expectedTokens, tokens);
        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }

    @Test
    public void tokenizeCharacterGarbageStream_group_with_4_groups_with_garbage() {
        String charaterGarbageStream = "{{<a>},{<a>},{<a>},{<a>}}";
        // @formatter:off
        List<Token> expectedTokens = Arrays.asList(
                new Token(GROUP_START, "{"),
                new Token(GROUP_START, "{"),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "a"),
                new Token(GARBAGE_END, ">"),
                new Token(GROUP_END, "}"),
                new Token(SEPERATOR, ","),

                new Token(GROUP_START, "{"),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "a"),
                new Token(GARBAGE_END, ">"),
                new Token(GROUP_END, "}"),
                new Token(SEPERATOR, ","),

                new Token(GROUP_START, "{"),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "a"),
                new Token(GARBAGE_END, ">"),
                new Token(GROUP_END, "}"),
                new Token(SEPERATOR, ","),

                new Token(GROUP_START, "{"),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "a"),
                new Token(GARBAGE_END, ">"),
                new Token(GROUP_END, "}"),
                new Token(GROUP_END, "}"));
        // @formatter:on
        int expectedGroupScore = 9;

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);
        int groupScore = StreamProcessing.countGroupScoreForTokens(tokens);

        Assert.assertEquals("Same tokens", expectedTokens, tokens);
        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }


    @Test
    public void tokenizeCharacterGarbageStream_group_escapedGarbage() {
        String charaterGarbageStream = "{{<!>},{<!>},{<!>},{<a>}}";
        // @formatter:off
        List<Token> expectedTokens = Arrays.asList(
                new Token(GROUP_START, "{"),
                new Token(GROUP_START, "{"),
                new Token(GARBAGE_START, "<"),
                new Token(GARBAGE, "!>},{<!>},{<!>},{<a"),
                new Token(GARBAGE_END, ">"),
                new Token(GROUP_END, "}"),
                new Token(GROUP_END, "}"));
        // @formatter:on

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);

        Assert.assertEquals("Same tokens", expectedTokens, tokens);
    }


    @Test
    public void tokenizeCharacterGarbageStream_group_emptyGarbage() {
        String charaterGarbageStream = "{<>,{},{<>}}";
        int expectedGroupScore = 1 + 2 + 2;

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);
        int groupScore = StreamProcessing.countGroupScoreForTokens(tokens);

        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }


    @Test
    public void tokenizeCharacterGarbageStream_myTask() {
        String classpathResource = "/day09/StreamProcessing_chrisgw.txt";
        String charaterGarbageStream = TestUtils.readAllLinesOfClassPathResource(classpathResource).get(0);
        int expectedGroupScore = 0;

        System.out.println(charaterGarbageStream);
        List<Token> tokens = StreamProcessing.tokenizeCharacterGarbageStream(charaterGarbageStream);
        printTokens(tokens);
        int groupScore = StreamProcessing.countGroupScoreForTokens(tokens);


       tokens.stream().filter(token -> token.type.equals(GARBAGE)).forEachOrdered(System.out::println);

        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }

    @Test
    public void simple_myTask() {
        String classpathResource = "/day09/StreamProcessing_chrisgw.txt";
        String charaterGarbageStream = TestUtils.readAllLinesOfClassPathResource(classpathResource).get(0);
        int expectedGroupScore = 21037;

        System.out.println(charaterGarbageStream);
        int groupScore = StreamProcessing.countGroupScoreForTokens(charaterGarbageStream);

        Assert.assertEquals("Expected group score", expectedGroupScore, groupScore);
    }



    private void printTokens(List<Token> tokens) {
        StringBuilder firstLine = new StringBuilder();
        StringBuilder secondLine = new StringBuilder();
        for (Token token : tokens) {
            int length = Math.max(token.value.length(), token.type.name().length());
            firstLine.append(String.format("%" + length + "s ", token.value));
            secondLine.append(String.format("%" + length + "s ", token.type));
        }
        System.out.println(firstLine);
        System.out.println(secondLine);
    }

}