//Studente sottoinsieme di Persona
class Studente extends Persona{
	int matricola;

	public String toString() {
		return super.toString() + " " + this.matricola;	
	}
}

/* public class Studente {
	String nome;
	int anno;
	int matricola;
	
	void stampa() {
		System.out.println(this.nome + " " + this.anno + " " + this.matricola);
	}
}
//s.stampa();
*/