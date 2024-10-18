import java.util.logging.Logger;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * classe per la prima finestra
 */
public class Prima extends JFrame {
	static Logger log = Logger.getLogger("Prima");

	/* gli elementi grafici (pulsanti, ecc.) sono variabili di istanza */
	JButton pulsante;
	JTextField campo;

	/* anche le eventuali altre finestre */
	JFrame seconda;

	/**
	 * il costrutture:
	 * - crea l'ascoltatore
	 * - crea i pulsanti, i campi testo, gli eventuali pannelli, ecc.
	 * - li inserisce nella finestra
	 * - associa loro l'ascoltatore
	 * - decide se deve la finestra deve essere inizialmente visibile
	 */
	Prima(Seconda seconda) {
		/* memorizza l'altra finestra */
		this.seconda = seconda;

		/* l'ascoltatore riceve this */
		AscoltaPrima a = new AscoltaPrima(this);
		
		/* crea pulsante, lo aggiunge, associa l'ascoltatore */
		pulsante = new JButton("pulsante");
		this.add(pulsante, BorderLayout.SOUTH);
		pulsante.addActionListener(a);
		
		campo = new JTextField(20);
		this.add(campo, BorderLayout.NORTH);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* finestra inizialmente visibile */
		this.setVisible(true);

		log.info("finestra creata");
	}
}