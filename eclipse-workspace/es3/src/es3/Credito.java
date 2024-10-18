package es3;

public class Credito extends ContoCorrente {
	private final int soglia = 2;
	private double commissione = 0.1;  //1%
	int operazioni = 0;
	
	public Credito(String codice, String nome, String cognome) {
		super(codice, nome, cognome);
	}
	
	public double getCommissione() {
		return this.commissione;
	}
	
	public void setCommissione(double c) {
		this.commissione = c;
	}
	
	public void reset() {
		operazioni = 0;
	}
	
	private void operazione() {
		operazioni++;
		if (operazioni > soglia)
			super.prelievo((int)(this.getSaldo() * commissione));
	}
	
	@Override
	public void prelievo(int cifra) {
		super.prelievo(cifra);
		operazione();
	}
	
	@Override
	public void deposito(int cifra) {
		super.deposito(cifra);
		operazione();
	}
}
