package lz4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Principale {
	/*
	public static Automobile immatricolaFerrari360GT(String targa) {
		Automobile a = new Automobile("Ferrari", "rosso");
		a.setTarga(targa);
		return a;
	}
	*/
	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(4);
		l.add(9);
		l.add(2);
		//l.add(new Integer(5));
		System.out.println("LinkedList: " + l);
		
		HashSet<Integer> ins = new HashSet<Integer>();
		ins.add(4);
		ins.add(9);
		ins.add(2);
		System.out.println("HashSet: " + ins);
		
		TreeSet<Integer> tree = new TreeSet<Integer>();
		tree.add(4);
		tree.add(9);
		tree.add(2);
		System.out.println("TreeSet: " + tree);
		/*
		Persona p = new Persona("Mario", 1999);
		Persona q = new Persona("Gigi", 1900);
		TreeSet<Persona> pp = new TreeSet<Persona>();
		pp.add(p);
		pp.add(q);
		System.out.println("TreeSet persona: " + pp);
		*/
		//Automobile a = immatricolaFerrari360GT("AB123CD")
		Automobile a = Automobile.immatricolaFerrari360GT("AB123CD");
		System.out.println(a);
				
	}

}
