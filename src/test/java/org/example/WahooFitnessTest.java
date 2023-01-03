package org.example;

import static org.junit.jupiter.api.Assertions.*;

class WahooFitnessTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void add() {
        WahooFitness sut = new WahooFitness();
        assert(sut.add(3,3) == 6);

    }
}