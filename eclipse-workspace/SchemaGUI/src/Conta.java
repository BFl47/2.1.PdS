import java.util.logging.Logger;

/**
 * contatore
 */
class Conta implements Runnable {
	static Logger log = Logger.getLogger("Conta");

	public void run() {
		log.info("conteggio iniziato");

		for (int i = 0; i < 5; i++) {
			System.out.println("conteggio: " + i);
			try {
			Thread.sleep(1000);
			}
			catch (InterruptedException e) {
			}
		}

		log.info("conteggio terminato");
	}
}