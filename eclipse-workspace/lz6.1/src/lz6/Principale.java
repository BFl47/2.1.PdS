package lz6;

import javax.swing.JOptionPane;

public class Principale {

	public static void main(String[] args) {
		Esempio x;
		//x = new Esempio();
		
		String scelta = JOptionPane.showInputDialog("prima o seconda?");
		
		if (scelta.equalsIgnoreCase("prima"))
			x = new Prima();
		else
			x = new Seconda();
		
		int y = x.prova(1);
		System.out.println(y);
		
		

	}

}
