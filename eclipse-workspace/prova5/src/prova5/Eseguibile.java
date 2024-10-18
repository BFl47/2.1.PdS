package prova5;

import java.util.Scanner;

public class Eseguibile implements Runnable {
	Finestra fn = null;
	String dainviare;
	Scanner s;
	
	public Eseguibile(Finestra fn, String dainviare, Scanner s) {
		this.fn = fn;
		this.dainviare = dainviare;
		this.s = s;
	}
	
	@Override
	public void run() {
		boolean running = true;
		while (running) {
			String r = s.nextLine();
			System.out.println(r);
			fn.area_log.append(r);
			if (dainviare.equals("artisti")) {
				fn.area_artisti.append(r + "\n");
			}
		}

	}

}
