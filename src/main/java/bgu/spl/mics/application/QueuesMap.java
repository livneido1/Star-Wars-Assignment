package bgu.spl.mics.application;

import bgu.spl.mics.Message;
import bgu.spl.mics.MicroService;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class QueuesMap {
    private int size;
    MessageQueue first;
    MessageQueue last;




    public QueuesMap(){
        size = 0;
        first = null;
        last = null;
    }


    public synchronized int size() {
        return size;
    }


    public synchronized boolean isEmpty() {
        return size==0;
    }

    public synchronized boolean containsKey(MicroService key) {
        if (first == null) return false;
        else{
            MessageQueue temp =  first;
            boolean stop = false;
            while (temp.hasNextQueue() & !stop){
                if (temp.getKey()== key)
                    stop = true;
                else
                    temp = temp.getNextQueue();
            }
            return stop;
        }

    }



    public synchronized MessageQueue get(MicroService key) {
        if (first == null) return null;
        else{
            MessageQueue temp =  first;
            boolean stop = false;
            while (temp.hasNextQueue() & !stop){
                if (temp.getKey()== key)
                    stop = true;
                else
                    temp = temp.getNextQueue();
            }
            return temp;
        }

    }

    public synchronized void add(MessageQueue queue){
        MessageQueue temp = last;
        temp.setNextQueue(queue);
        last =  queue;
    }

    public synchronized MessageQueue remove(MicroService key) {
        if (first != null) {
            MessageQueue temp =  first;
            boolean stop = false;
            while (temp.hasNextQueue() & !stop){
                if (temp.getKey()== key)
                    stop = true;
                else
                    temp = temp.getNextQueue();
            }
            MessageQueue prev = temp.getPrevQueue();
            MessageQueue next =  temp.getNextQueue();
            prev.setNextQueue(next);
            if (next != null) next.setPrevQueue(prev);
            else last = prev;
            size --;
            return temp;

        }
        return null;

    }



    public synchronized void clear() {
        first = null;
        last = null;
        size = 0;

    }





}
