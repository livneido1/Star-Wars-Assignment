package bgu.spl.mics.application.passiveObjects;

import bgu.spl.mics.application.services.LandoMicroservice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Passive data-object representing a Diary - in which the flow of the battle is recorded.
 * We are going to compare your recordings with the expected recordings, and make sure that your output makes sense.
 * <p>
 * Do not add to this class nothing but a single constructor, getters and setters.
 */
public class Diary {
    private static Diary instance;
    AtomicInteger totalAttacks;
    long HanSoloFinish;
    long C3PoFinish;
    long R2D2Deactivate;
    long LeiaTerminate;
    long HanSoloTerminate;
    long R2D2Terminate;
    long C3POTerminate;
    long LandoTerminate;

    protected Diary(){
        totalAttacks = new AtomicInteger(0);
        HanSoloFinish=0;
        C3PoFinish=0;
        R2D2Deactivate=0;
        LeiaTerminate=0;
        HanSoloTerminate=0;
        R2D2Terminate=0;
        C3POTerminate=0;
        LandoTerminate=0;

    }


    //TODO need to set the MicroService Finish time

    public static Diary getInstance() {
        if (instance == null){
            instance =  new Diary();
        }
        return instance;
    }

    public void setC3PoFinish(long c3PoFinish) {
        C3PoFinish = c3PoFinish;
    }

    public void setHanSoloFinish(long hanSoloFinish) {
        HanSoloFinish = hanSoloFinish;
    }


    public void setC3POTerminate(long c3POTerminate) {
        C3POTerminate = c3POTerminate;
    }

    public void setHanSoloTerminate(long hanSoloTerminate) {
        HanSoloTerminate = hanSoloTerminate;
    }

    public void setLeiaTerminate(long leiaTerminate) {
        LeiaTerminate = leiaTerminate;
    }

    public void setR2D2Deactivate(long r2D2Deactivate) {
        R2D2Deactivate = r2D2Deactivate;
    }

    public void setLandoTerminate(long landoTerminate) {
        LandoTerminate = landoTerminate;
    }

    public void setR2D2Terminate(long r2D2Terminate) {
        R2D2Terminate = r2D2Terminate;
    }

    public void setTotalAttacks() {
        totalAttacks.set((totalAttacks.get() + 1));
    }

    public long getC3POTerminate() {
        return C3POTerminate;
    }

    public long getHanSoloFinish() {
        return HanSoloFinish;
    }

    public long getHanSoloTerminate() {
        return HanSoloTerminate;
    }

    public long getLandoTerminate() {
        return LandoTerminate;
    }

    public long getLeiaTerminate() {
        return LeiaTerminate;
    }

    public long getR2D2Deactivate() {
        return R2D2Deactivate;
    }

    public long getR2D2Terminate() {
        return R2D2Terminate;
    }

    public int getTotalAttacks() {
        return totalAttacks.get();
    }

    public long getC3PoFinish() {
        return C3PoFinish;
    }



}
