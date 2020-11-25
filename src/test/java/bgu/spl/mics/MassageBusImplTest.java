package bgu.spl.mics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MassageBusImplTest {
    private MessageBusImpl massageBus =  new MessageBusImpl();


    @Test
    public void singeltonCheck(){
        MessageBusImpl  temp  =  new MessageBusImpl();

        assertTrue(temp ==  massageBus);
    }

    @Test
    public void registerTest(){

    }




}
