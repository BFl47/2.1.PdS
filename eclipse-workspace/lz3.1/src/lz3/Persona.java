package lz3;

public class Persona {
	private String nome;
	int anno;
	
	public Persona() {}
	/*
	  public Persona() {
	 
	  	this.nome = null;
	  	this.anno = 0;
	}
	*/	
	public Persona(String nome, int anno) {
		this.nome = nome;
		this.anno = anno;
	}
	
	void setNome(String n) {
		this.nome = n;
	}
	
	public String toString() {
		return this.nome + " " + this.anno;
	}
	
}
