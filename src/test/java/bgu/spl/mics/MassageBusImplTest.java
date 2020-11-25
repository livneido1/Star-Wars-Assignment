package bgu.spl.mics;

import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.swing.SwingUtilities2;

import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MassageBusImplTest {
    private MessageBusImpl messageBus;
    private MicroService hanSolo;
    private MicroService c3po;
    private MicroService leia ;
    private MicroService r2d2 ;
    private MicroService lando;

    @BeforeEach
    public void setMessageBus(){
        messageBus = new MessageBusImpl();
        hanSolo =  new HanSoloMicroservice();
        c3po =  new C3POMicroservice();
        Attack[] attacks =  new Attack[3];
        leia =  new LeiaMicroservice(attacks );
        r2d2 =  new R2D2Microservice(2000);
        lando =  new LandoMicroservice(2000);


    }


    @Test
    public void singeltonCheck(){
        MessageBusImpl  temp  =  new MessageBusImpl();

        assertTrue(temp ==  messageBus);
    }

  /*  @Test
    public void registerTest(){
        try {
            messageBus.register(hanSolo);
            messageBus.register(c3po);
            hanSolo.


        }catch ();



    }*/

    @Test
    public void sendBroadCastTest(){
        messageBus.register(hanSolo);
        messageBus.register(c3po);
        AttackEvent attackEvent = new AttackEvent();


        Broadcast broadcast =  new Broadcast() {
            public String message = "hi";

        };


        Broadcast broadcast2 =  new Broadcast() {
            public String message = "no good";

        };
        messageBus.subscribeBroadcast(broadcast.getClass(),hanSolo);
        messageBus.subscribeBroadcast(broadcast.getClass(),c3po);

        messageBus.sendBroadcast(broadcast);


    }

    @Test
    public void subscribeEventTest(){

        MicroService temp  =  new MicroService("m1") {
            @Override
            protected void initialize() {

            }
        };
        messageBus.register(hanSolo);
        messageBus.register(c3po);
        AttackEvent attackEvent = new AttackEvent();
        messageBus.subscribeEvent(attackEvent.getClass(),hanSolo);
        messageBus.subscribeEvent(attackEvent.getClass(),c3po);




    }



}
