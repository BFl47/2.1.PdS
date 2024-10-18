package es7;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Finestra {

	public static void main(String[] args) {
		JFrame finestra = new JFrame();
		
		JButton scarica = new JButton("scarica");
		Ascoltatore a = new Ascoltatore(scarica);
		scarica.addActionListener(a);
		finestra.add(scarica, BorderLayout.NORTH);
		
		JButton interrompi = new JButton("interrompi");
		interrompi.addActionListener(a);
		finestra.add(interrompi, BorderLayout.CENTER);
		
		JButton visualizza = new JButton("visualizza");
		visualizza.addActionListener(a);
		finestra.add(visualizza, BorderLayout.SOUTH);

		finestra.setVisible(true);
		finestra.pack();
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
