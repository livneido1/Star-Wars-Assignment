package bgu.spl.mics.application.passiveObjects;

public class Output {
    int totalAttacks;
    long HanSoloFinish;
    long C3PoFinish;
    long R2D2Deactivate;
    long LeiaTerminate;
    long HanSoloTerminate;
    long R2D2Terminate;
    long C3POTerminate;
    long LandoTerminate;
    public Output( int totalAttacks, long HanSoloFinish, long C3PoFinish, long R2D2Deactivate, long LeiaTerminate, long HanSoloTerminate,
             long C3POTerminate,long R2D2Terminate, long LandoTerminate)
    {
         this.totalAttacks=totalAttacks;
         this.HanSoloFinish=HanSoloFinish;
         this.C3PoFinish=C3PoFinish;
         this.R2D2Deactivate=R2D2Deactivate;
         this.LeiaTerminate=LeiaTerminate;
         this.HanSoloTerminate=HanSoloTerminate;
         this.R2D2Terminate=R2D2Terminate;
         this.C3POTerminate=C3POTerminate;
         this.LandoTerminate=LandoTerminate;
    }
}
