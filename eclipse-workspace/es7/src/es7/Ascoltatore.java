package es7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Ascoltatore implements ActionListener {
	Scaricatore s;
	Thread t;
	JButton b;
	
	public Ascoltatore(JButton scarica) {
		this.b = scarica;
		this.s = new Scaricatore();
		this.s.bottone = b;
		this.t = new Thread(s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("scarica")) {
			System.out.println("scarica");
			this.b.setEnabled(false);
			this.t.start();
			this.b.setEnabled(true);
		}
		
		if (e.getActionCommand().equals("interrompi")) {
			if (this.s.scaricando) {
				this.s.scaricando = false;
				this.s.interrompo = true;
				this.t.interrupt();
				System.out.println("interrotto");
			}
		}
		
		if (e.getActionCommand().equals("visualizza")) {
			System.out.println("visualizza");
			System.out.println("t: " + t.getState());
		}
	}
}
