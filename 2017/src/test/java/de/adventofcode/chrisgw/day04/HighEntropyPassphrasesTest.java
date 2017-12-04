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
    }

    @Test
    public void passphrase_aa_bb_cc_dd_aa_should_not_be_valid() {
        String passphraseStr = "aa bb cc dd aa";
        String[] expectedWords = new String[] { "aa", "bb", "cc", "dd", "aa" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertFalse("Expect passphrase isValid false", passphrase.isValid());
    }

    @Test
    public void passphrase_aa_bb_cc_dd_aaa_should_be_valid() {
        String passphraseStr = "aa bb cc dd aaa ";
        String[] expectedWords = new String[] { "aa", "bb", "cc", "dd", "aaa" };

        Passphrase passphrase = HighEntropyPassphrases.parsePassphrase(passphraseStr);
        Assert.assertArrayEquals("Expect passphrase words", expectedWords, passphrase.getWords());
        Assert.assertTrue("Expect passphrase isValid true", passphrase.isValid());
    }

    @Test
    public void passphrase_myTask_countValidPassphrases() {
        long expectedValidPassphraseCount = 386;
        List<String> passphrasesLines = TestUtils.readAllLinesOfClassPathResource("/day04/Passphrases.txt");

        List<Passphrase> passphraseList = HighEntropyPassphrases.parsePassphrases(passphrasesLines);

        long validPassphraseCount = passphraseList.stream().filter(Passphrase::isValid).count();
        Assert.assertEquals("Expect validPassphraseCount", expectedValidPassphraseCount, validPassphraseCount);
    }

}