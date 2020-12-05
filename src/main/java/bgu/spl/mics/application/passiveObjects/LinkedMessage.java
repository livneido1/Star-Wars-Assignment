package bgu.spl.mics.application.passiveObjects;

import bgu.spl.mics.Future;
import bgu.spl.mics.Message;

public class LinkedMessage {
    private Message message;
    private LinkedMessage next;
    private LinkedMessage prev;
    private Future<?> future;

    public LinkedMessage(Message message, LinkedMessage next, LinkedMessage prev){
        this.message = message;
        this.next = next;
        this.prev = prev;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setNext(LinkedMessage next) {
        this.next = next;
    }
    public void setPrev(LinkedMessage prev){
        this.prev = prev;
    }

    public Message getMessage() {
        return message;
    }

    public LinkedMessage getNext() {
        return next;
    }

    public LinkedMessage getPrev() {
        return prev;
    }


}
