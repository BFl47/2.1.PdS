package lz10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

public class Agisci implements ActionListener{
	JTextArea testo;

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("premuto" + " " + e.getActionCommand());
		this.testo.append("\n" + e.getActionCommand());
		if (e.getActionCommand().equals("no")) {
			System.out.println("chiudi finestra");
			System.exit(0);
		}
			
	}
	
	

}
