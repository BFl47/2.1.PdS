import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ascoltatore per la prima finestra:
 * risponde agli eventi di pressione pulsanti, ecc.
 */
public class AscoltaPrima implements ActionListener {
	static Logger log = Logger.getLogger("AscoltaPrima");
	Prima finestra;

	/**
	 * memorizza la finestra che ascolta
	 */
	AscoltaPrima(Prima finestra) {
		log.info("ascoltatore creato");
		this.finestra = finestra;
	}

	/**
	 * risponde agli eventi
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/* visualizzare SEMPRE gli eventi ricevuti!!! */
		log.info("evento ricevuto: " + e.getActionCommand());

		if (e.getActionCommand().equals("pulsante")) {
			this.finestra.campo.setText("premuto");
			this.finestra.seconda.setVisible(true);
		}
	}
}