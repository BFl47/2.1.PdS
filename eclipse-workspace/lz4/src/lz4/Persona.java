package lz4;

public class Persona {
	String nome;
	int anno;
	
	public Persona(String nome, int anno) {
		this.nome = nome;
		this.anno = anno;
	}
	@Override
	public String toString() {
		return this.nome + "-" + this.anno;
	}
}
