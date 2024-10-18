package lz9;

public class Coppia<P, S extends haLunghezza>{
	P primo;
	S secondo;
	
	public Coppia(P p, S s) {
		this.primo = p;
		this.secondo = s;
	}
/*
	@Override
	public String toString() {
		return primo.toString() + " " + secondo.toString();
	}
*/
	@Override
	public String toString() {
		//return primo.toString() + " " + secondo.toString();
		return primo.toString() + " " + secondo.getLunghezza();
	}
	
	

}
