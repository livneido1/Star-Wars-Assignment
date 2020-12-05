package bgu.spl.mics.application;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.application.passiveObjects.Ewoks;
import bgu.spl.mics.application.services.*;
import com.google.gson.Gson;


import java.io.*;

/** This is the Main class of the application. You should parse the input file, 
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) {

		Gson json = new Gson();
		try (Reader reader = new FileReader(args[0]))  {
			Input input =  json.fromJson(reader, Input.class);
			MessageBusImpl messageBus = MessageBusImpl.getInstance();
			HanSoloMicroservice hanSolo =  new HanSoloMicroservice();
			Thread hanSoloThread = new Thread(hanSolo);
			C3POMicroservice c3po =  new C3POMicroservice();
			Thread c3poThread =  new Thread(c3po);
			LeiaMicroservice leia = new LeiaMicroservice(input.getAttacks());
			Thread leiaThread =  new Thread(leia);
			LandoMicroservice lando =  new LandoMicroservice(input.getLando());
			Thread landoThread =  new Thread(lando);
			R2D2Microservice r2d2 = new R2D2Microservice(input.getR2D2());
			Thread r2d2Thread =  new Thread(r2d2);
			Ewoks ewoks = Ewoks.getInstance();
			ewoks.initialize(input.getEwoks());

			hanSoloThread.start();
			c3poThread.start();
			leiaThread.start();
			landoThread.start();
			r2d2Thread.start();} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {}



	}
}
