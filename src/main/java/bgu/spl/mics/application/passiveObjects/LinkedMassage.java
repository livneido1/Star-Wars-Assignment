package bgu.spl.mics.application.passiveObjects;

import bgu.spl.mics.Future;
import bgu.spl.mics.Message;
import bgu.spl.mics.application.LinkedMessage;
import sun.awt.image.ImageWatched;

public class  LinkedMassage {
    private LinkedMessage next ;
    private LinkedMessage prev;
    private Message message;

    public LinkedMassage(LinkedMessage next, LinkedMessage prev, Message message){
        this.next = next;
        this.prev =  prev;
        this.message = message;
    }

    public void setNext(LinkedMessage next) {
        this.next = next;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setPrev(LinkedMessage prev) {
        this.prev = prev;
    }

    public Message getMessage() {
        return message;
    }

    public LinkedMessage getPrev() {
        return prev;
    }

    public LinkedMessage getNext() {
        return next;
    }

}
