package prova3;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Eseguibile implements Runnable {
	ColoredButton[] bottoni;
	Scanner s;
	Finestra fn = null;
	
	public Eseguibile(Scanner s, Finestra fn) {
		this.bottoni = fn.bottoni;
		this.s = s;
		this.fn = fn;
	}
	@Override
	public void run() {
		setEnabled(false);
		boolean running = true;
		boolean interrotto = false;
		while (running) {
			String r = s.nextLine();
			System.out.println("server: " + r);
			String comandi[] = r.split(";"); 
			
			if (comandi[0].equals("*") && comandi[1].equals("*")) {
				running = false;
				continue;
			}
			
			if (comandi[0].equals("-1") && comandi[1].equals("-1")) {
				running = false;
				interrotto = true;
				continue;
			}
			
			int posizione = Integer.parseInt(comandi[0]);
			String estratto = comandi[1];
			System.out.println("mio: " + bottoni[posizione].getDigit());
			
			if (bottoni[posizione].getDigit().equals(estratto)) {
				bottoni[posizione].changeColor(Color.GREEN);
			}
			else
				bottoni[posizione].changeColor(Color.RED);
			
		}
		
		if (interrotto) {
			JOptionPane.showMessageDialog(null, "Hai perso!");
		} else {
			boolean verde = false;
			for (ColoredButton cb: bottoni) {
				if (cb.isGreen())
					verde = true;
			}
			if (verde)
				JOptionPane.showMessageDialog(null, "Hai vinto!");
			else 
				JOptionPane.showMessageDialog(null, "Hai perso!");
		}
		setEnabled(true);
		fn.partito = false;
		fn.setBottoni();
		
	}
	
	public void setEnabled(boolean state) {
		for (ColoredButton cb: bottoni) {
			cb.setEnabled(state);
		}
	}
}
