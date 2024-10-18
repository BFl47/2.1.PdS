import java.util.LinkedList;

public class Principale {

	public static void main(String[] args) {
		Persona p = new Persona();
		p.nome = "Mario";
		p.anno = 1996;
		System.out.println(p);
		
		Studente s = new Studente();
		s.nome = "Alice";
		s.anno = 1992;
		s.matricola = 1238947;
		System.out.println(s);
		
		int eta = s.eta(1999); // lancia errore se anno < p.anno
		System.out.println(eta);
		
		Persona t[] = new Persona[2];
		t[0] = p;
		t[1] = s;
		// System.out.println(t); non stampa come ci si aspetterebbe
		for (int i = 0; i < 2; i++) 
			System.out.println(t[i]);
		
		LinkedList<Persona> l = new LinkedList<Persona>();
		l.add(p);
		l.add(s);
		System.out.println(l);

	}

}
