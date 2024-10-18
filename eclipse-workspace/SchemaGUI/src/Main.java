import java.util.logging.Logger;

///
// crea un oggetto per finestra
// 

public class Main {
	static Logger log = Logger.getLogger("Main");

	public static void main(String[] args) {
		 Seconda s = new Seconda();
		 Prima p = new Prima(s);

		 log.info("finestre create");
	}
}

/*
Schema GUI:

- ogni finestra ha un suo ascoltatore
- ogni finestra è un oggetto che estende JFrame
- ogni finestra passa se stessa all'ascoltatore
- gli ascoltatori lanciano thread per operazioni lunghe

Classi:

Main.java
	classe principale: crea le due finestre e basta
Prima.java
	prima finestra
AscoltaPrima.java
	ascoltatore per la prima finestra
Seconda.java
	seconda finestra
AscoltaSeconda.java
	ascoltatore per la seconda finestra
Conta.java
	thread per una operazione lunga lanciata dalla seconda finestra
*/