package bgu.spl.mics;

import bgu.spl.mics.application.passiveObjects.LinkedMessage;
import bgu.spl.mics.application.passiveObjects.MessageQueue;

import java.util.*;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus interface.
 * Write your implementation here!
 * Only private fields and methods can be added to this class.
 */
public class MessageBusImpl implements MessageBus {
	private HashMap <Class<? extends MicroService>, MessageQueue> queuesMap; // this queue holds the messages for the Microservices
	private HashMap<Class<? extends Message>,LinkedList<MicroService>> robinManner;
	private HashMap<Event,Future > eventMap;
	private static  MessageBusImpl instance ;

	private MessageBusImpl(){
		queuesMap =  new HashMap<>();
		robinManner =  new HashMap<>();
		eventMap =  new HashMap<>();

	}

	@Override
	public synchronized <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		if (robinManner.containsKey(type)){
			LinkedList<MicroService> list = robinManner.get((type));
			list.add(m);
		}
		else{
			LinkedList<MicroService> microServiceLinkedList = new LinkedList<>();
			microServiceLinkedList.add(m);
			robinManner.put(type,microServiceLinkedList);
		}
		if (!queuesMap.containsKey(m.getClass())){
			MessageQueue messageQueue = new MessageQueue();
			queuesMap.put(m.getClass(),messageQueue) ;
		}


	}

	@Override
	public synchronized void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		if (robinManner.containsKey(type)){
			LinkedList<MicroService> list = robinManner.get((type));
			list.add(m);
		}
		else{
			LinkedList<MicroService> microServiceLinkedList = new LinkedList<>();
			microServiceLinkedList.add(m);
			robinManner.put(type,microServiceLinkedList);
		}
		if (!queuesMap.containsKey(m.getClass())){
			MessageQueue messageQueue = new MessageQueue();
			queuesMap.put(m.getClass(),messageQueue) ;
		}
    }

	@Override @SuppressWarnings("unchecked")
	public <T> void complete(Event<T> e, T result) {
		eventMap.get(e).resolve(result);

	}

	@Override
	public synchronized void sendBroadcast(Broadcast b) {
		LinkedList<MicroService> list =   robinManner.get(b.getClass());
		for (MicroService microService: list){
			MessageQueue queue = queuesMap.get(microService.getClass());
			queue.enqueue(b);
			synchronized (queue){ queue.notifyAll();};
		}
	}

	
	@Override
	public synchronized  <T> Future<T> sendEvent(Event<T> e) {
		LinkedList<MicroService> list =   robinManner.get(e.getClass());
		MicroService first =  list.removeFirst();
		Future<T> future =  new Future<>();
		MessageQueue queue =  queuesMap.get(first.getClass());
		queue.enqueue(e);
		synchronized (queue){queue.notifyAll();}

		list.add(first);
		eventMap.put(e,future);
		return future;
	}

	@Override
		public void register(MicroService m) {
		MessageQueue messageQueue =  new MessageQueue();
		queuesMap.put(m.getClass(), messageQueue);

	}

	@Override
	public synchronized void  unregister(MicroService m) {
		queuesMap.remove(m.getClass());

		// Need to check whether we need it or not
		for(Map.Entry< Class<? extends  Message> ,LinkedList<MicroService>> entry : robinManner.entrySet()) {
			Class<? extends Message> key = entry.getKey();
			LinkedList<MicroService> value = entry.getValue();
			if (value.contains(m)){
				value.remove(m);
			}

		}




	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		MessageQueue queue = queuesMap.get(m.getClass());
		LinkedMessage linkedMessage =  queue.dequeue();
		while (linkedMessage == null){
			synchronized (queue) {
				queue.wait();
				linkedMessage = queue.dequeue();
			}
		}
		return linkedMessage.getMessage();

	}


	public synchronized static MessageBusImpl getInstance()
	{
		if (instance == null){
			instance = new MessageBusImpl();
		}
		return instance;
	}


}
