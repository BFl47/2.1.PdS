package lz5;

public class Persona implements Comparable<Object>{
	String nome;
	int anno;
	
	public Persona(String nome, int anno) {
		this.nome = nome;
		this.anno = anno;
	}
	
	@Override
	public boolean equals(Object o) {
		Persona p = (Persona) o;
		return this.nome == p.nome && this.anno == p.anno;
	}
	/*
	public boolean equals(Persona p) {
		return this.nome == p.nome && this.anno == p.anno;
	}
	*/
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.anno;
		//return 0;
	}
	
	@Override
	public int compareTo(Object o) {
		Persona p = (Persona) o;
		//per anno
		if (this.anno > p.anno)
			return 1;
		else if (this.anno == p.anno)
			return 0;
		else
			return -1;
		//per nome
		//return this.nome.compareTo(p.nome);
	}
	
	@Override
	public String toString() {
		return this.nome + "-" + this.anno;
	}
}
