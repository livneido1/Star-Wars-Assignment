package bgu.spl.mics.application.services;
import java.util.List;

import bgu.spl.mics.Callback;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AttackEvent;
//import bgu.spl.mics.application.messages.TerminateBroadcast;
import bgu.spl.mics.application.messages.FinishBroadcast;
import bgu.spl.mics.application.messages.allAttacksHandledBroadcast;
import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Ewoks;


/**
 * C3POMicroservices is in charge of the handling {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class C3POMicroservice extends MicroService {
	
    public C3POMicroservice() {
        super("C3PO");
    }

    @Override
    protected void initialize() {

        subscribeEvent(AttackEvent.class,attackCallback);
        subscribeBroadcast(FinishBroadcast.class,finishBroadcastCallback);
        subscribeBroadcast(allAttacksHandledBroadcast.class,allAttacksHandledBroadcastCallback);
    }
    Callback<AttackEvent> attackCallback = (AttackEvent attackEvent) -> {
        Thread.currentThread().sleep(attackEvent.getDuration());
        this.complete(attackEvent,true);};
    Callback<FinishBroadcast> finishBroadcastCallback=(FinishBroadcast finish)->{this.terminate();};
    Callback <allAttacksHandledBroadcast> allAttacksHandledBroadcastCallback=(allAttacksHandledBroadcast allAttacksHandledBroadcast)->{
        MessageBusImpl messageBus=new MessageBusImpl();
        messageBus.getInstance();
        messageBus.wait();
    };
}
