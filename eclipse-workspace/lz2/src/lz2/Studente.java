package lz2;

public class Studente extends Persona{
	int matricola;
	
	//costruttore
	Studente(String nome, int anno, int matricola) {
		super(nome, anno);
		this.matricola = matricola;
	}
	
	public String toString() {
		return super.toString() + " " + this.matricola;
	}
	
	
}
