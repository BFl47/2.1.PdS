package persone;

public class Studente extends Persona {
	protected int matricola;
	String nome;

	public Studente(String nome, int anno, int matricola) {
		super(nome, anno);
		this.matricola = matricola;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		Studente s = (Studente) o;
		return this.matricola == s.matricola;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.matricola;		
	}

}
