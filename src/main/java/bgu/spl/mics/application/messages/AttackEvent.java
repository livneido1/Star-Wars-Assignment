package bgu.spl.mics.application.messages;
import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.Attack;

import java.util.List;

public class AttackEvent implements Event<Boolean> {

   Attack attack;
   public AttackEvent()
   {

   }

    public AttackEvent(Attack attack)
    {
        this.attack=attack;
    }
    public List<Integer> getSerialNumbers(){return attack.getSerials();}
    public  long getDuration(){return attack.getDuration();}

}
