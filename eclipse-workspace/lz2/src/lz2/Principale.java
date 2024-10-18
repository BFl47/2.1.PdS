package lz2;

import java.util.HashSet;
import java.util.LinkedList;

public class Principale {
	
	static void stampa2000(LinkedList<Persona> l) {
		for (Persona x : l) {
			if (x.anno < 2000)
				System.out.println(x);
		}
	}

	public static void main(String[] args) {
		//Liste
		LinkedList<Object> l_tutti = new LinkedList<Object>();
		l_tutti.add(9);
		l_tutti.add("abcd");
		
		Persona p = new Persona("Mario", 2001);
		l_tutti.add(p);
		System.out.println("lista l_tutti: " + l_tutti);
		
		//Lista di persone
		LinkedList<Persona> l = new LinkedList<Persona>();
		l.add(p);
		
		Studente s = new Studente("Carlo", 1999, 123456);
		//l.add(s);
		l.add(0, s);
		System.out.println("lista l: " + l);

		stampa2000(l);
		
		//scansione lista
		for (int i = 0; i < l.size(); i++) 
			System.out.println(l.get(i));
		
		//verifica presenza elemento
		Persona q = new Persona("Gigi",  2001);
		if (l.contains(q))
			System.out.println("presente");
		else
			System.out.println("assente");
		
		l.add(1, q);
		
		//eliminazione elemento
		l.remove(p);
		System.out.println(l);
		
		//stampare primo elemento
		System.out.println(l.getFirst());
		System.out.println(l.getFirst().anno);
		System.out.println(l.getFirst().getNome()); // s.getNome()
		//System.out.println(l.getFirst().matricola); 
		System.out.println(((Studente) l.getFirst()).matricola);
		//Studente test = (Studente) l.getFirst();
		//System.out.println(test.matricola);
		
		
		
		//modificare nome persona
		p.setNome("Luca");
		System.out.println(p);
		l.add(p);
		
		//cast
		Persona f = s; //f = getFirst();
		System.out.println(f);
		((Studente)f).matricola = 123;
		System.out.println(s); //cambio matricola a s in modo indiretto
		
		//System.out.println(f.matricola);
		Studente y = (Studente)f;
		System.out.println(y.matricola);
	
		//Insiemi
		HashSet<Persona> ins = new HashSet<Persona>(l);
		System.out.println(ins);
		
		//scansione insieme
		for (Persona x : ins) {
			System.out.println(x);
		}
	}
}
