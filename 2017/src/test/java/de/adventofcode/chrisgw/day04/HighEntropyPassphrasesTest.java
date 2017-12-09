package de.adventofcode.chrisgw.day04;

import de.adventofcode.chrisgw.TestUtils;
import de.adventofcode.chrisgw.day04.HighEntropyPassphrases.Passphrase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class HighEntropyPassphrasesTest {

    @Test
    public void passphrase_aa_bb_cc_dd_ee_should_be_valid() {
        String passphraseStr = "aa bb cc dd ee";
        String[] expectedWords = new String[] { "aa", "bb", "cc", "dd", "ee" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertTrue("Expect passphrase isValid true", passphrase.isValid());
        Assert.assertTrue("Expect passphrase isValid with anagrams true", passphrase.isValid(true));
    }

    @Test
    public void passphrase_aa_bb_cc_dd_aa_should_not_be_valid() {
        String passphraseStr = "aa bb cc dd aa";
        String[] expectedWords = new String[] { "aa", "bb", "cc", "dd", "aa" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertFalse("Expect passphrase isValid false", passphrase.isValid());
        Assert.assertFalse("Expect passphrase isValid with anagrams false", passphrase.isValid(true));
    }

    @Test
    public void passphrase_aa_bb_cc_dd_aaa_should_be_valid() {
        String passphraseStr = "aa bb cc dd aaa ";
        String[] expectedWords = new String[] { "aa", "bb", "cc", "dd", "aaa" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertTrue("Expect passphrase isValid true", passphrase.isValid());
        Assert.assertTrue("Expect passphrase isValid with anagrams true", passphrase.isValid(true));
    }

    @Test
    public void passphrase_myTask_countValidPassphrases() {
        long expectedValidPassphraseCount = 386;
        String classpathResource = "/day04/HighEntropyPassphrases_chrisgw.txt";
        List<String> passphrasesLines = TestUtils.readAllLinesOfClassPathResource(classpathResource);

        List<Passphrase> passphraseList = HighEntropyPassphrases.parsePassphrases(passphrasesLines);

        long validPassphraseCount = passphraseList.stream().filter(Passphrase::isValid).count();
        Assert.assertEquals("Expect validPassphraseCount", expectedValidPassphraseCount, validPassphraseCount);
    }


    // --- part 2

    @Test
    public void passphrase_myTask_countValidPassphrases_withAnagrams() {
        long expectedValidPassphraseCount = 208;
        String classpathResource = "/day04/HighEntropyPassphrases_chrisgw.txt";
        List<String> passphrasesLines = TestUtils.readAllLinesOfClassPathResource(classpathResource);

        List<Passphrase> passphraseList = HighEntropyPassphrases.parsePassphrases(passphrasesLines);

        long validPassphraseCount = passphraseList.stream().filter(passphrase -> passphrase.isValid(true)).count();
        Assert.assertEquals("Expect validPassphraseCount", expectedValidPassphraseCount, validPassphraseCount);
    }

    @Test
    public void passphrase_abcde_fghij_should_be_valid() {
        String passphraseStr = "abcde fghij";
        String[] expectedWords = new String[] { "abcde", "fghij" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertTrue("Expect passphrase isValid true", passphrase.isValid());
        Assert.assertTrue("Expect passphrase isValid with anagrams true", passphrase.isValid(true));
    }

    @Test
    public void passphrase_abcde_xyz_ecdab_should_not_be_valid() {
        String passphraseStr = "abcde xyz ecdab";
        String[] expectedWords = new String[] { "abcde", "xyz", "ecdab" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertTrue("Expect passphrase isValid false", passphrase.isValid());
        Assert.assertFalse("Expect passphrase isValid with anagrams false", passphrase.isValid(true));
    }

    @Test
    public void passphrase_a_ab_abc_abd_abf_abj_should_be_valid() {
        String passphraseStr = "a ab abc abd abf abj";
        String[] expectedWords = new String[] { "a", "ab", "abc", "abd", "abf", "abj" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertTrue("Expect passphrase isValid true", passphrase.isValid());
        Assert.assertTrue("Expect passphrase isValid with anagrams true", passphrase.isValid(true));
    }

    @Test
    public void passphrase_iiii_oiii_ooii_oooi_oooo_should_be_valid() {
        String passphraseStr = "iiii oiii ooii oooi oooo";
        String[] expectedWords = new String[] { "iiii", "oiii", "ooii", "oooi", "oooo" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertTrue("Expect passphrase isValid true", passphrase.isValid());
        Assert.assertTrue("Expect passphrase isValid with anagrams true", passphrase.isValid(true));
    }

    @Test
    public void passphrase_oiii_ioii_iioi_iiio_should_be_not_valid() {
        String passphraseStr = "oiii ioii iioi iiio";
        String[] expectedWords = new String[] { "oiii", "ioii", "iioi", "iiio" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertTrue("Expect passphrase isValid true", passphrase.isValid());
        Assert.assertFalse("Expect passphrase isValid with anagrams false", passphrase.isValid(true));
    }

}