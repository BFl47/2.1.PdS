package lz5;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Principale {

	public static void main(String[] args) {
		//Linked list
		LinkedList<Persona> l = new LinkedList<Persona>();
		l.add(new Persona("Gigi", 1954));
		l.add(new Persona("Luca", 1920));
		Persona x = new Persona("Mario", 1999);
		l.add(x);
		
		String s = "Mario";
		int a = 1999;
		Persona p = new Persona(s, a);
		
		System.out.println(l.contains(x));
		System.out.println(l.contains(p));
		//System.out.println(x == p);
		System.out.println(p.equals(x));
		//System.out.println(p.equals("abcd"));
		
		//Insiemi
		HashSet<Persona> ins = new HashSet<Persona>();
		ins.add(new Persona("Gigi", 1954));
		ins.add(new Persona("Luca", 1920));
		Persona y = new Persona("Mario", 1999);
		ins.add(y);
		
		System.out.println(p.equals(y));
		System.out.println(ins.contains(y));
		System.out.println(ins.contains(p));
		
		//Tree set
		TreeSet<Persona> t = new TreeSet<Persona>();
		t.add(new Persona("Gigi", 1954));
		t.add(new Persona("Luca", 1920));
		Persona z = new Persona("Mario", 1999);
		t.add(z);
		
		System.out.println(p.equals(z));
		System.out.println(t.contains(z));
		System.out.println(t.contains(p));
		
	}
}
