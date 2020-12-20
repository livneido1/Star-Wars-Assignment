package bgu.spl.mics.application.passiveObjects;

import bgu.spl.mics.Message;

public class MessageQueue  {
    LinkedMessage first;
    LinkedMessage last;
    int size;


    public MessageQueue(){
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(Message message){
        LinkedMessage toInsert;
        if (first == null){
            toInsert =  new LinkedMessage(message,null,null );
            first = toInsert;
            last = toInsert;
        }
        else{
            LinkedMessage currLast = last;
            toInsert =  new LinkedMessage(message,null,currLast);
            currLast.setNext(toInsert);
            last = toInsert;
        }
        size++;
    }

    public LinkedMessage dequeue(){
        if (!isEmpty()) {
           /* LinkedMessage result = last;
            last = result.getPrev();
            last.setNext(null);*/
           LinkedMessage result=first;
           if (first.getNext()!=null) {
               first = first.getNext();
               first.setPrev(null);
           }
           else {
               first=null;
               last=null;
           }
            result.setNext(null);
            size--;
            return result;
        }
        else
            return null;
    }

    public boolean isEmpty(){return size == 0;};
}
