package bgu.spl.mics.application.services;


import bgu.spl.mics.Callback;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.BombDestroyerEvent;
//import bgu.spl.mics.application.messages.TerminateBroadcast;
import bgu.spl.mics.application.messages.FinishBroadcast;
import bgu.spl.mics.application.passiveObjects.Diary;

/**
 * LandoMicroservice
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class LandoMicroservice  extends MicroService {
    long duration;

    public LandoMicroservice(long duration) {
        super("Lando");
        this.duration=duration;
    }

    @Override
    protected void initialize() {

        super.subscribeEvent(BombDestroyerEvent.class,bombDestroyerEventCallback);
        subscribeBroadcast(FinishBroadcast.class,finishBroadcastCallback);
    }

    public long getDuration() {
        return duration;
    }
    Callback<BombDestroyerEvent> bombDestroyerEventCallback=(BombDestroyerEvent bombDestroyerEvent)->{
        Thread.currentThread().sleep(duration);
        this.complete(bombDestroyerEvent,true);
    };
    Callback<FinishBroadcast> finishBroadcastCallback=(FinishBroadcast finish)->{this.terminate();};
}
