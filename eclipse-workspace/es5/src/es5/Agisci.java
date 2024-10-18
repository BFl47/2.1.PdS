package es5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Agisci implements ActionListener {
	JPasswordField campopw;
	JTextArea testo;
	String pw = "";
	boolean finito;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (finito) {
			this.testo.setText("");
			finito = false;
		}
				
		if (!e.getActionCommand().equals("Mostra") && !e.getActionCommand().equals("C")) {
			this.pw += e.getActionCommand();
			this.campopw.setText(pw);
		}
		
		if (e.getActionCommand().equals("Mostra")) {
			this.testo.append(pw);
			this.pw = "";
			finito = true;
		}
		
		if (e.getActionCommand().equals("C")) {
			int conferma = JOptionPane.showConfirmDialog(null,"Cancellare il codice inserito?", "Pannello digitale",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (conferma == 0) {
				this.campopw.setText("");
				this.pw = "";
			}
		}
					
	}

}
