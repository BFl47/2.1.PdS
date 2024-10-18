package primo;

import javax.swing.JOptionPane;

class Punto {
	int x;
	int y;
}

public class Unico {
	
	static int fattoriale(int x) {
		if (x == 1)
			return 1;
		return x * fattoriale(x-1);
	}
	
	static public void main(String[] args) {
		System.out.println("prova");
		
		String s;
		s = JOptionPane.showInputDialog("inserisci una stringa:");
		System.out.println(s);
		
		System.out.println(fattoriale(3));
		
		Punto a;
		a = new Punto();
		a.x = 5;
		a.y = 6;
		System.out.println("x: " + a.x + " y: " + a.y);
		
		Persona b = new Persona();
		b.nome = "Mario";
		b.anno = 1992;
		b.stampa();
		System.out.println("età di " + b.nome + " : " + b.eta(2022));
		
		Studente c = new Studente();
		c.nome = "Alice";
		c.anno = 2000;
		c.matricola = 1010123;
		c.stampa_s();
	}
}
