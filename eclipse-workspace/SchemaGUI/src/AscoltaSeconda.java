import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ascoltatore per la seconda finestra;
 * simile al primo
 */
public class AscoltaSeconda implements ActionListener {
	static Logger log = Logger.getLogger("AscoltaSeconda");
	Seconda finestra;

	AscoltaSeconda(Seconda finestra) {
		log.info("ascoltatore creato");
		this.finestra = finestra;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* visualizzare SEMPRE gli eventi ricevuti!!! */
		log.info("evento ricevuto: " + e.getActionCommand());

		if (e.getActionCommand().equals("conta")) {
			Conta c = new Conta();
			Thread t = new Thread(c);
			t.start();
		}
		else if (e.getActionCommand().equals("chiudi"))
			this.finestra.setVisible(false);
	}
}