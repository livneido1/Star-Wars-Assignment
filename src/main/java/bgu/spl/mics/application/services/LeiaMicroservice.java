package bgu.spl.mics.application.services;
import java.util.ArrayList;
import java.util.List;

import bgu.spl.mics.Callback;
import bgu.spl.mics.Future;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.messages.DeactivationEvent;
//import bgu.spl.mics.application.messages.TerminateBroadcast;
import bgu.spl.mics.application.messages.*;
import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.passiveObjects.Diary;

import javax.swing.plaf.FontUIResource;

/**
 * LeiaMicroservices Initialized with Attack objects, and sends them as  {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LeiaMicroservice extends MicroService {
	private Attack[] attacks;
	private Future[] futures;
	
    public LeiaMicroservice(Attack[] attacks) {
        super("Leia");
		this.attacks = attacks;
		this.futures=new Future[attacks.length];
    }

    @Override
    protected void initialize()  {
        //subscribeBroadcast(FinishBroadcast.class,finishBroadcastCallback);

    	for (int i=0;i<attacks.length;i++)
        {
            AttackEvent attackEvent=new AttackEvent(attacks[i]);
            futures[i]=sendEvent(attackEvent);

        }
    	for (int i=0;i<futures.length;i++)
        {
                try {
                    futures[i].get();
                }
                catch (InterruptedException e){}
        }


        DeactivationEvent deactivationEvent=new DeactivationEvent();
        Future deactiveFuture=sendEvent(deactivationEvent);
        try{
            deactiveFuture.get();
        }
        catch (InterruptedException e){}
        BombDestroyerEvent bombDestroyerEvent=new BombDestroyerEvent();
        Future bombFuture=sendEvent(bombDestroyerEvent);
        try{
            bombFuture.get();
        }
        catch (InterruptedException e){}
        MessageBusImpl.getInstance().notifyAll(); // this will release the R2D2 and C3PO from sleep.
        FinishBroadcast finishBroadcast= new FinishBroadcast();
        sendBroadcast(finishBroadcast);
        this.terminate();
        Diary.getInstance().setLeiaTerminate(System.currentTimeMillis());

    }


}
