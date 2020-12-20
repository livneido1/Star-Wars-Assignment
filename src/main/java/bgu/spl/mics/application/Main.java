package bgu.spl.mics.application;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.application.passiveObjects.*;
import bgu.spl.mics.application.services.C3POMicroservice;
import bgu.spl.mics.application.services.HanSoloMicroservice;
import bgu.spl.mics.application.services.LandoMicroservice;
import bgu.spl.mics.application.services.LeiaMicroservice;
import bgu.spl.mics.application.services.R2D2Microservice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;


/** This is the Main class of the application. You should parse the input file, 
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) {

		Gson gson = new Gson();
		Reader reader = null;
		try {
			reader = new FileReader(args[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Input input = gson.fromJson(reader, Input.class);
		MessageBusImpl messageBus = MessageBusImpl.getInstance();
		HanSoloMicroservice hanSolo = new HanSoloMicroservice();
		Thread hanSoloThread = new Thread(hanSolo);
		C3POMicroservice c3po = new C3POMicroservice();
		Thread c3poThread = new Thread(c3po);
		LeiaMicroservice leia = new LeiaMicroservice(input.getAttacks());
		Thread leiaThread = new Thread(leia);
		LandoMicroservice lando = new LandoMicroservice(input.getLando());
		Thread landoThread = new Thread(lando);
		R2D2Microservice r2d2 = new R2D2Microservice(input.getR2D2());
		Thread r2d2Thread = new Thread(r2d2);
		Ewoks ewoks = Ewoks.getInstance();
		ewoks.initialize(input.getEwoks());

		hanSoloThread.start();
		c3poThread.start();
		try {
			leiaThread.sleep(100);
			leiaThread.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		landoThread.start();
		r2d2Thread.start();
		try {
			hanSoloThread.join();
			c3poThread.join();
			leiaThread.join();
			r2d2Thread.join();
			landoThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Gson json = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter output = new FileWriter(args[1]);
			json.toJson(Diary.getInstance(), output);
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	}

