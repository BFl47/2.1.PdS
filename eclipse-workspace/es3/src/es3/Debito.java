package es3;

public class Debito extends ContoCorrente {
	public Debito(String codice,  String nome, String cognome) {
		super(codice, nome, cognome);
	}
	
	void riconosciInteresse(double interesse) {
		this.deposito((int) (this.getSaldo() * interesse));
	}
}
