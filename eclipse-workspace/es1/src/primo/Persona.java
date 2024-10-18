package primo;

public class Persona {
	String nome;
	int anno;
	
	void stampa() {
		System.out.println("nome: " + this.nome + " anno: " + this.anno);
	}
	
	int eta(int a) {
		return a - this.anno;
	}
}

/*
Persona p;
p = new Persona;
p.nome = "Mario";
p.anno = 1992;
stampa(p);
System.out.println("età:" + eta(2022);
*/