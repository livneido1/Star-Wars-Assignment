package bgu.spl.mics.application.passiveObjects;

import bgu.spl.mics.application.LinkedMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * Passive object representing the resource manager.
 * <p>
 * This class must be implemented as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private methods and fields to this class.
 */
public class Ewoks {
    private HashMap<Integer , Ewok> ewokMap;
    private  static volatile Ewoks instance;
    private static Object lock = new Object();


    private Ewoks(){

        ewokMap = new HashMap<Integer, Ewok>();
    }

    public static Ewoks getInstance(){

        Ewoks local = instance;
        if (local==null){
            synchronized (lock){
                local = instance;
                if (local == null){
                    instance = new Ewoks();
                    local = instance;
                }

            }
        }
        return local;
    }

    public synchronized List<Ewok> aquire(List<Integer> serialNumbers) throws InterruptedException {


        LinkedList<Ewok> output = new LinkedList<Ewok>();
        boolean allFound =  false;
        while(!allFound ) {
            allFound = true;
            for (Integer curr : serialNumbers) {
                if (allFound) {// to restart if the Microservice couldn't get all Ewoks
                    if (ewokMap.containsKey(curr) && ewokMap.get(curr).isAvailable()) {
                        output.add(ewokMap.get(curr));

                    } else {
                        output.clear(); // if not all ewoks are free- let other use them.
                        wait();
                        allFound = false;

                    }
                }
            }

        }
        for (Ewok ewok : output){
            ewok.acquire();
        }

        return output;
    }

    public boolean release(List<Ewok> ewoks){
        boolean output = false;
        for (Ewok curr : ewoks){
            curr.release();
        }
        notifyAll();

        return true;
    }

    public synchronized void initialize(List<Ewok> ewoks){
        for (Ewok ewok : ewoks){
            ewokMap.put(ewok.getSerialNumber(),ewok);
        }
    }








}
