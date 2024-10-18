package lz4;

public class Automobile {
	String modello;
	String colore;
	private String targa;
	//costruttore 1
	public Automobile(String modello, String colore) {
		this.modello = modello;
		this.colore = colore;
	}
	//costruttore 2
	public Automobile(String modello, String colore, String targa) {
		this(modello, colore);
		this.targa = targa;
	}
	
	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	public String getTarga() {
		return this.targa;
	}
	
	public static Automobile immatricolaFerrari360GT(String targa) {
		Automobile a = new Automobile("Ferrari", "rosso");
		a.setTarga(targa);
		return a;
	}
	
	public String toString() {
		return this.modello + "-" + this.modello + "-" + this.targa;
	}
}
