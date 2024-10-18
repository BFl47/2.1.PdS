package es3;

public abstract class ContoCorrente implements Comparable<ContoCorrente> {
	private int saldo = 0;
	public Banca banca;
	String codice;
	String nome;
	String cognome;
	
	public ContoCorrente(String codice, String nome, String cognome) {
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	@Override
	public String toString() {
		return this.codice + " " + this.saldo;
	}
	
	@Override
	public boolean equals(Object o) {
		ContoCorrente c = (ContoCorrente) o;
		return this.codice.equals(c.codice);
	}
	
	@Override
	public int compareTo(ContoCorrente c) {
		if (this.saldo < c.saldo) 
			return -1;
		else if (this.equals(c)) 
			return 0;
		else
			return 1;
	}
		
	public int getSaldo() {
		return this.saldo;
	}
	
	public void prelievo(int cifra) {
		this.banca.conti.remove(this);
		this.saldo -= cifra;
		this.banca.conti.add(this);
	}
	
	public void deposito(int cifra) {
		this.banca.conti.remove(this);
		this.saldo += cifra;
		this.banca.conti.add(this);
	}
}
