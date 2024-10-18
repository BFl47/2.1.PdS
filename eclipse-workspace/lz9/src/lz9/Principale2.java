package lz9;

import java.util.LinkedList;

public class Principale2 {

	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.add(new Persona());
		l.add(new Edificio());
		l.add("abc");
		l.add(new RuntimeException());
		l.add(12);
		
		Persona q = (Persona) l.get(0);
		
		System.out.println(l);
		
		LinkedList<Persona> l1;
		l1 = new LinkedList<Persona>();
		l1.add(new Persona());
		//l1.add(new Edificio());
		
		Persona p = l1.get(0);
		
		CoppiaPersonaEdificio c = new CoppiaPersonaEdificio();
		c.primo = new Persona();
		c.secondo = new Edificio();
		
		Persona uno = new Persona();
		Edificio due = new Edificio();
		Coppia<Persona, Edificio> c1 = new Coppia<Persona, Edificio>(uno, due);
		System.out.println(c1);

	}

}
