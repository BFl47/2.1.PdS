package prova1;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JTextArea;

public class Esegui implements Runnable {
	PrintWriter p;
	Scanner s;
	
	JTextArea area_usa;
	JTextArea area_italia;
	JTextArea area_log;

	boolean finito;
	HashSet<String> citta = new HashSet<String>();
	
	@Override
	public void run() {
		
		String r = s.nextLine();
		r = s.nextLine();
		System.out.println("ricevuto da server: " + r);
		while (!r.equals("END")) {
			System.out.println(r);
			//ITALIA:Napoli
			area_log.append(r);
			area_log.append("\n");
			
			String[] elem = r.split(":");
			if (elem[0].equalsIgnoreCase("usa")) {
				area_usa.append(elem[1]);
				
				if (citta.contains(elem[1]))
					area_usa.append(" dopp.");
				area_usa.append("\n");
			}
			if (elem[0].equalsIgnoreCase("italia")) {
				area_italia.append(elem[1]);
				
				if (citta.contains(elem[1]))
					area_italia.append(" dopp.");
				area_italia.append("\n");
			}
			
			citta.add(elem[1]);
			
			r = s.nextLine();
			if (r.equals("END")) {
				finito = true;
				System.out.println(r);
			}	
		}
	}
}