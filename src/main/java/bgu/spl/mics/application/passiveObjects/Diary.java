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
    String output;
    int totalAttacks;
    long HanSoloFinish;
    long C3PoFinish;
    long R2D2Deactivate;
    long LeiaTerminate;
    long HanSoloTerminate;
    long R2D2Terminate;
    long C3POTerminate;
    long LandoTerminate;

    private Diary(){
        totalAttacks = 0;
        HanSoloFinish=0;
        C3PoFinish=0;
        R2D2Deactivate=0;
        LeiaTerminate=0;
        HanSoloTerminate=0;
        R2D2Terminate=0;
        C3POTerminate=0;
        LandoTerminate=0;
        output = "";

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

    public synchronized void setTotalAttacks() {
        totalAttacks++;
    }

    public int getTotalAttacks() {
        return totalAttacks;
    }

    public long getC3PoFinish() {
        return C3PoFinish;
    }

    public String getOutput() {
        output = "totalAttacks: " + totalAttacks +"," + "\n"+
                "HanSoloFinish: " + HanSoloFinish + "," + "\n"+
                "C3POFinish: " + C3PoFinish + "," + "\n"+
                "R2D2Deactivate: " + R2D2Deactivate + "," + "\n"+
                "LeiaTerminate: " + LeiaTerminate + "," + "\n"+
                "HanSoloTerminate: " + HanSoloTerminate + "," + "\n"+
                "C3POTerminate: " + C3POTerminate + "," + "\n"+
                "R2D2Terminate: " + R2D2Terminate + "," + "\n"+
                "LandoTerminate: " + LandoTerminate ;
        return output;
    }

}
