
public class Persona {
	String nome;
	String cognome;
	
	public Persona(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public String toString() {
		return this.nome + " " + this.cognome;
	}
}
