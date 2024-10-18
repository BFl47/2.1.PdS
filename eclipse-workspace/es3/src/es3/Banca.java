package es3;

import java.util.TreeSet;

public class Banca {
	String nome;
	public TreeSet<ContoCorrente> conti;
	
	public Banca(String nome) {
		this.nome = nome;
		this.conti = new TreeSet<ContoCorrente>();
	}
	
	@Override
	public String toString() {
		return this.nome + " " + this.conti;
	}
	
	public void addConto(ContoCorrente c) {
		this.conti.add(c);
		c.banca = this;
	}
	
}
