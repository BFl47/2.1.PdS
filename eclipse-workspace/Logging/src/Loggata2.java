import java.io.IOException;
import java.util.logging.*;

public class Loggata2 {
	private static Logger logger = Logger.getLogger("Loggata2");
	
	private static void metodoEsempio() {
		System.out.println("sto eseguendo qualcosa");
	}
	public static void main(String[] args) throws SecurityException, IOException {
		logger.setLevel(Level.ALL);
		//logger.setUseParentHandlers(false);
		
		//manda il log su file
		FileHandler fh = new FileHandler("testo.txt");
		logger.addHandler(fh);
		logger.log(Level.FINE, "ok");
		
		try {
			Loggata2.metodoEsempio();
		} catch (Exception ex) {
			logger.log(Level.WARNING, "problemi nel metodo", ex);
		}
		logger.log(Level.INFO, "fatto");
		//logger.info("fatto");
	}
}
