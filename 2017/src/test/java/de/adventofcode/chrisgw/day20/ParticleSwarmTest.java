package de.adventofcode.chrisgw.day20;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class ParticleSwarmTest {

    @Test
    public void particleSwarm_part1_testTick_particle0() {
        String particleLine = "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>";
        List<Particle> expectedParticleAfterTick = Arrays.asList(
                Particle.parseParticleLine("p=<4,0,0>, v=<1,0,0>, a=<-1,0,0>"),
                Particle.parseParticleLine("p=<4,0,0>, v=<0,0,0>, a=<-1,0,0>"),
                Particle.parseParticleLine("p=<3,0,0>, v=<-1,0,0>, a=<-1,0,0>"));

        Particle particle = Particle.parseParticleLine(particleLine);

        for (int tick = 1; tick <= expectedParticleAfterTick.size(); tick++) {
            particle.nextTick();

            Particle expectedParticle = expectedParticleAfterTick.get(tick - 1);
            Assert.assertEquals("expect particle after tick: " + tick, expectedParticle, particle);
        }
    }

    @Test
    public void particleSwarm_part1_testTick_particle1() {
        String particleLine = "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>";
        List<Particle> expectedParticleAfterTick = Arrays.asList(
                Particle.parseParticleLine("p=<2,0,0>, v=<-2,0,0>, a=<-2,0,0>"),
                Particle.parseParticleLine("p=<-2,0,0>, v=<-4,0,0>, a=<-2,0,0>"),
                Particle.parseParticleLine("p=<-8,0,0>, v=<-6,0,0>, a=<-2,0,0>"));

        Particle particle = Particle.parseParticleLine(particleLine);

        for (int tick = 1; tick <= expectedParticleAfterTick.size(); tick++) {
            particle.nextTick();

            Particle expectedParticle = expectedParticleAfterTick.get(tick - 1);
            Assert.assertEquals("expect particle after tick: " + tick, expectedParticle, particle);
        }
    }


    @Test
    public void particleSwarm_part1_example() {
        List<String> particleLines = Arrays.asList( //
                "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>", //
                "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>");
        int expectedParticleIndex = 0;

        ParticleSwarm particleSwarm = ParticleSwarm.parseParticleSwarm(particleLines);
        System.out.println(particleSwarm);
        int particleIndex = particleSwarm.findParticleWithStaysNearOrigin();

        Assert.assertEquals("expect particle index", expectedParticleIndex, particleIndex);
    }

    @Test
    public void particleSwarm_part1_myTask() {
        List<String> particleLines = TestUtils.readAllLinesOfClassPathResource("/day20/ParticleSwarm_chrisgw.txt");
        int expectedParticleIndex = 243;

        ParticleSwarm particleSwarm = ParticleSwarm.parseParticleSwarm(particleLines);
        System.out.println(particleSwarm);
        int particleIndex = particleSwarm.findParticleWithStaysNearOrigin();

        Assert.assertEquals("expect particle index", expectedParticleIndex, particleIndex);
    }

}