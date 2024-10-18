package es2;

public class Quadrato extends Rettangolo {
	private double lato;
	
	public Quadrato(double lato) {
		super(lato, lato);
		this.lato = lato;
	}
	
	public String toString() {
		return this.descrizione + " " + this.lato;
	}
}
