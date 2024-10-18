import java.util.logging.Logger;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * seconda finestra, simile alla prima
 */
public class Seconda extends JFrame {
	static Logger log = Logger.getLogger("Seconda");
	JButton conta;
	JButton chiudi;

	Seconda() {
		AscoltaSeconda a = new AscoltaSeconda(this);
		
		conta = new JButton("conta");
		this.add(conta, BorderLayout.NORTH);
		conta.addActionListener(a);

		chiudi = new JButton("chiudi");
		this.add(chiudi, BorderLayout.SOUTH);
		chiudi.addActionListener(a);

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* finestra inizialmente non visibile */
		this.setVisible(false);

		log.info("finestra creata");
	}
}