
public class Principale {

	public static void main(String[] args) {
		
		Duplicabile d;
		d = new Duplicabile();
		d.c = new Collegata();
		d.c.x = 12;
		
		//LIVELLO 1 (copia superficiale)
		
		//2 puntatori allo stesso oggetto Duplicabile
		Duplicabile e;
		e = d;
		e.c.x = 14;
		
		System.out.println(d.c.x);	//14
		System.out.println(e.c.x);	//14
		
		//2 oggetti Duplicabile che puntano allo stesso oggetto Collegata
		//(con funzione commentata)
		Duplicabile f;
		f = (Duplicabile) d.clone();
		f.c.x = 42;
		
		System.out.println(d.c.x);	//42
		System.out.println(f.c.x);	//42
		
		//LIVELLO 2 (copia profonda)
		
		//inizialmente 2 oggetti D che puntano allo stesso oggetto C
		//con n.c = (Collegata) this.c.clone() 2 oggetti differenti
		Duplicabile g;
		g = (Duplicabile) d.clone();
		g.c.x = 4;
		
		System.out.println(d.c.x);	//42
		System.out.println(g.c.x);	//4
		
		//sottoclassi
		Sotto s = new Sotto();
		s.c = new Collegata();
		s.a = new Collegata();
		s.c.x = 4;
		s.a.x = 16;
		
		Sotto q;
		q = (Sotto) s.clone();
		q.c.x = 7;
		q.a.x = 13;
		
		System.out.println(s.c.x);	//4
		System.out.println(q.c.x);	//7
		
		System.out.println(s.a.x);	//16
		System.out.println(q.a.x);	//13

	}

}
