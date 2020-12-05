package bgu.spl.mics.application.services;

import java.util.List;

import bgu.spl.mics.*;

import bgu.spl.mics.application.messages.*;
import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.passiveObjects.Diary;
import bgu.spl.mics.application.passiveObjects.Ewoks;

/**
 * HanSoloMicroservices is in charge of the handling {@link AttackEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link AttackEvent}.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class HanSoloMicroservice extends MicroService{

    public HanSoloMicroservice() {
        super("Han");
    }


    @Override
    protected void initialize() {
        subscribeEvent(AttackEvent.class,attackCallback);
        subscribeBroadcast(FinishBroadcast.class,finishBroadcastCallback);
    }


    Callback<AttackEvent> attackCallback = (AttackEvent attackEvent) -> {
        Thread.currentThread().sleep(attackEvent.getDuration());
        this.complete(attackEvent,true);
    };
    Callback<FinishBroadcast> finishBroadcastCallback=(FinishBroadcast finish)->{this.terminate();};


}
