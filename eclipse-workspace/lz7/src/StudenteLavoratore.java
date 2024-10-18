import persone.Studente;

public class StudenteLavoratore extends Studente {

	public StudenteLavoratore(String nome, int anno, int matricola) {
		super(nome, anno, matricola);
	}

	void prova() {
		this.matricola = 12345;
	}
}
