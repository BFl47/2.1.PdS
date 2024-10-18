package universita;

import java.util.HashSet;
import persone.Studente;

public class Corso {
	String nome;
	public HashSet<Studente> iscritti;
	
	public Corso(String nome) {
		this.nome = nome;
		this.iscritti = new HashSet<Studente>();
	}
	
	@Override
	public String toString() {
		return this.nome + " " + this.iscritti;
	}
	
}
