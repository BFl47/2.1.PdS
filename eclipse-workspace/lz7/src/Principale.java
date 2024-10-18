import persone.*;
import universita.Corso;
//import persone.Persona;

public class Principale {

	public static void main(String[] args) {
		Persona p = new Persona("Mario", 1999);
		//persone.Persona p = new persone.Persona();
		
		Studente s = new Studente("Gigi", 2002, 123456);
		Corso c = new Corso("Project");
		c.iscritti.add(s);
				
		Class x;
		x = p.getClass();
		System.out.println(x);
		//System.out.println(p.getClass());
		
		System.out.println(s instanceof Persona);
		System.out.println(p instanceof Studente);
		
		System.out.println(Persona.class);
		System.out.println("s istanza di persona? " + Persona.class.isInstance(s));
		
		System.out.println(s.getClass() == p.getClass());
		
		System.out.println(p.equals("abc"));
		System.out.println(p.equals(null));
		
		Persona r = new Studente("Luca", 2021, 34098);
		//Se matricola public
		//System.out.println(((Studente)r).matricola);		
		
		
		
		

	}

}
