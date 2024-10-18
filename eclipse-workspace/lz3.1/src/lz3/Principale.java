package lz3;

public class Principale {
	
	static void prima() {}
	static void prima(int x) {
		System.out.println("abcd");
	}
	static float prima(float f) {
		return 0;
	}
	static void prima(String s) {}
	//static String prima(String s) {}
	
	static void annullaP(Persona p) {
		p.setNome("NN");
		p.anno = 0;
	}

	public static void main(String[] args) {
		Persona p;
		p = new Persona("Mario", 1999);
		annullaP(p);
		System.out.println(p);
		
		Studente s;
		s = new Studente();
		s.setNome("Gigi");
		s.anno = 1998;
		s.matricola = 12356;
		System.out.println(s);
		
		prima(3);
		float x = prima(3.3f);
		System.out.println(x);
		
	}

}
