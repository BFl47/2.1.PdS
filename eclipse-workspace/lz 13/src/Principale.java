import java.awt.GridLayout;
import javax.swing.*;

public class Principale {

	public static void main(String[] args) {
		JFrame finestra = new JFrame();
		finestra.setLayout(new GridLayout(4, 1));
		Ascoltatore ascolta = new Ascoltatore();
		JButton scarica = new JButton("scarica");
		scarica.addActionListener(ascolta);
		finestra.add(scarica);
		JButton stato = new JButton("stato");
		stato.addActionListener(ascolta);
		finestra.add(stato);
		JButton congela = new JButton("congela");
		congela.addActionListener(ascolta);
		finestra.add(congela);
		JButton ferma = new JButton("ferma");
		ferma.addActionListener(ascolta);
		finestra.add(ferma);
		
		finestra.setVisible(true);
		finestra.pack();
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
