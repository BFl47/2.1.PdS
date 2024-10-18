package lz2;

public class Persona  {
	private String nome;
	int anno;
	
	//costruttore	
	Persona(String n, int a) {
		if (n.equals(""))
			throw new RuntimeException("nome non valido");
		this.nome = n;
		this.anno = a;
	}
		
	public String toString() {
		return this.nome + " " + this.anno;
	}
	
	String getNome() {
		return this.nome;
	}
	
	void setNome(String n) {
		if (n.equals(""))
			throw new RuntimeException("nome non valido");
		this.nome = n;
	}
		
	
		
	
}
