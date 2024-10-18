package persone;

public class Persona {
	String nome;
	int anno;
	
	public Persona(String nome, int anno) {
		this.nome = nome;
		this.anno = anno;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Persona p = (Persona) o;
		return this.nome.equals(p.nome) && this.anno == p.anno;
		
	}
	
	@Override
	public String toString() {
		return this.nome + " " + this.anno;
	}
}
