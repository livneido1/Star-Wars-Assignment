package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.Ewok;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EwokTest {
    private Ewok ewok;


    @BeforeEach
    public void setEwok(){ewok = new Ewok();}


    @Test
    public void acquireTest(){
        assertTrue(ewok.isAvailable());
        ewok.acquire();
        assertFalse(ewok.isAvailable());

    }

    @Test
    public void releaseTest(){

        ewok.acquire();
        assertFalse(ewok.isAvailable());
        ewok.release();
        assertTrue(ewok.isAvailable());

    }
}

