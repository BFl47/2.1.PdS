package prova4;

import java.util.Scanner;

public class Eseguibile implements Runnable {
	MyFrame fn = null;
	String comando;
	Scanner s;
	
	public Eseguibile(MyFrame fn, String comando, Scanner s) {
		this.fn = fn;
		this.comando = comando;
		this.s = s;
	}
	@Override
	public void run() {
		fn.area_image.append("=== Download iniziato ===\n");
		
		boolean running = true;
		
		while (running) {
			String r = s.nextLine();
			System.out.println(r);
			if (r.equals("END")) {
				running = false;
				fn.area_image.append("=== Download completato ===\n");
			}
			else if (r.equals("INTERRUPTED")) {
				//interrotto = true;
				running = false;
				fn.area_image.append("=== Download interrotto ===\n");
			}
			else if (r.equals("ERROR")) {
				fn.area_image.append("=== Errore ===\n");
				running = false;
			}
			else {
				fn.area_image.append(r + "\n");
			}
		}
		fn.partito = false;
		fn.setBottoni();
	}
}
