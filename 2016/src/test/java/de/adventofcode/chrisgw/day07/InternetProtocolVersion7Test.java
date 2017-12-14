package de.adventofcode.chrisgw.day07;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;


public class InternetProtocolVersion7Test {


    @Test
    public void ip7_isSupportingTLS_example_1() {
        String ip7 = "abba[mnop]qrst";
        boolean expectedSupportingTLS = true;

        InternetProtocolVersion7 ipv7 = new InternetProtocolVersion7(ip7);
        System.out.println(ipv7);
        boolean supportingTLS = ipv7.isSupportingTLS();

        Assert.assertEquals("Expect supporting TLS", expectedSupportingTLS, supportingTLS);
    }

    @Test
    public void ip7_isSupportingTLS_example_2() {
        String ip7 = "abcd[bddb]xyyx";
        boolean expectedSupportingTLS = false;

        InternetProtocolVersion7 ipv7 = new InternetProtocolVersion7(ip7);
        System.out.println(ipv7);
        boolean supportingTLS = ipv7.isSupportingTLS();

        Assert.assertEquals("Expect supporting TLS", expectedSupportingTLS, supportingTLS);
    }

    @Test
    public void ip7_isSupportingTLS_example_3() {
        String ip7 = "aaaa[qwer]tyui";
        boolean expectedSupportingTLS = false;

        InternetProtocolVersion7 ipv7 = new InternetProtocolVersion7(ip7);
        System.out.println(ipv7);
        boolean supportingTLS = ipv7.isSupportingTLS();

        Assert.assertEquals("Expect supporting TLS", expectedSupportingTLS, supportingTLS);
    }

    @Test
    public void ip7_isSupportingTLS_example_4() {
        String ip7 = "ioxxoj[asdfgh]zxcvbn";
        boolean expectedSupportingTLS = true;

        InternetProtocolVersion7 ipv7 = new InternetProtocolVersion7(ip7);
        System.out.println(ipv7);
        boolean supportingTLS = ipv7.isSupportingTLS();

        Assert.assertEquals("Expect supporting TLS", expectedSupportingTLS, supportingTLS);
    }


    @Test
    public void ip7_isSupportingTLS_myTask() {
        String classpathResource = "/day07/InternetProtocolVersion7_chrisgw.txt";
        List<InternetProtocolVersion7> ipv7List = TestUtils.readAllLinesOfClassPathResource(classpathResource)
                .stream()
                .map(InternetProtocolVersion7::new)
                .collect(Collectors.toList());
        long expectedCountIp7WichSupportingTLS = 110;

        long countIp7WichSupportingTLS = ipv7List.stream().filter(InternetProtocolVersion7::isSupportingTLS).count();

        Assert.assertEquals("Expected Ip7 wich supporting TLS", expectedCountIp7WichSupportingTLS,
                countIp7WichSupportingTLS);
    }

}