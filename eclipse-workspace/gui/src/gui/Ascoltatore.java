package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Ascoltatore  implements ActionListener  {	
	String pw = "";
	boolean finito = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (finito) {
			MyFrame.areatesto.setText("");
			finito = false;
		}
		
		if (!comando.equals("Mostra") && !comando.equals("C")) {
			pw += e.getActionCommand();
			MyFrame.campopw.setText(pw);
		}
		
		if (comando.equals("Mostra")) {
			MyFrame.areatesto.append(pw);
			pw = "";
			finito = true;
		}
		
		if (comando.equals("C")) {
			int conferma = JOptionPane.showConfirmDialog(null, "Cancellare il codice inserito?", "Pannello Digitale", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (conferma == 0) {
				MyFrame.campopw.setText("");
				pw = "";
			}
		}	
	}
}
