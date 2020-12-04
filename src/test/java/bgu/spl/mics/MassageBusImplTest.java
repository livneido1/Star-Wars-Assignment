package bgu.spl.mics;

import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.FinishBroadcast;
import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MassageBusImplTest {
    private MessageBusImpl messageBus;
    private HanSoloMicroservice hanSolo;
    private C3POMicroservice c3po;
    private LeiaMicroservice leia;


    @BeforeEach
    public void setMessageBus(){
        messageBus = new MessageBusImpl();
        hanSolo = new HanSoloMicroservice();
        c3po = new C3POMicroservice();

        leia =  new LeiaMicroservice(new Attack[3]);
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>TESTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void singeltonCheck(){
        MessageBusImpl  temp  =  new MessageBusImpl();

        assertTrue(temp ==  messageBus);
    }


    /**
     * this method tests the following methods in the MessageBusImpl
     * 1. register
     * 2. subscribeEvent
     * 3. sendEvent
     * 4. awaitMessage
     *
     * NOTICE!! the register method should be called in the constructor of the Microservices therefor
     * will be tested but unseen in the test code
     * NOTICE! this method does not test the round robin manner as well as the whole MicroService Objects
     */
    @Test
    public  void EventTests(){


        AttackEvent attackEvent =  new AttackEvent();
        Callback  emptyCallBack = (Message) -> {};

        hanSolo.subscribeEvent(attackEvent.getClass(),emptyCallBack);
        leia.sendEvent(attackEvent);
        Message e2 = null;
        try {
             e2 = messageBus.awaitMessage(hanSolo);
        }
        catch (Exception e){};


        assertTrue(e2 == attackEvent);


    }
    /**
     * this method tests the following methods in the MessageBusImpl
     * 1. register
     * 2. subscribeBroadCast
     * 3. sendBroadCast
     * 4. awaitMessage
     *
     * NOTICE! this method does not test the round robin manner as well as the whole MicroService Objects
     */
    @Test
    public void broadcastTests(){


        Broadcast broadcast =  new FinishBroadcast();
        Callback emptyCallBack = (Message) -> {};

        c3po.subscribeBroadcast(broadcast.getClass(),emptyCallBack);
        hanSolo.subscribeBroadcast(broadcast.getClass(),emptyCallBack);
        leia.sendBroadcast(broadcast);
        Message e2 = null;
        Message e3 =  null;
        try {
            e2 = messageBus.awaitMessage(c3po);
            e3 =  messageBus.awaitMessage(hanSolo);
        }
        catch (Exception e){};


        assertTrue(e2 == broadcast);
        assertTrue(e3 ==  broadcast);



    }

    @Test
    public void completeTest() {
        AttackEvent attackEvent = new AttackEvent();
        Callback  emptyCallBack = (Message) -> {};

        hanSolo.subscribeEvent(attackEvent.getClass(),emptyCallBack);
        Future <Boolean>  future  = leia.sendEvent(attackEvent);
        Message e2 = null;
        try {
            e2 = messageBus.awaitMessage(hanSolo);
        }
        catch (Exception e){};


        messageBus.complete(attackEvent, true);
        assertTrue(future.get());




    }

}
