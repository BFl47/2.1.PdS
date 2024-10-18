
public class Automobile {
	private String targa;		//immutabile (non modificabile)
	private String modello;		//immutabile
	String colore;
	Persona proprietario;
	//boolean targaModificata; //int targaModificata;
	
	public Automobile(String modello, String colore) {
		this.modello = modello;
		this.colore = colore;
	}
	
	public String getModello() {
		return this.modello;
	}
	
	public void setTarga(String targa) {
		if (targa.equals(""))
			throw new RuntimeException("targa vuota");
		/*
		if (!targaModificata) { // (targaModificata == 0)
			this.targa = targa;
			modificato = true;
		}
		*/
		if (this.targa == null)
			this.targa = targa;
		else
			throw new RuntimeException("targa già modificata");
	}
	
	public String toString() {
		return this.modello + "-" + this.colore + "-" + this.targa + "-" + this.proprietario;
	}
}
