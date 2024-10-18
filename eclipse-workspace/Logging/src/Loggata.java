import java.util.logging.*;

public class Loggata {
	private static Logger logger = Logger.getLogger("Loggata");
	
	private static void metodoEsempio() {
		System.out.println("sto eseguendo qualcosa");
	}
	public static void main(String[] args) {
		try {
			Loggata.metodoEsempio();
		} catch (Exception ex) {
			logger.log(Level.WARNING, "problemi nel metodo", ex);
		}
		//manda log su console (default)
		logger.log(Level.INFO, "fatto");
	}
}
