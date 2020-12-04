package bgu.spl.mics.application;

import bgu.spl.mics.Message;
import bgu.spl.mics.MicroService;
import jdk.vm.ci.meta.SpeculationLog;
import sun.awt.image.ImageWatched;

import java.util.List;

public class MessageQueue {
    private int size;
    private LinkedMessage first;
    private LinkedMessage last;
    private MessageQueue nextQueue;
    private MessageQueue prevQueue;
    final private MicroService key;

    public MessageQueue(MicroService key, MessageQueue nextQueue, MessageQueue prevQueue){
        size = 0;
        first = null;
        last = null;
        this.key = key;
        this.nextQueue = nextQueue;
        this.prevQueue = prevQueue;

    }

    public synchronized void add(Message message) {

        if (first == null) {
            LinkedMessage linkedMessage = new LinkedMessage(message, null, null);
            first = linkedMessage;
            last = linkedMessage;
        }
        else {
            LinkedMessage temp = last;
            LinkedMessage newMessage =  new LinkedMessage(message,null,temp );
            temp.setNext(newMessage);
        }
        size ++;
    }

    /**
     * thread safe
     *
     * @return the first Message in the queue, the first message to be inserted
     */
    public synchronized Message dequeue(){
        LinkedMessage output = first;
        if (size == 1){
            first = null;
            last =  null;
        }
        else{
            first = output.getNext();
            first.setPrev(null);
        }

        size --;
        return output.getMessage();
    }

    public int getSize() {
        return size;
    }

    public synchronized void setNextQueue(MessageQueue nextQueue) {
        this.nextQueue = nextQueue;
    }

    public synchronized void setPrevQueue(MessageQueue prevQueue) {
        this.prevQueue = prevQueue;
    }

    public MicroService getKey() {
        return key;
    }

    public MessageQueue getNextQueue() {
        return nextQueue;
    }

    public MessageQueue getPrevQueue() {
        return prevQueue;
    }

    public boolean hasNextQueue(){
        return nextQueue!= null;
    }
}
