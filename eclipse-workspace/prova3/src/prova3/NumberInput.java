package prova3;

import javax.swing.JOptionPane;

public class NumberInput implements Reader {

	@Override
	public String read() {
		String digit = JOptionPane.showInputDialog("inserire numero:");
		while (Integer.parseInt(digit) < 0 || Integer.parseInt(digit) > 9) {
			digit = JOptionPane.showInputDialog("inserire numero:");
		}
		return digit;
	}

}
