package es2;

public class Cerchio extends Ellisse {
	double raggio;

	public Cerchio(double raggio) {
		super(raggio, raggio);
		this.raggio = raggio;
	}
	
	public String toString() {
		return this.descrizione + " " + this.raggio;
	}
}
