
public class Persona {
	String nome;
	int anno;
	
	int eta(int anno) {
		if (anno < this.anno) 
			throw new RuntimeException("errore!");
		return anno - this.anno;
	}
	//ridefinire println
	public String toString() {
		return this.nome + " " + this.anno;
	}
}